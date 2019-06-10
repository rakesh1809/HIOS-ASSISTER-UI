package com.cgifederal.www;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.util.HashSet;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import org.apache.axis.AxisFault;
import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.Service;
import org.apache.axis.client.Stub;

public class HIOSSCISServiceLocator
  extends Service
  implements HIOSSCISService
{
  public HIOSSCISServiceLocator() {}
  
  public HIOSSCISServiceLocator(EngineConfiguration config)
  {
    super(config);
  }
  
  public HIOSSCISServiceLocator(String wsdlLoc, QName sName)
    throws ServiceException
  {
    super(wsdlLoc, sName);
  }
  
  private String BasicHttpBinding_IHIOSSCISService_address = "http://ffx-hpms-iwp-wb.cgifederal.com/HIOSSCIS/HIOSSCISService.svc/mex";
  
  public String getBasicHttpBinding_IHIOSSCISServiceAddress()
  {
    return this.BasicHttpBinding_IHIOSSCISService_address;
  }
  
  private String BasicHttpBinding_IHIOSSCISServiceWSDDServiceName = "BasicHttpBinding_IHIOSSCISService";
  
  public String getBasicHttpBinding_IHIOSSCISServiceWSDDServiceName()
  {
    return this.BasicHttpBinding_IHIOSSCISServiceWSDDServiceName;
  }
  
  public void setBasicHttpBinding_IHIOSSCISServiceWSDDServiceName(String name)
  {
    this.BasicHttpBinding_IHIOSSCISServiceWSDDServiceName = name;
  }
  
  public IHIOSSCISService getBasicHttpBinding_IHIOSSCISService()
    throws ServiceException
  {
    URL endpoint;
    try
    {
      endpoint = new URL(this.BasicHttpBinding_IHIOSSCISService_address);
    }
    catch (MalformedURLException e)
    {
      throw new ServiceException(e);
    }
    return getBasicHttpBinding_IHIOSSCISService(endpoint);
  }
  
  public IHIOSSCISService getBasicHttpBinding_IHIOSSCISService(URL portAddress)
    throws ServiceException
  {
    try
    {
      BasicHttpBinding_IHIOSSCISServiceStub _stub = new BasicHttpBinding_IHIOSSCISServiceStub(portAddress, this);
      _stub.setPortName(getBasicHttpBinding_IHIOSSCISServiceWSDDServiceName());
      return _stub;
    }
    catch (AxisFault e) {}
    return null;
  }
  
  public void setBasicHttpBinding_IHIOSSCISServiceEndpointAddress(String address)
  {
    this.BasicHttpBinding_IHIOSSCISService_address = address;
  }
  
  public Remote getPort(Class serviceEndpointInterface)
    throws ServiceException
  {
    try
    {
      if (IHIOSSCISService.class.isAssignableFrom(serviceEndpointInterface))
      {
        BasicHttpBinding_IHIOSSCISServiceStub _stub = new BasicHttpBinding_IHIOSSCISServiceStub(new URL(this.BasicHttpBinding_IHIOSSCISService_address), this);
        _stub.setPortName(getBasicHttpBinding_IHIOSSCISServiceWSDDServiceName());
        return _stub;
      }
    }
    catch (Throwable t)
    {
      throw new ServiceException(t);
    }
    throw new ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
  }
  
  public Remote getPort(QName portName, Class serviceEndpointInterface)
    throws ServiceException
  {
    if (portName == null) {
      return getPort(serviceEndpointInterface);
    }
    String inputPortName = portName.getLocalPart();
    if ("BasicHttpBinding_IHIOSSCISService".equals(inputPortName)) {
      return getBasicHttpBinding_IHIOSSCISService();
    }
    Remote _stub = getPort(serviceEndpointInterface);
    ((Stub)_stub).setPortName(portName);
    return _stub;
  }
  
  public QName getServiceName()
  {
    return new QName("http://tempuri.org/", "HIOSSCISService");
  }
  
  private HashSet ports = null;
  
  public Iterator getPorts()
  {
    if (this.ports == null)
    {
      this.ports = new HashSet();
      this.ports.add(new QName("http://tempuri.org/", "BasicHttpBinding_IHIOSSCISService"));
    }
    return this.ports.iterator();
  }
  
  public void setEndpointAddress(String portName, String address)
    throws ServiceException
  {
    if ("BasicHttpBinding_IHIOSSCISService".equals(portName)) {
      setBasicHttpBinding_IHIOSSCISServiceEndpointAddress(address);
    } else {
      throw new ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
    }
  }
  
  public void setEndpointAddress(QName portName, String address)
    throws ServiceException
  {
    setEndpointAddress(portName.getLocalPart(), address);
  }
}
