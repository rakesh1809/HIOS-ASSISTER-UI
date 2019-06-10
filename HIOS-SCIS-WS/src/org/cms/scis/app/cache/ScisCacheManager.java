package org.cms.scis.app.cache;

import java.io.Serializable;

public abstract interface ScisCacheManager
{
  public abstract void add(String paramString1, String paramString2, Serializable paramSerializable)
    throws Exception;
  
  public abstract Object get(String paramString1, String paramString2)
    throws Exception;
}
