package org.cms.scis.app.cache;

import java.io.Serializable;
import java.util.Date;

/**
 * cachable object w/ time stamp
 * @author nsubramanian
 *
 */
public class CacheObject implements Serializable {

	private static final long serialVersionUID = -8552347003821793070L;

	private Serializable o;
	private Date date;

	public CacheObject(Serializable o) {
		this.o = o;
		this.date = new Date();
	}

	public Object getObject() {
		return o;
	}

	public Date getDate() {
		return date;
	}

}
