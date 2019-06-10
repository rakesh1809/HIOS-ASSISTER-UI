package com.cgifederal.www;

import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;

public class IHIOSSCISServiceProxy
  implements IHIOSSCISService
{
  private String _endpoint = null;
  private IHIOSSCISService iHIOSSCISService = null;
  
  public IHIOSSCISServiceProxy()
  {
    _initIHIOSSCISServiceProxy();
  }
  
  public IHIOSSCISServiceProxy(String endpoint)
  {
    this._endpoint = endpoint;
    _initIHIOSSCISServiceProxy();
  }
  
  private void _initIHIOSSCISServiceProxy()
  {
    try
    {
      this.iHIOSSCISService = new HIOSSCISServiceLocator().getBasicHttpBinding_IHIOSSCISService();
      if (this.iHIOSSCISService != null) {
        if (this._endpoint != null) {
          ((Stub)this.iHIOSSCISService)._setProperty("javax.xml.rpc.service.endpoint.address", this._endpoint);
        } else {
          this._endpoint = ((String)((Stub)this.iHIOSSCISService)._getProperty("javax.xml.rpc.service.endpoint.address"));
        }
      }
    }
    catch (ServiceException serviceException) {}
  }
  
  public String getEndpoint()
  {
    return this._endpoint;
  }
  
  public void setEndpoint(String endpoint)
  {
    this._endpoint = endpoint;
    if (this.iHIOSSCISService != null) {
      ((Stub)this.iHIOSSCISService)._setProperty("javax.xml.rpc.service.endpoint.address", this._endpoint);
    }
  }
  
  public IHIOSSCISService getIHIOSSCISService()
  {
    if (this.iHIOSSCISService == null) {
      _initIHIOSSCISServiceProxy();
    }
    return this.iHIOSSCISService;
  }
  
  public ValidEntityResponse isValidIssuer(String issuerCode)
    throws RemoteException
  {
    if (this.iHIOSSCISService == null) {
      _initIHIOSSCISServiceProxy();
    }
    return this.iHIOSSCISService.isValidIssuer(issuerCode);
  }
  
  public ValidEntityResponse isValidProduct(String productCode)
    throws RemoteException
  {
    if (this.iHIOSSCISService == null) {
      _initIHIOSSCISServiceProxy();
    }
    return this.iHIOSSCISService.isValidProduct(productCode);
  }
  
  public ValidEntityResponse isValidIssuerProduct(String issuerCode, String productCode)
    throws RemoteException
  {
    if (this.iHIOSSCISService == null) {
      _initIHIOSSCISServiceProxy();
    }
    return this.iHIOSSCISService.isValidIssuerProduct(issuerCode, productCode);
  }
  
  public IssuerResponse[] validIssuers(String[] issuers)
    throws RemoteException
  {
    if (this.iHIOSSCISService == null) {
      _initIHIOSSCISServiceProxy();
    }
    return this.iHIOSSCISService.validIssuers(issuers);
  }
  
  public ProductResponse[] validProducts(String[] products)
    throws RemoteException
  {
    if (this.iHIOSSCISService == null) {
      _initIHIOSSCISServiceProxy();
    }
    return this.iHIOSSCISService.validProducts(products);
  }
  
  public IssuerProductResponse[] validIssuersProducts(IssuerProductRequest[] li)
    throws RemoteException
  {
    if (this.iHIOSSCISService == null) {
      _initIHIOSSCISServiceProxy();
    }
    return this.iHIOSSCISService.validIssuersProducts(li);
  }
}
