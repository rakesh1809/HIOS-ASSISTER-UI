package com.cgifederal.www;

import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class ValidEntityResponse
  implements Serializable
{
  private boolean isValid;
  
  public ValidEntityResponse(boolean isValid)
  {
    this.isValid = isValid;
  }
  
  public boolean isIsValid()
  {
    return this.isValid;
  }
  
  public void setIsValid(boolean isValid)
  {
    this.isValid = isValid;
  }
  
  private Object __equalsCalc = null;
  
  public synchronized boolean equals(Object obj)
  {
    if (!(obj instanceof ValidEntityResponse)) {
      return false;
    }
    ValidEntityResponse other = (ValidEntityResponse)obj;
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
    
    boolean _equals = this.isValid == other.isIsValid();
    
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
    _hashCode += (isIsValid() ? Boolean.TRUE : Boolean.FALSE).hashCode();
    this.__hashCodeCalc = false;
    return _hashCode;
  }
  
  private static TypeDesc typeDesc = new TypeDesc(ValidEntityResponse.class, true);
  
  static
  {
    typeDesc.setXmlType(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "ValidEntityResponse"));
    ElementDesc elemField = new ElementDesc();
    elemField.setFieldName("isValid");
    elemField.setXmlName(new QName("http://schemas.datacontract.org/2004/07/HIOSCommonObjects", "IsValid"));
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
  
  public ValidEntityResponse() {}
}
