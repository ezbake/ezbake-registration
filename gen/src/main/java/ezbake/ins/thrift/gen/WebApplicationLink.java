/*   Copyright (C) 2013-2014 Computer Sciences Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package ezbake.ins.thrift.gen;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebApplicationLink implements org.apache.thrift.TBase<WebApplicationLink, WebApplicationLink._Fields>, java.io.Serializable, Cloneable, Comparable<WebApplicationLink> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("WebApplicationLink");

  private static final org.apache.thrift.protocol.TField APP_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("appName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField WEB_URL_FIELD_DESC = new org.apache.thrift.protocol.TField("webUrl", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField INCLUDE_PREFIX_FIELD_DESC = new org.apache.thrift.protocol.TField("includePrefix", org.apache.thrift.protocol.TType.BOOL, (short)3);
  private static final org.apache.thrift.protocol.TField REQUIRED_GROUP_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("requiredGroupName", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new WebApplicationLinkStandardSchemeFactory());
    schemes.put(TupleScheme.class, new WebApplicationLinkTupleSchemeFactory());
  }

  public String appName; // optional
  public String webUrl; // optional
  public boolean includePrefix; // optional
  public String requiredGroupName; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    APP_NAME((short)1, "appName"),
    WEB_URL((short)2, "webUrl"),
    INCLUDE_PREFIX((short)3, "includePrefix"),
    REQUIRED_GROUP_NAME((short)4, "requiredGroupName");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // APP_NAME
          return APP_NAME;
        case 2: // WEB_URL
          return WEB_URL;
        case 3: // INCLUDE_PREFIX
          return INCLUDE_PREFIX;
        case 4: // REQUIRED_GROUP_NAME
          return REQUIRED_GROUP_NAME;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __INCLUDEPREFIX_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.APP_NAME,_Fields.WEB_URL,_Fields.INCLUDE_PREFIX,_Fields.REQUIRED_GROUP_NAME};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.APP_NAME, new org.apache.thrift.meta_data.FieldMetaData("appName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.WEB_URL, new org.apache.thrift.meta_data.FieldMetaData("webUrl", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.INCLUDE_PREFIX, new org.apache.thrift.meta_data.FieldMetaData("includePrefix", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.REQUIRED_GROUP_NAME, new org.apache.thrift.meta_data.FieldMetaData("requiredGroupName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(WebApplicationLink.class, metaDataMap);
  }

  public WebApplicationLink() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public WebApplicationLink(WebApplicationLink other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetAppName()) {
      this.appName = other.appName;
    }
    if (other.isSetWebUrl()) {
      this.webUrl = other.webUrl;
    }
    this.includePrefix = other.includePrefix;
    if (other.isSetRequiredGroupName()) {
      this.requiredGroupName = other.requiredGroupName;
    }
  }

  public WebApplicationLink deepCopy() {
    return new WebApplicationLink(this);
  }

  @Override
  public void clear() {
    this.appName = null;
    this.webUrl = null;
    setIncludePrefixIsSet(false);
    this.includePrefix = false;
    this.requiredGroupName = null;
  }

  public String getAppName() {
    return this.appName;
  }

  public WebApplicationLink setAppName(String appName) {
    this.appName = appName;
    return this;
  }

  public void unsetAppName() {
    this.appName = null;
  }

  /** Returns true if field appName is set (has been assigned a value) and false otherwise */
  public boolean isSetAppName() {
    return this.appName != null;
  }

  public void setAppNameIsSet(boolean value) {
    if (!value) {
      this.appName = null;
    }
  }

  public String getWebUrl() {
    return this.webUrl;
  }

  public WebApplicationLink setWebUrl(String webUrl) {
    this.webUrl = webUrl;
    return this;
  }

  public void unsetWebUrl() {
    this.webUrl = null;
  }

  /** Returns true if field webUrl is set (has been assigned a value) and false otherwise */
  public boolean isSetWebUrl() {
    return this.webUrl != null;
  }

  public void setWebUrlIsSet(boolean value) {
    if (!value) {
      this.webUrl = null;
    }
  }

  public boolean isIncludePrefix() {
    return this.includePrefix;
  }

  public WebApplicationLink setIncludePrefix(boolean includePrefix) {
    this.includePrefix = includePrefix;
    setIncludePrefixIsSet(true);
    return this;
  }

  public void unsetIncludePrefix() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __INCLUDEPREFIX_ISSET_ID);
  }

  /** Returns true if field includePrefix is set (has been assigned a value) and false otherwise */
  public boolean isSetIncludePrefix() {
    return EncodingUtils.testBit(__isset_bitfield, __INCLUDEPREFIX_ISSET_ID);
  }

  public void setIncludePrefixIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __INCLUDEPREFIX_ISSET_ID, value);
  }

  public String getRequiredGroupName() {
    return this.requiredGroupName;
  }

  public WebApplicationLink setRequiredGroupName(String requiredGroupName) {
    this.requiredGroupName = requiredGroupName;
    return this;
  }

  public void unsetRequiredGroupName() {
    this.requiredGroupName = null;
  }

  /** Returns true if field requiredGroupName is set (has been assigned a value) and false otherwise */
  public boolean isSetRequiredGroupName() {
    return this.requiredGroupName != null;
  }

  public void setRequiredGroupNameIsSet(boolean value) {
    if (!value) {
      this.requiredGroupName = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case APP_NAME:
      if (value == null) {
        unsetAppName();
      } else {
        setAppName((String)value);
      }
      break;

    case WEB_URL:
      if (value == null) {
        unsetWebUrl();
      } else {
        setWebUrl((String)value);
      }
      break;

    case INCLUDE_PREFIX:
      if (value == null) {
        unsetIncludePrefix();
      } else {
        setIncludePrefix((Boolean)value);
      }
      break;

    case REQUIRED_GROUP_NAME:
      if (value == null) {
        unsetRequiredGroupName();
      } else {
        setRequiredGroupName((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case APP_NAME:
      return getAppName();

    case WEB_URL:
      return getWebUrl();

    case INCLUDE_PREFIX:
      return Boolean.valueOf(isIncludePrefix());

    case REQUIRED_GROUP_NAME:
      return getRequiredGroupName();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case APP_NAME:
      return isSetAppName();
    case WEB_URL:
      return isSetWebUrl();
    case INCLUDE_PREFIX:
      return isSetIncludePrefix();
    case REQUIRED_GROUP_NAME:
      return isSetRequiredGroupName();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof WebApplicationLink)
      return this.equals((WebApplicationLink)that);
    return false;
  }

  public boolean equals(WebApplicationLink that) {
    if (that == null)
      return false;

    boolean this_present_appName = true && this.isSetAppName();
    boolean that_present_appName = true && that.isSetAppName();
    if (this_present_appName || that_present_appName) {
      if (!(this_present_appName && that_present_appName))
        return false;
      if (!this.appName.equals(that.appName))
        return false;
    }

    boolean this_present_webUrl = true && this.isSetWebUrl();
    boolean that_present_webUrl = true && that.isSetWebUrl();
    if (this_present_webUrl || that_present_webUrl) {
      if (!(this_present_webUrl && that_present_webUrl))
        return false;
      if (!this.webUrl.equals(that.webUrl))
        return false;
    }

    boolean this_present_includePrefix = true && this.isSetIncludePrefix();
    boolean that_present_includePrefix = true && that.isSetIncludePrefix();
    if (this_present_includePrefix || that_present_includePrefix) {
      if (!(this_present_includePrefix && that_present_includePrefix))
        return false;
      if (this.includePrefix != that.includePrefix)
        return false;
    }

    boolean this_present_requiredGroupName = true && this.isSetRequiredGroupName();
    boolean that_present_requiredGroupName = true && that.isSetRequiredGroupName();
    if (this_present_requiredGroupName || that_present_requiredGroupName) {
      if (!(this_present_requiredGroupName && that_present_requiredGroupName))
        return false;
      if (!this.requiredGroupName.equals(that.requiredGroupName))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(WebApplicationLink other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetAppName()).compareTo(other.isSetAppName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAppName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.appName, other.appName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetWebUrl()).compareTo(other.isSetWebUrl());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWebUrl()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.webUrl, other.webUrl);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIncludePrefix()).compareTo(other.isSetIncludePrefix());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIncludePrefix()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.includePrefix, other.includePrefix);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRequiredGroupName()).compareTo(other.isSetRequiredGroupName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRequiredGroupName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.requiredGroupName, other.requiredGroupName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("WebApplicationLink(");
    boolean first = true;

    if (isSetAppName()) {
      sb.append("appName:");
      if (this.appName == null) {
        sb.append("null");
      } else {
        sb.append(this.appName);
      }
      first = false;
    }
    if (isSetWebUrl()) {
      if (!first) sb.append(", ");
      sb.append("webUrl:");
      if (this.webUrl == null) {
        sb.append("null");
      } else {
        sb.append(this.webUrl);
      }
      first = false;
    }
    if (isSetIncludePrefix()) {
      if (!first) sb.append(", ");
      sb.append("includePrefix:");
      sb.append(this.includePrefix);
      first = false;
    }
    if (isSetRequiredGroupName()) {
      if (!first) sb.append(", ");
      sb.append("requiredGroupName:");
      if (this.requiredGroupName == null) {
        sb.append("null");
      } else {
        sb.append(this.requiredGroupName);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class WebApplicationLinkStandardSchemeFactory implements SchemeFactory {
    public WebApplicationLinkStandardScheme getScheme() {
      return new WebApplicationLinkStandardScheme();
    }
  }

  private static class WebApplicationLinkStandardScheme extends StandardScheme<WebApplicationLink> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, WebApplicationLink struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // APP_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.appName = iprot.readString();
              struct.setAppNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // WEB_URL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.webUrl = iprot.readString();
              struct.setWebUrlIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // INCLUDE_PREFIX
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.includePrefix = iprot.readBool();
              struct.setIncludePrefixIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // REQUIRED_GROUP_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.requiredGroupName = iprot.readString();
              struct.setRequiredGroupNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, WebApplicationLink struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.appName != null) {
        if (struct.isSetAppName()) {
          oprot.writeFieldBegin(APP_NAME_FIELD_DESC);
          oprot.writeString(struct.appName);
          oprot.writeFieldEnd();
        }
      }
      if (struct.webUrl != null) {
        if (struct.isSetWebUrl()) {
          oprot.writeFieldBegin(WEB_URL_FIELD_DESC);
          oprot.writeString(struct.webUrl);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetIncludePrefix()) {
        oprot.writeFieldBegin(INCLUDE_PREFIX_FIELD_DESC);
        oprot.writeBool(struct.includePrefix);
        oprot.writeFieldEnd();
      }
      if (struct.requiredGroupName != null) {
        if (struct.isSetRequiredGroupName()) {
          oprot.writeFieldBegin(REQUIRED_GROUP_NAME_FIELD_DESC);
          oprot.writeString(struct.requiredGroupName);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class WebApplicationLinkTupleSchemeFactory implements SchemeFactory {
    public WebApplicationLinkTupleScheme getScheme() {
      return new WebApplicationLinkTupleScheme();
    }
  }

  private static class WebApplicationLinkTupleScheme extends TupleScheme<WebApplicationLink> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, WebApplicationLink struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetAppName()) {
        optionals.set(0);
      }
      if (struct.isSetWebUrl()) {
        optionals.set(1);
      }
      if (struct.isSetIncludePrefix()) {
        optionals.set(2);
      }
      if (struct.isSetRequiredGroupName()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetAppName()) {
        oprot.writeString(struct.appName);
      }
      if (struct.isSetWebUrl()) {
        oprot.writeString(struct.webUrl);
      }
      if (struct.isSetIncludePrefix()) {
        oprot.writeBool(struct.includePrefix);
      }
      if (struct.isSetRequiredGroupName()) {
        oprot.writeString(struct.requiredGroupName);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, WebApplicationLink struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.appName = iprot.readString();
        struct.setAppNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.webUrl = iprot.readString();
        struct.setWebUrlIsSet(true);
      }
      if (incoming.get(2)) {
        struct.includePrefix = iprot.readBool();
        struct.setIncludePrefixIsSet(true);
      }
      if (incoming.get(3)) {
        struct.requiredGroupName = iprot.readString();
        struct.setRequiredGroupNameIsSet(true);
      }
    }
  }

}

