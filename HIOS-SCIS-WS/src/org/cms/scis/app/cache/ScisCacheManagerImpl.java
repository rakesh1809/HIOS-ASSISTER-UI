package org.cms.scis.app.cache;

import java.io.Serializable;
import java.util.Calendar;

import javax.naming.InitialContext;
import org.apache.log4j.Logger;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.tree.Fqn;
import org.infinispan.tree.TreeCache;
import org.infinispan.tree.TreeCacheFactory;

public class ScisCacheManagerImpl implements ScisCacheManager {
	private static final Logger log = Logger.getLogger(ScisCacheManagerImpl.class);
	private static String cacheName = "scis-cache";
	private static ScisCacheManagerImpl _scisCacheManager;
	private TreeCache treeCache;
	private static boolean initialized = false;
	// general flag to enable or disable cache. is checked upon every cache call
	// (so can be modified at runtime)
	private static String systemPropertyToEnableCache = "SCIS.WS.CACHE.ENABLED";
	
	//Overwrites original day-long length for amount of time to keep an item in cache
	private static String systemPropertyToSetRefreshTime="SCIS.WS.CACHE.REFRESH.TIME";

	private ScisCacheManagerImpl() throws Exception {
		log.debug("Creating instance of ScisCacheManager...");
	}

	/*
	 * get instance of cache, check system properties each time to see if cache
	 * was enabled / disabled
	 */
	static public synchronized ScisCacheManagerImpl getInstance() throws Exception {

		if (_scisCacheManager == null) {
			log.debug("Getting instance of ScisCacheManager...");
			_scisCacheManager = new ScisCacheManagerImpl();
		}
		handleCacheEnabling();
		return _scisCacheManager;
	}

	static private void handleCacheEnabling() throws Exception {
		// enabled but not initialized
		if (!initialized && "true".equals(System.getProperty(systemPropertyToEnableCache))) {
			log.debug("initializing SCIS cache ...");
			_scisCacheManager.initialize();
			initialized = true;
		}

		// initialized and not enabled
		else if (initialized && !"true".equals(System.getProperty(systemPropertyToEnableCache))) {
			log.debug("deactivating SCIS cache ...");
			_scisCacheManager.deactivate();
			initialized = false;
		}

		// else ...
		else {
			if (log.isDebugEnabled()) {
				if (!initialized)
					log.debug("SCIS Caching is not enabled...");
				else
					log.debug("SCIS Caching is enabled...");
			}
		}

	}

	// start cache
	protected void initialize() throws Exception {
		InitialContext ic = new InitialContext();
		ConfigurationBuilder c = new ConfigurationBuilder();
		c.invocationBatching().enable();

		EmbeddedCacheManager cacheContainer = (EmbeddedCacheManager) ic
				.lookup("java:jboss/infinispan/container/SCISWsCacheContainer");
		cacheContainer = new DefaultCacheManager(c.build());
		Cache<String, String> cache = cacheContainer.getCache(cacheName);
		TreeCacheFactory tcf = new TreeCacheFactory();
		/*
		 * cache.getCacheManager().defineConfiguration(cacheName, new
		 * ConfigurationBuilder().read(cache.getCacheConfiguration()).
		 * invocationBatching().enable().build());
		 */ 
		treeCache = tcf.createTreeCache(cache);
	}

	// stop cache
	protected void deactivate() throws Exception {
		treeCache.removeNode(Fqn.fromString("/"));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cms.hios.cache.HiosCacheManager#add(java.lang.String,
	 * java.io.Serializable)
	 */
	public void add(String path, String key, Serializable obj) throws Exception {
		if ("true".equals(System.getProperty(systemPropertyToEnableCache)) ) {
			log.debug("Adding to cache: (" + path + "): " + key);
			String keyHash = Integer.toString(key.hashCode());
			treeCache.put(path, keyHash, new CacheObject(obj));
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cms.hios.cache.HiosCacheManager#get(java.lang.String)
	 */
	public Object get(String path, String key) throws Exception {
		Object result = null;
		log.debug("Getting from cache: " + key);
		if ("true".equals(System.getProperty(systemPropertyToEnableCache))) {
			String keyHash = Integer.toString(key.hashCode());
			CacheObject o = (CacheObject) treeCache.get(path, keyHash);
			if (o != null) {
				result = o.getObject();

				Calendar cachedOn = Calendar.getInstance();
				cachedOn.setTime(o.getDate());

				Calendar now = Calendar.getInstance();

				// expired if older than defined interval or at midnight if not
				// defined
				// system property)
				String refreshIntervalString = System.getProperty(systemPropertyToSetRefreshTime);
				if (refreshIntervalString != null) {
					try {
						int refreshInterval = Integer.parseInt(refreshIntervalString);
						cachedOn.add(Calendar.MINUTE, refreshInterval);
						if (now.after(cachedOn)) {
							log.debug("Cached object is older than refresh interval ... expiring ...");
							result = null;
						}
					} catch (Exception e) {
						log.error("Invalid refresh interval");
						throw new Exception("Invalid number for refresh interval");
					}
				} else {
					if (now.get(Calendar.DATE) != cachedOn.get(Calendar.DATE)) {
						log.debug("Cached object is not from today ... expiring ...");
						result = null;
					}
				}
			}
		}
		return result;
	}

	/**
	 * Evicts the cache
	 *
	 * @throws Exception
	 */
	public void clearCache() throws Exception {
		if ("true".equals(System.getProperty(systemPropertyToEnableCache))) {
			treeCache.removeNode(Fqn.fromString("/"));
		}

	}
}
