package com.cgifederal.www;

import java.net.URL;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

public abstract interface HIOSSCISService
  extends Service
{
  public abstract String getBasicHttpBinding_IHIOSSCISServiceAddress();
  
  public abstract IHIOSSCISService getBasicHttpBinding_IHIOSSCISService()
    throws ServiceException;
  
  public abstract IHIOSSCISService getBasicHttpBinding_IHIOSSCISService(URL paramURL)
    throws ServiceException;
}
