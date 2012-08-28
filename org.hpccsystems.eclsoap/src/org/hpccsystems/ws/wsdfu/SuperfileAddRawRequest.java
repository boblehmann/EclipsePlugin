/*******************************************************************************
 * Copyright (c) 2011 HPCC Systems.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     HPCC Systems - initial API and implementation
 ******************************************************************************/
/**
 * SuperfileAddRawRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.hpccsystems.ws.wsdfu;

public class SuperfileAddRawRequest  implements java.io.Serializable {
	private java.lang.String superfile;

	private java.lang.String subfiles;

	private java.lang.String before;

	public SuperfileAddRawRequest() {
	}

	public SuperfileAddRawRequest(
			java.lang.String superfile,
			java.lang.String subfiles,
			java.lang.String before) {
		this.superfile = superfile;
		this.subfiles = subfiles;
		this.before = before;
	}


	/**
	 * Gets the superfile value for this SuperfileAddRawRequest.
	 * 
	 * @return superfile
	 */
	public java.lang.String getSuperfile() {
		return superfile;
	}


	/**
	 * Sets the superfile value for this SuperfileAddRawRequest.
	 * 
	 * @param superfile
	 */
	public void setSuperfile(java.lang.String superfile) {
		this.superfile = superfile;
	}


	/**
	 * Gets the subfiles value for this SuperfileAddRawRequest.
	 * 
	 * @return subfiles
	 */
	public java.lang.String getSubfiles() {
		return subfiles;
	}


	/**
	 * Sets the subfiles value for this SuperfileAddRawRequest.
	 * 
	 * @param subfiles
	 */
	public void setSubfiles(java.lang.String subfiles) {
		this.subfiles = subfiles;
	}


	/**
	 * Gets the before value for this SuperfileAddRawRequest.
	 * 
	 * @return before
	 */
	public java.lang.String getBefore() {
		return before;
	}


	/**
	 * Sets the before value for this SuperfileAddRawRequest.
	 * 
	 * @param before
	 */
	public void setBefore(java.lang.String before) {
		this.before = before;
	}

	private java.lang.Object __equalsCalc = null;
	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof SuperfileAddRawRequest)) {
			return false;
		}
		SuperfileAddRawRequest other = (SuperfileAddRawRequest) obj;
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && 
				((this.superfile==null && other.getSuperfile()==null) || 
						(this.superfile!=null &&
						this.superfile.equals(other.getSuperfile()))) &&
						((this.subfiles==null && other.getSubfiles()==null) || 
								(this.subfiles!=null &&
								this.subfiles.equals(other.getSubfiles()))) &&
								((this.before==null && other.getBefore()==null) || 
										(this.before!=null &&
										this.before.equals(other.getBefore())));
		__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;
	@Override
	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;
		if (getSuperfile() != null) {
			_hashCode += getSuperfile().hashCode();
		}
		if (getSubfiles() != null) {
			_hashCode += getSubfiles().hashCode();
		}
		if (getBefore() != null) {
			_hashCode += getBefore().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc =
	new org.apache.axis.description.TypeDesc(SuperfileAddRawRequest.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("urn:hpccsystems:ws:wsdfu", ">SuperfileAddRawRequest"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("superfile");
		elemField.setXmlName(new javax.xml.namespace.QName("urn:hpccsystems:ws:wsdfu", "superfile"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("subfiles");
		elemField.setXmlName(new javax.xml.namespace.QName("urn:hpccsystems:ws:wsdfu", "subfiles"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("before");
		elemField.setXmlName(new javax.xml.namespace.QName("urn:hpccsystems:ws:wsdfu", "before"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc() {
		return typeDesc;
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
			java.lang.String mechType, 
			java.lang.Class _javaType,  
			javax.xml.namespace.QName _xmlType) {
		return 
				new  org.apache.axis.encoding.ser.BeanSerializer(
						_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
			java.lang.String mechType, 
			java.lang.Class _javaType,  
			javax.xml.namespace.QName _xmlType) {
		return 
				new  org.apache.axis.encoding.ser.BeanDeserializer(
						_javaType, _xmlType, typeDesc);
	}

}
