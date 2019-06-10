package com.cgifederal.www;

import java.rmi.Remote;
import java.rmi.RemoteException;

public abstract interface IHIOSSCISService
  extends Remote
{
  public abstract ValidEntityResponse isValidIssuer(String paramString)
    throws RemoteException;
  
  public abstract ValidEntityResponse isValidProduct(String paramString)
    throws RemoteException;
  
  public abstract ValidEntityResponse isValidIssuerProduct(String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract IssuerResponse[] validIssuers(String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract ProductResponse[] validProducts(String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract IssuerProductResponse[] validIssuersProducts(IssuerProductRequest[] paramArrayOfIssuerProductRequest)
    throws RemoteException;
}
