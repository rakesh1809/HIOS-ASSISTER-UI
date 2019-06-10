package com.cgifederal.www;

import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class IssuerProductRequest
  implements Serializable
{
  private String issuer;
  private String product;
  
  public IssuerProductRequest(String issuer, String product)
  {
    this.issuer = issuer;
    this.product = product;
  }
  
  public String getIssuer()
  {
    return this.issuer;
  }
  
  public void setIssuer(String issuer)
  {
    this.issuer = issuer;
  }
  
  public String getProduct()
  {
    return this.product;
  }
  
  public void setProduct(String product)
  {
    this.product = product;
  }
  
  private Object __equalsCalc = null;
  
  public synchronized boolean equals(Object obj)
  {
    if (!(obj instanceof IssuerProductRequest)) {
      return false;
    }
    IssuerProductRequest other = (IssuerProductRequest)obj;
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (this.__equalsCalc != null) {
      return this.__equalsCalc == obj;
    }
    this.__equalsCalc = obj;
    
    boolean _equals = ((this.issuer == null) && (other.getIssuer() == null)) || ((this.issuer != null) && (this.issuer.equals(other.getIssuer())) && (((this.product == null) && (other.getProduct() == null)) || ((this.product != null) && (this.product.equals(other.getProduct())))));
    
    this.__equalsCalc = null;
    return _equals;
  }
  
  private boolean __hashCodeCalc = false;
  
  public synchronized int hashCode()
  {
    if (this.__hashCodeCalc) {
      return 0;
    }
    this.__hashCodeCalc = true;
    int _hashCode = 1;
    if (getIssuer() != null) {
      _hashCode += getIssuer().hashCode();
    }
    if (getProduct() != null) {
      _hashCode += getProduct().hashCode();
    }
    this.__hashCodeCalc = false;
    return _hashCode;
  }
  
  private static TypeDesc typeDesc = new TypeDesc(IssuerProductRequest.class, true);
  
  static
  {
    typeDesc.setXmlType(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerProductRequest"));
    ElementDesc elemField = new ElementDesc();
    elemField.setFieldName("issuer");
    elemField.setXmlName(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "Issuer"));
    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    elemField.setMinOccurs(0);
    elemField.setNillable(true);
    typeDesc.addFieldDesc(elemField);
    elemField = new ElementDesc();
    elemField.setFieldName("product");
    elemField.setXmlName(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "Product"));
    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    elemField.setMinOccurs(0);
    elemField.setNillable(true);
    typeDesc.addFieldDesc(elemField);
  }
  
  public static TypeDesc getTypeDesc()
  {
    return typeDesc;
  }
  
  public static Serializer getSerializer(String mechType, Class _javaType, QName _xmlType)
  {
    return new BeanSerializer(_javaType, _xmlType, typeDesc);
  }
  
  public static Deserializer getDeserializer(String mechType, Class _javaType, QName _xmlType)
  {
    return new BeanDeserializer(_javaType, _xmlType, typeDesc);
  }
  
  public IssuerProductRequest() {}
}
