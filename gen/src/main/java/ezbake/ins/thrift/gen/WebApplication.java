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

public class WebApplication implements org.apache.thrift.TBase<WebApplication, WebApplication._Fields>, java.io.Serializable, Cloneable, Comparable<WebApplication> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("WebApplication");

  private static final org.apache.thrift.protocol.TField URN_MAP_FIELD_DESC = new org.apache.thrift.protocol.TField("urnMap", org.apache.thrift.protocol.TType.MAP, (short)1);
  private static final org.apache.thrift.protocol.TField IS_CHLOE_ENABLED_FIELD_DESC = new org.apache.thrift.protocol.TField("isChloeEnabled", org.apache.thrift.protocol.TType.BOOL, (short)2);
  private static final org.apache.thrift.protocol.TField CHLOE_WEB_URL_FIELD_DESC = new org.apache.thrift.protocol.TField("chloeWebUrl", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField EXTERNAL_URI_FIELD_DESC = new org.apache.thrift.protocol.TField("externalUri", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField REQUIRED_GROUP_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("requiredGroupName", org.apache.thrift.protocol.TType.STRING, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new WebApplicationStandardSchemeFactory());
    schemes.put(TupleScheme.class, new WebApplicationTupleSchemeFactory());
  }

  public Map<String,WebApplicationLink> urnMap; // optional
  public boolean isChloeEnabled; // optional
  public String chloeWebUrl; // optional
  public String externalUri; // optional
  public String requiredGroupName; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    URN_MAP((short)1, "urnMap"),
    IS_CHLOE_ENABLED((short)2, "isChloeEnabled"),
    CHLOE_WEB_URL((short)3, "chloeWebUrl"),
    EXTERNAL_URI((short)4, "externalUri"),
    REQUIRED_GROUP_NAME((short)5, "requiredGroupName");

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
        case 1: // URN_MAP
          return URN_MAP;
        case 2: // IS_CHLOE_ENABLED
          return IS_CHLOE_ENABLED;
        case 3: // CHLOE_WEB_URL
          return CHLOE_WEB_URL;
        case 4: // EXTERNAL_URI
          return EXTERNAL_URI;
        case 5: // REQUIRED_GROUP_NAME
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
  private static final int __ISCHLOEENABLED_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.URN_MAP,_Fields.IS_CHLOE_ENABLED,_Fields.CHLOE_WEB_URL,_Fields.EXTERNAL_URI,_Fields.REQUIRED_GROUP_NAME};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.URN_MAP, new org.apache.thrift.meta_data.FieldMetaData("urnMap", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, WebApplicationLink.class))));
    tmpMap.put(_Fields.IS_CHLOE_ENABLED, new org.apache.thrift.meta_data.FieldMetaData("isChloeEnabled", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.CHLOE_WEB_URL, new org.apache.thrift.meta_data.FieldMetaData("chloeWebUrl", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.EXTERNAL_URI, new org.apache.thrift.meta_data.FieldMetaData("externalUri", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.REQUIRED_GROUP_NAME, new org.apache.thrift.meta_data.FieldMetaData("requiredGroupName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(WebApplication.class, metaDataMap);
  }

  public WebApplication() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public WebApplication(WebApplication other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetUrnMap()) {
      Map<String,WebApplicationLink> __this__urnMap = new HashMap<String,WebApplicationLink>(other.urnMap.size());
      for (Map.Entry<String, WebApplicationLink> other_element : other.urnMap.entrySet()) {

        String other_element_key = other_element.getKey();
        WebApplicationLink other_element_value = other_element.getValue();

        String __this__urnMap_copy_key = other_element_key;

        WebApplicationLink __this__urnMap_copy_value = new WebApplicationLink(other_element_value);

        __this__urnMap.put(__this__urnMap_copy_key, __this__urnMap_copy_value);
      }
      this.urnMap = __this__urnMap;
    }
    this.isChloeEnabled = other.isChloeEnabled;
    if (other.isSetChloeWebUrl()) {
      this.chloeWebUrl = other.chloeWebUrl;
    }
    if (other.isSetExternalUri()) {
      this.externalUri = other.externalUri;
    }
    if (other.isSetRequiredGroupName()) {
      this.requiredGroupName = other.requiredGroupName;
    }
  }

  public WebApplication deepCopy() {
    return new WebApplication(this);
  }

  @Override
  public void clear() {
    this.urnMap = null;
    setIsChloeEnabledIsSet(false);
    this.isChloeEnabled = false;
    this.chloeWebUrl = null;
    this.externalUri = null;
    this.requiredGroupName = null;
  }

  public int getUrnMapSize() {
    return (this.urnMap == null) ? 0 : this.urnMap.size();
  }

  public void putToUrnMap(String key, WebApplicationLink val) {
    if (this.urnMap == null) {
      this.urnMap = new HashMap<String,WebApplicationLink>();
    }
    this.urnMap.put(key, val);
  }

  public Map<String,WebApplicationLink> getUrnMap() {
    return this.urnMap;
  }

  public WebApplication setUrnMap(Map<String,WebApplicationLink> urnMap) {
    this.urnMap = urnMap;
    return this;
  }

  public void unsetUrnMap() {
    this.urnMap = null;
  }

  /** Returns true if field urnMap is set (has been assigned a value) and false otherwise */
  public boolean isSetUrnMap() {
    return this.urnMap != null;
  }

  public void setUrnMapIsSet(boolean value) {
    if (!value) {
      this.urnMap = null;
    }
  }

  public boolean isIsChloeEnabled() {
    return this.isChloeEnabled;
  }

  public WebApplication setIsChloeEnabled(boolean isChloeEnabled) {
    this.isChloeEnabled = isChloeEnabled;
    setIsChloeEnabledIsSet(true);
    return this;
  }

  public void unsetIsChloeEnabled() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ISCHLOEENABLED_ISSET_ID);
  }

  /** Returns true if field isChloeEnabled is set (has been assigned a value) and false otherwise */
  public boolean isSetIsChloeEnabled() {
    return EncodingUtils.testBit(__isset_bitfield, __ISCHLOEENABLED_ISSET_ID);
  }

  public void setIsChloeEnabledIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ISCHLOEENABLED_ISSET_ID, value);
  }

  public String getChloeWebUrl() {
    return this.chloeWebUrl;
  }

  public WebApplication setChloeWebUrl(String chloeWebUrl) {
    this.chloeWebUrl = chloeWebUrl;
    return this;
  }

  public void unsetChloeWebUrl() {
    this.chloeWebUrl = null;
  }

  /** Returns true if field chloeWebUrl is set (has been assigned a value) and false otherwise */
  public boolean isSetChloeWebUrl() {
    return this.chloeWebUrl != null;
  }

  public void setChloeWebUrlIsSet(boolean value) {
    if (!value) {
      this.chloeWebUrl = null;
    }
  }

  public String getExternalUri() {
    return this.externalUri;
  }

  public WebApplication setExternalUri(String externalUri) {
    this.externalUri = externalUri;
    return this;
  }

  public void unsetExternalUri() {
    this.externalUri = null;
  }

  /** Returns true if field externalUri is set (has been assigned a value) and false otherwise */
  public boolean isSetExternalUri() {
    return this.externalUri != null;
  }

  public void setExternalUriIsSet(boolean value) {
    if (!value) {
      this.externalUri = null;
    }
  }

  public String getRequiredGroupName() {
    return this.requiredGroupName;
  }

  public WebApplication setRequiredGroupName(String requiredGroupName) {
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
    case URN_MAP:
      if (value == null) {
        unsetUrnMap();
      } else {
        setUrnMap((Map<String,WebApplicationLink>)value);
      }
      break;

    case IS_CHLOE_ENABLED:
      if (value == null) {
        unsetIsChloeEnabled();
      } else {
        setIsChloeEnabled((Boolean)value);
      }
      break;

    case CHLOE_WEB_URL:
      if (value == null) {
        unsetChloeWebUrl();
      } else {
        setChloeWebUrl((String)value);
      }
      break;

    case EXTERNAL_URI:
      if (value == null) {
        unsetExternalUri();
      } else {
        setExternalUri((String)value);
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
    case URN_MAP:
      return getUrnMap();

    case IS_CHLOE_ENABLED:
      return Boolean.valueOf(isIsChloeEnabled());

    case CHLOE_WEB_URL:
      return getChloeWebUrl();

    case EXTERNAL_URI:
      return getExternalUri();

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
    case URN_MAP:
      return isSetUrnMap();
    case IS_CHLOE_ENABLED:
      return isSetIsChloeEnabled();
    case CHLOE_WEB_URL:
      return isSetChloeWebUrl();
    case EXTERNAL_URI:
      return isSetExternalUri();
    case REQUIRED_GROUP_NAME:
      return isSetRequiredGroupName();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof WebApplication)
      return this.equals((WebApplication)that);
    return false;
  }

  public boolean equals(WebApplication that) {
    if (that == null)
      return false;

    boolean this_present_urnMap = true && this.isSetUrnMap();
    boolean that_present_urnMap = true && that.isSetUrnMap();
    if (this_present_urnMap || that_present_urnMap) {
      if (!(this_present_urnMap && that_present_urnMap))
        return false;
      if (!this.urnMap.equals(that.urnMap))
        return false;
    }

    boolean this_present_isChloeEnabled = true && this.isSetIsChloeEnabled();
    boolean that_present_isChloeEnabled = true && that.isSetIsChloeEnabled();
    if (this_present_isChloeEnabled || that_present_isChloeEnabled) {
      if (!(this_present_isChloeEnabled && that_present_isChloeEnabled))
        return false;
      if (this.isChloeEnabled != that.isChloeEnabled)
        return false;
    }

    boolean this_present_chloeWebUrl = true && this.isSetChloeWebUrl();
    boolean that_present_chloeWebUrl = true && that.isSetChloeWebUrl();
    if (this_present_chloeWebUrl || that_present_chloeWebUrl) {
      if (!(this_present_chloeWebUrl && that_present_chloeWebUrl))
        return false;
      if (!this.chloeWebUrl.equals(that.chloeWebUrl))
        return false;
    }

    boolean this_present_externalUri = true && this.isSetExternalUri();
    boolean that_present_externalUri = true && that.isSetExternalUri();
    if (this_present_externalUri || that_present_externalUri) {
      if (!(this_present_externalUri && that_present_externalUri))
        return false;
      if (!this.externalUri.equals(that.externalUri))
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
  public int compareTo(WebApplication other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetUrnMap()).compareTo(other.isSetUrnMap());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUrnMap()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.urnMap, other.urnMap);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIsChloeEnabled()).compareTo(other.isSetIsChloeEnabled());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsChloeEnabled()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.isChloeEnabled, other.isChloeEnabled);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetChloeWebUrl()).compareTo(other.isSetChloeWebUrl());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetChloeWebUrl()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.chloeWebUrl, other.chloeWebUrl);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetExternalUri()).compareTo(other.isSetExternalUri());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExternalUri()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.externalUri, other.externalUri);
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
    StringBuilder sb = new StringBuilder("WebApplication(");
    boolean first = true;

    if (isSetUrnMap()) {
      sb.append("urnMap:");
      if (this.urnMap == null) {
        sb.append("null");
      } else {
        sb.append(this.urnMap);
      }
      first = false;
    }
    if (isSetIsChloeEnabled()) {
      if (!first) sb.append(", ");
      sb.append("isChloeEnabled:");
      sb.append(this.isChloeEnabled);
      first = false;
    }
    if (isSetChloeWebUrl()) {
      if (!first) sb.append(", ");
      sb.append("chloeWebUrl:");
      if (this.chloeWebUrl == null) {
        sb.append("null");
      } else {
        sb.append(this.chloeWebUrl);
      }
      first = false;
    }
    if (isSetExternalUri()) {
      if (!first) sb.append(", ");
      sb.append("externalUri:");
      if (this.externalUri == null) {
        sb.append("null");
      } else {
        sb.append(this.externalUri);
      }
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

  private static class WebApplicationStandardSchemeFactory implements SchemeFactory {
    public WebApplicationStandardScheme getScheme() {
      return new WebApplicationStandardScheme();
    }
  }

  private static class WebApplicationStandardScheme extends StandardScheme<WebApplication> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, WebApplication struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // URN_MAP
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map32 = iprot.readMapBegin();
                struct.urnMap = new HashMap<String,WebApplicationLink>(2*_map32.size);
                for (int _i33 = 0; _i33 < _map32.size; ++_i33)
                {
                  String _key34;
                  WebApplicationLink _val35;
                  _key34 = iprot.readString();
                  _val35 = new WebApplicationLink();
                  _val35.read(iprot);
                  struct.urnMap.put(_key34, _val35);
                }
                iprot.readMapEnd();
              }
              struct.setUrnMapIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // IS_CHLOE_ENABLED
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.isChloeEnabled = iprot.readBool();
              struct.setIsChloeEnabledIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CHLOE_WEB_URL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.chloeWebUrl = iprot.readString();
              struct.setChloeWebUrlIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // EXTERNAL_URI
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.externalUri = iprot.readString();
              struct.setExternalUriIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // REQUIRED_GROUP_NAME
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, WebApplication struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.urnMap != null) {
        if (struct.isSetUrnMap()) {
          oprot.writeFieldBegin(URN_MAP_FIELD_DESC);
          {
            oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRUCT, struct.urnMap.size()));
            for (Map.Entry<String, WebApplicationLink> _iter36 : struct.urnMap.entrySet())
            {
              oprot.writeString(_iter36.getKey());
              _iter36.getValue().write(oprot);
            }
            oprot.writeMapEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetIsChloeEnabled()) {
        oprot.writeFieldBegin(IS_CHLOE_ENABLED_FIELD_DESC);
        oprot.writeBool(struct.isChloeEnabled);
        oprot.writeFieldEnd();
      }
      if (struct.chloeWebUrl != null) {
        if (struct.isSetChloeWebUrl()) {
          oprot.writeFieldBegin(CHLOE_WEB_URL_FIELD_DESC);
          oprot.writeString(struct.chloeWebUrl);
          oprot.writeFieldEnd();
        }
      }
      if (struct.externalUri != null) {
        if (struct.isSetExternalUri()) {
          oprot.writeFieldBegin(EXTERNAL_URI_FIELD_DESC);
          oprot.writeString(struct.externalUri);
          oprot.writeFieldEnd();
        }
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

  private static class WebApplicationTupleSchemeFactory implements SchemeFactory {
    public WebApplicationTupleScheme getScheme() {
      return new WebApplicationTupleScheme();
    }
  }

  private static class WebApplicationTupleScheme extends TupleScheme<WebApplication> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, WebApplication struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetUrnMap()) {
        optionals.set(0);
      }
      if (struct.isSetIsChloeEnabled()) {
        optionals.set(1);
      }
      if (struct.isSetChloeWebUrl()) {
        optionals.set(2);
      }
      if (struct.isSetExternalUri()) {
        optionals.set(3);
      }
      if (struct.isSetRequiredGroupName()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetUrnMap()) {
        {
          oprot.writeI32(struct.urnMap.size());
          for (Map.Entry<String, WebApplicationLink> _iter37 : struct.urnMap.entrySet())
          {
            oprot.writeString(_iter37.getKey());
            _iter37.getValue().write(oprot);
          }
        }
      }
      if (struct.isSetIsChloeEnabled()) {
        oprot.writeBool(struct.isChloeEnabled);
      }
      if (struct.isSetChloeWebUrl()) {
        oprot.writeString(struct.chloeWebUrl);
      }
      if (struct.isSetExternalUri()) {
        oprot.writeString(struct.externalUri);
      }
      if (struct.isSetRequiredGroupName()) {
        oprot.writeString(struct.requiredGroupName);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, WebApplication struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TMap _map38 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.urnMap = new HashMap<String,WebApplicationLink>(2*_map38.size);
          for (int _i39 = 0; _i39 < _map38.size; ++_i39)
          {
            String _key40;
            WebApplicationLink _val41;
            _key40 = iprot.readString();
            _val41 = new WebApplicationLink();
            _val41.read(iprot);
            struct.urnMap.put(_key40, _val41);
          }
        }
        struct.setUrnMapIsSet(true);
      }
      if (incoming.get(1)) {
        struct.isChloeEnabled = iprot.readBool();
        struct.setIsChloeEnabledIsSet(true);
      }
      if (incoming.get(2)) {
        struct.chloeWebUrl = iprot.readString();
        struct.setChloeWebUrlIsSet(true);
      }
      if (incoming.get(3)) {
        struct.externalUri = iprot.readString();
        struct.setExternalUriIsSet(true);
      }
      if (incoming.get(4)) {
        struct.requiredGroupName = iprot.readString();
        struct.setRequiredGroupNameIsSet(true);
      }
    }
  }

}

