package com.cgifederal.www;

import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class IssuerProductResponse
  implements Serializable
{
  private String issuer;
  private boolean issuerProductValid;
  private boolean issuerValid;
  private String product;
  private boolean productValid;
  
  public IssuerProductResponse(String issuer, boolean issuerProductValid, boolean issuerValid, String product, boolean productValid)
  {
    this.issuer = issuer;
    this.issuerProductValid = issuerProductValid;
    this.issuerValid = issuerValid;
    this.product = product;
    this.productValid = productValid;
  }
  
  public String getIssuer()
  {
    return this.issuer;
  }
  
  public void setIssuer(String issuer)
  {
    this.issuer = issuer;
  }
  
  public boolean isIssuerProductValid()
  {
    return this.issuerProductValid;
  }
  
  public void setIssuerProductValid(boolean issuerProductValid)
  {
    this.issuerProductValid = issuerProductValid;
  }
  
  public boolean isIssuerValid()
  {
    return this.issuerValid;
  }
  
  public void setIssuerValid(boolean issuerValid)
  {
    this.issuerValid = issuerValid;
  }
  
  public String getProduct()
  {
    return this.product;
  }
  
  public void setProduct(String product)
  {
    this.product = product;
  }
  
  public boolean isProductValid()
  {
    return this.productValid;
  }
  
  public void setProductValid(boolean productValid)
  {
    this.productValid = productValid;
  }
  
  private Object __equalsCalc = null;
  
  public synchronized boolean equals(Object obj)
  {
    if (!(obj instanceof IssuerProductResponse)) {
      return false;
    }
    IssuerProductResponse other = (IssuerProductResponse)obj;
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
    
    boolean _equals = ((this.issuer == null) && (other.getIssuer() == null)) || ((this.issuer != null) && (this.issuer.equals(other.getIssuer())) && (this.issuerProductValid == other.isIssuerProductValid()) && (this.issuerValid == other.isIssuerValid()) && (((this.product == null) && (other.getProduct() == null)) || ((this.product != null) && (this.product.equals(other.getProduct())) && (this.productValid == other.isProductValid()))));
    
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
    _hashCode += (isIssuerProductValid() ? Boolean.TRUE : Boolean.FALSE).hashCode();
    _hashCode += (isIssuerValid() ? Boolean.TRUE : Boolean.FALSE).hashCode();
    if (getProduct() != null) {
      _hashCode += getProduct().hashCode();
    }
    _hashCode += (isProductValid() ? Boolean.TRUE : Boolean.FALSE).hashCode();
    this.__hashCodeCalc = false;
    return _hashCode;
  }
  
  private static TypeDesc typeDesc = new TypeDesc(IssuerProductResponse.class, true);
  
  static
  {
    typeDesc.setXmlType(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerProductResponse"));
    ElementDesc elemField = new ElementDesc();
    elemField.setFieldName("issuer");
    elemField.setXmlName(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "Issuer"));
    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    elemField.setMinOccurs(0);
    elemField.setNillable(true);
    typeDesc.addFieldDesc(elemField);
    elemField = new ElementDesc();
    elemField.setFieldName("issuerProductValid");
    elemField.setXmlName(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerProductValid"));
    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "boolean"));
    elemField.setNillable(false);
    typeDesc.addFieldDesc(elemField);
    elemField = new ElementDesc();
    elemField.setFieldName("issuerValid");
    elemField.setXmlName(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IssuerValid"));
    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "boolean"));
    elemField.setNillable(false);
    typeDesc.addFieldDesc(elemField);
    elemField = new ElementDesc();
    elemField.setFieldName("product");
    elemField.setXmlName(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "Product"));
    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    elemField.setMinOccurs(0);
    elemField.setNillable(true);
    typeDesc.addFieldDesc(elemField);
    elemField = new ElementDesc();
    elemField.setFieldName("productValid");
    elemField.setXmlName(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ProductValid"));
    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "boolean"));
    elemField.setNillable(false);
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
  
  public IssuerProductResponse() {}
}
