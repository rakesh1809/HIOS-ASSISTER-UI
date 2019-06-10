package com.cgifederal.www;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Vector;
import javax.xml.namespace.QName;
import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;
import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.DeserializerFactory;
import org.apache.axis.encoding.ser.ArrayDeserializerFactory;
import org.apache.axis.encoding.ser.ArraySerializerFactory;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.encoding.ser.EnumDeserializerFactory;
import org.apache.axis.encoding.ser.EnumSerializerFactory;
import org.apache.axis.encoding.ser.SimpleDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleListDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleListSerializerFactory;
import org.apache.axis.encoding.ser.SimpleSerializerFactory;

public class BasicHttpBinding_IHIOSSCISServiceStub
  extends Stub
  implements IHIOSSCISService
{
  private Vector cachedSerClasses = new Vector();
  private Vector cachedSerQNames = new Vector();
  private Vector cachedSerFactories = new Vector();
  private Vector cachedDeserFactories = new Vector();
  static OperationDesc[] _operations = new OperationDesc[6];
  
  static
  {
    _initOperationDesc1();
  }
  
  private static void _initOperationDesc1()
  {
    OperationDesc oper = new OperationDesc();
    oper.setName("IsValidIssuer");
    ParameterDesc param = new ParameterDesc(new QName("http://tempuri.org/", "issuerCode"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setOmittable(true);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ValidEntityResponse"));
    oper.setReturnClass(ValidEntityResponse.class);
    oper.setReturnQName(new QName("http://tempuri.org/", "IsValidIssuerResult"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[0] = oper;
    
    oper = new OperationDesc();
    oper.setName("IsValidProduct");
    param = new ParameterDesc(new QName("http://tempuri.org/", "productCode"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setOmittable(true);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ValidEntityResponse"));
    oper.setReturnClass(ValidEntityResponse.class);
    oper.setReturnQName(new QName("http://tempuri.org/", "IsValidProductResult"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[1] = oper;
    
    oper = new OperationDesc();
    oper.setName("IsValidIssuerProduct");
    param = new ParameterDesc(new QName("http://tempuri.org/", "issuerCode"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setOmittable(true);
    param.setNillable(true);
    oper.addParameter(param);
    param = new ParameterDesc(new QName("http://tempuri.org/", "productCode"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    param.setOmittable(true);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ValidEntityResponse"));
    oper.setReturnClass(ValidEntityResponse.class);
    oper.setReturnQName(new QName("http://tempuri.org/", "IsValidIssuerProductResult"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[2] = oper;
    
    oper = new OperationDesc();
    oper.setName("ValidIssuers");
    param = new ParameterDesc(new QName("http://tempuri.org/", "issuers"), (byte)1, new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfstring"), String[].class, false, false);
    param.setItemQName(new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
    param.setOmittable(true);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ArrayOfIssuerResponse"));
    oper.setReturnClass(IssuerResponse[].class);
    oper.setReturnQName(new QName("http://tempuri.org/", "ValidIssuersResult"));
    param = oper.getReturnParamDesc();
    param.setItemQName(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerResponse"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[3] = oper;
    
    oper = new OperationDesc();
    oper.setName("ValidProducts");
    param = new ParameterDesc(new QName("http://tempuri.org/", "products"), (byte)1, new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfstring"), String[].class, false, false);
    param.setItemQName(new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
    param.setOmittable(true);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ArrayOfProductResponse"));
    oper.setReturnClass(ProductResponse[].class);
    oper.setReturnQName(new QName("http://tempuri.org/", "ValidProductsResult"));
    param = oper.getReturnParamDesc();
    param.setItemQName(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ProductResponse"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[4] = oper;
    
    oper = new OperationDesc();
    oper.setName("ValidIssuersProducts");
    param = new ParameterDesc(new QName("http://tempuri.org/", "li"), (byte)1, new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ArrayOfIssuerProductRequest"), IssuerProductRequest[].class, false, false);
    param.setItemQName(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerProductRequest"));
    param.setOmittable(true);
    param.setNillable(true);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ArrayOfIssuerProductResponse"));
    oper.setReturnClass(IssuerProductResponse[].class);
    oper.setReturnQName(new QName("http://tempuri.org/", "ValidIssuersProductsResult"));
    param = oper.getReturnParamDesc();
    param.setItemQName(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerProductResponse"));
    oper.setStyle(Style.WRAPPED);
    oper.setUse(Use.LITERAL);
    _operations[5] = oper;
  }
  
  public BasicHttpBinding_IHIOSSCISServiceStub()
    throws AxisFault
  {
    this(null);
  }
  
  public BasicHttpBinding_IHIOSSCISServiceStub(URL endpointURL, javax.xml.rpc.Service service)
    throws AxisFault
  {
    this(service);
    this.cachedEndpoint = endpointURL;
  }
  
  public BasicHttpBinding_IHIOSSCISServiceStub(javax.xml.rpc.Service service)
    throws AxisFault
  {
    if (service == null) {
      this.service = new org.apache.axis.client.Service();
    } else {
      this.service = service;
    }
    ((org.apache.axis.client.Service)this.service).setTypeMappingVersion("1.2");
    
    Class beansf = BeanSerializerFactory.class;
    Class beandf = BeanDeserializerFactory.class;
    Class enumsf = EnumSerializerFactory.class;
    Class enumdf = EnumDeserializerFactory.class;
    Class arraysf = ArraySerializerFactory.class;
    Class arraydf = ArrayDeserializerFactory.class;
    Class simplesf = SimpleSerializerFactory.class;
    Class simpledf = SimpleDeserializerFactory.class;
    Class simplelistsf = SimpleListSerializerFactory.class;
    Class simplelistdf = SimpleListDeserializerFactory.class;
    QName qName = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ArrayOfIssuerProductRequest");
    this.cachedSerQNames.add(qName);
    Class cls = IssuerProductRequest[].class;
    this.cachedSerClasses.add(cls);
    qName = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerProductRequest");
    QName qName2 = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerProductRequest");
    this.cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
    this.cachedDeserFactories.add(new ArrayDeserializerFactory());
    
    qName = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ArrayOfIssuerProductResponse");
    this.cachedSerQNames.add(qName);
    cls = IssuerProductResponse[].class;
    this.cachedSerClasses.add(cls);
    qName = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerProductResponse");
    qName2 = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerProductResponse");
    this.cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
    this.cachedDeserFactories.add(new ArrayDeserializerFactory());
    
    qName = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ArrayOfIssuerResponse");
    this.cachedSerQNames.add(qName);
    cls = IssuerResponse[].class;
    this.cachedSerClasses.add(cls);
    qName = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerResponse");
    qName2 = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerResponse");
    this.cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
    this.cachedDeserFactories.add(new ArrayDeserializerFactory());
    
    qName = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ArrayOfProductResponse");
    this.cachedSerQNames.add(qName);
    cls = ProductResponse[].class;
    this.cachedSerClasses.add(cls);
    qName = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ProductResponse");
    qName2 = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ProductResponse");
    this.cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
    this.cachedDeserFactories.add(new ArrayDeserializerFactory());
    
    qName = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerProductRequest");
    this.cachedSerQNames.add(qName);
    cls = IssuerProductRequest.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    
    qName = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerProductResponse");
    this.cachedSerQNames.add(qName);
    cls = IssuerProductResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    
    qName = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerResponse");
    this.cachedSerQNames.add(qName);
    cls = IssuerResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    
    qName = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ProductResponse");
    this.cachedSerQNames.add(qName);
    cls = ProductResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    
    qName = new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ValidEntityResponse");
    this.cachedSerQNames.add(qName);
    cls = ValidEntityResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);
    
    qName = new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfstring");
    this.cachedSerQNames.add(qName);
    cls = String[].class;
    this.cachedSerClasses.add(cls);
    qName = new QName("http://www.w3.org/2001/XMLSchema", "string");
    qName2 = new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string");
    this.cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
    this.cachedDeserFactories.add(new ArrayDeserializerFactory());
  }
  
  protected Call createCall()
    throws RemoteException
  {
    try
    {
      Call _call = super._createCall();
      if (this.maintainSessionSet) {
        _call.setMaintainSession(this.maintainSession);
      }
      if (this.cachedUsername != null) {
        _call.setUsername(this.cachedUsername);
      }
      if (this.cachedPassword != null) {
        _call.setPassword(this.cachedPassword);
      }
      if (this.cachedEndpoint != null) {
        _call.setTargetEndpointAddress(this.cachedEndpoint);
      }
      if (this.cachedTimeout != null) {
        _call.setTimeout(this.cachedTimeout);
      }
      if (this.cachedPortName != null) {
        _call.setPortName(this.cachedPortName);
      }
      Enumeration keys = this.cachedProperties.keys();
      while (keys.hasMoreElements())
      {
        String key = (String)keys.nextElement();
        _call.setProperty(key, this.cachedProperties.get(key));
      }
      synchronized (this)
      {
        if (firstCall())
        {
          _call.setEncodingStyle(null);
          for (int i = 0; i < this.cachedSerFactories.size(); i++)
          {
            Class cls = (Class)this.cachedSerClasses.get(i);
            QName qName = (QName)this.cachedSerQNames.get(i);
            
            Object x = this.cachedSerFactories.get(i);
            if ((x instanceof Class))
            {
              Class sf = (Class)this.cachedSerFactories.get(i);
              
              Class df = (Class)this.cachedDeserFactories.get(i);
              
              _call.registerTypeMapping(cls, qName, sf, df, false);
            }
            else if ((x instanceof javax.xml.rpc.encoding.SerializerFactory))
            {
              org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)this.cachedSerFactories.get(i);
              
              DeserializerFactory df = (DeserializerFactory)this.cachedDeserFactories.get(i);
              
              _call.registerTypeMapping(cls, qName, sf, df, false);
            }
          }
        }
      }
      return _call;
    }
    catch (Throwable _t)
    {
      throw new AxisFault("Failure trying to get the Call object", _t);
    }
  }

@Override
public ValidEntityResponse isValidIssuer(String paramString) throws RemoteException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public ValidEntityResponse isValidProduct(String paramString) throws RemoteException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public ValidEntityResponse isValidIssuerProduct(String paramString1, String paramString2) throws RemoteException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public IssuerResponse[] validIssuers(String[] paramArrayOfString) throws RemoteException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public ProductResponse[] validProducts(String[] paramArrayOfString) throws RemoteException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public IssuerProductResponse[] validIssuersProducts(IssuerProductRequest[] paramArrayOfIssuerProductRequest)
		throws RemoteException {
	// TODO Auto-generated method stub
	return null;
}
}
