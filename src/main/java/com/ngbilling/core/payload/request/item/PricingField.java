/*
 jBilling - The Enterprise Open Source Billing System
 Copyright (C) 2003-2011 Enterprise jBilling Software Ltd. and Emiliano Conde

 This file is part of jbilling.

 jbilling is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 jbilling is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with jbilling.  If not, see <http://www.gnu.org/licenses/>.

 This source was modified by Web Data Technologies LLP (www.webdatatechnologies.in) since 15 Nov 2015.
 You may download the latest source from webdataconsulting.github.io.

 */
package com.ngbilling.core.payload.request.item;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Emil
 */
public class PricingField implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String STRING_ENCODING = "UTF-8";

    private String name;
    private Type type;
    private Integer position = 1;
    private String value = null;
    private long resultId; // at the time, only used for mediation of batch

    public PricingField() {
    }

    /**
     * Constructs a new PricingField from a given encoded String.
     * <p>
     * This constructor is designed for internal use only.
     *
     * @param encoded encoded string to parse
     * @see #encode(PricingField)
     */
    public PricingField(String encoded) {
        String[] fields = encoded.split(":");

        if (fields == null || fields.length != 4) {
            this.name = "";
            this.type = Type.INTEGER;
            this.value = "0";
            return;
        }

        try {
            this.name = fields[0] != null ? URLDecoder.decode(fields[0], STRING_ENCODING) : fields[0];
            this.position = Integer.parseInt(fields[1]);
            this.type = mapType(fields[2]);
            this.value = fields[3].equals("null") ? null : URLDecoder.decode(fields[3], STRING_ENCODING);
        } catch (UnsupportedEncodingException e) {
            // Should never happen
        }
    }

    /**
     * Copy constructor, creates a new instance of the given PricingField with
     * the same member values.
     *
     * @param field pricing field to copy
     */
    public PricingField(PricingField field) {
        this.name = field.getName();
        this.type = field.getType();
        this.position = field.getPosition();
        this.value = field.getStrValue();
    }

    /**
     * Constructs a new PricingField of type {@code STRING}
     *
     * @param name  field name
     * @param value field value
     */
    public PricingField(String name, String value) {
        this.name = name;
        this.type = Type.STRING;
        setStrValue(value);
    }

    /**
     * Constructs a new PricingField of type {@code DATE}
     *
     * @param name  field name
     * @param value field value
     */
    public PricingField(String name, Date value) {
        this.name = name;
        this.type = Type.DATE;
        setDateValue(value);
    }

    /**
     * Constructs a new PricingField of type {@code INTEGER}
     *
     * @param name  field name
     * @param value field value
     */
    public PricingField(String name, Integer value) {
        this.name = name;
        this.type = Type.INTEGER;
        setIntValue(value);
    }

    /**
     * Constructs a new PricingField of type {@code DECIMAL}
     *
     * @param name  field name
     * @param value field value
     */
    public PricingField(String name, BigDecimal value) {
        this.name = name;
        this.type = Type.DECIMAL;
        setDecimalValue(value);
    }

    /**
     * Constructs a new PricingField of type {@code BOOLEAN}
     *
     * @param name  field name
     * @param value field value
     */
    public PricingField(String name, Boolean value) {
        this.name = name;
        this.type = Type.BOOLEAN;
        setBooleanValue(value);
    }

    public PricingField(String name, Long value) {
        this.name = name;
        this.type = Type.LONG;
        setLongValue(value);
    }

    /**
     * Returns an appropriate {@link Type} for the given string, or null if no matching type found.
     * <p>
     * Type strings:
     * string
     * integer
     * float
     * double
     * decimal
     * date
     * boolean
     *
     * @param myType type string
     * @return matching type
     */
    public static Type mapType(String myType) {  // todo: should be a member of the Type enum eg, Type$fromString(...);
        if (myType.equalsIgnoreCase("string")) {
            return Type.STRING;
        } else if (myType.equalsIgnoreCase("integer")) {
            return Type.INTEGER;
        } else if (myType.equalsIgnoreCase("float") || myType.equalsIgnoreCase("double") || myType.equalsIgnoreCase("decimal")) {
            return Type.DECIMAL;
        } else if (myType.equalsIgnoreCase("date")) {
            return Type.DATE;
        } else if (myType.equalsIgnoreCase("boolean")) {
            return Type.BOOLEAN;
        } else if (myType.equalsIgnoreCase("long")) {
            return Type.LONG;
        } else {
            return null;
        }
    }

    /**
     * Encodes a pricing field as a string. The encoded string is a semi-colon
     * delimited string in the format {@code :name:position:type:value}, where name and position are
     * optional.
     * <p>
     * Example:
     * :src::string:310-1010
     * :dst::string:1-800-123-4567
     * :userid:integer:1234
     *
     * @param field field to encode
     * @return encoded string
     */
    public static String encode(PricingField field) {
        StringBuffer sb = new StringBuffer();
        try {
            sb.append(URLEncoder.encode(field.getName(), STRING_ENCODING))
                    .append(':')
                    .append(field.getPosition());

            switch (field.getType()) {
                case STRING:
                    sb.append(":string:");
                    break;

                case INTEGER:
                    sb.append(":integer:");
                    break;

                case LONG:
                    sb.append(":long:");
                    break;

                case DECIMAL:
                    sb.append(":float:");
                    break;

                case DATE:
                    sb.append(":date:");
                    break;
                case BOOLEAN:
                    sb.append(":boolean:");
                    break;
            }
            sb.append(field.getStrValue() != null ? URLEncoder.encode(field.getStrValue(), STRING_ENCODING) : field.getStrValue());
        } catch (UnsupportedEncodingException e) {
        }

        return sb.toString();
    }

    /**
     * Parses a comma separated list of encoded PricingField strings and returns
     * an array of fields.
     *
     * @param pricingFields comma separated list of encoded pricing field strings
     * @return array of fields
     */
    public static PricingField[] getPricingFieldsValue(String pricingFields) {
        if (pricingFields == null)
            return null;
        String[] fields = pricingFields.split(",");
        if (fields == null || fields.length == 0) {
            return null;
        }
        List<PricingField> result = new ArrayList<PricingField>();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] != null && !fields[i].equals("") && fields[i].split(":").length == 4) {
                result.add(new PricingField(fields[i]));
            }
        }
        return result.toArray(new PricingField[result.size()]);
    }

    /**
     * Returns a comma separated list of encoded PricingField strings from the given
     * array of fields.
     *
     * @param pricingFields array of fields to convert
     * @return comma separated list of encoded pricing field strings
     */
    public static String setPricingFieldsValue(PricingField[] pricingFields) {
        PricingField[] fields = pricingFields; // defensive copy
        StringBuffer result = new StringBuffer();
        if (fields != null && fields.length > 0) {
            for (int i = 0; i < fields.length; i++) {
                result.append(PricingField.encode(fields[i]));
                if (i < (fields.length - 1)) {
                    result.append(',');
                }
            }
        }
        return result.toString();
    }

    /**
     * Convenience method to find a pricing field by name.
     *
     * @param fields    pricing fields
     * @param fieldName name
     * @return found pricing field or null if no field found.
     */
    public static PricingField find(List<PricingField> fields, String fieldName) {
        if (fields != null) {
            for (PricingField field : fields) {
                if (field.getName().equals(fieldName))
                    return field;
            }
        }
        return null;
    }

    public static String setPricingFieldsValue(List<PricingField> pricingFields) {
        PricingField[] fields = pricingFields.toArray(new PricingField[pricingFields.size()]);
        return setPricingFieldsValue(fields);
    }

    public static void add(List<PricingField> fields, PricingField pricingField) {
        PricingField fieldToAdd = PricingField.find(fields, pricingField.getName());
        if (fieldToAdd != null) {
            fields.remove(fieldToAdd);
        }
        fields.add(pricingField);
    }

    public static void addAll(List<PricingField> fields, List<PricingField> fieldsToAdd) {
        for (PricingField fieldToAdd : fieldsToAdd) {
            PricingField.add(fields, fieldToAdd);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public long getResultId() {
        return resultId;
    }

    public void setResultId(long resultId) {
        this.resultId = resultId;
    }

    /**
     * Returns this pricing fields value as a raw type.
     *
     * @return pricing field value
     */
    public Object getValue() {
        switch (type) {
            case STRING:
                return value;
            case DATE:
                return getDateValue();
            case INTEGER:
                return getIntValue();
            case DECIMAL:
                return getDecimalValue();
            case BOOLEAN:
                return getBooleanValue();
            case LONG:
                return getLongValue();
            default:
                return null;
        }
    }

    @XmlTransient
    public String getStrValue() {
        return value;
    }

    public void setStrValue(String value) {
        this.value = value;
    }

    @XmlTransient
    public Date getDateValue() {
        if (value == null) return null;
        return new Date(Long.parseLong(value));
    }

    public void setDateValue(Date value) {
        if (value != null) {
            this.value = String.valueOf(value.getTime());
        } else {
            this.value = null;
        }
    }

    @XmlTransient
    public Calendar getCalendarValue() {
        if (value == null) return null;

        Calendar cal = Calendar.getInstance();
        cal.setTime(getDateValue());
        return cal;
    }

    @XmlTransient
    public Integer getIntValue() {
        if (value == null) return null;
        return Integer.valueOf(value);
    }

    public void setIntValue(Integer value) {
        if (value != null) {
            this.value = value.toString();
        } else {
            this.value = null;
        }
    }

    @XmlTransient
    public BigDecimal getDecimalValue() {
        if (value == null) return null;
        return new BigDecimal(value);
    }

    public void setDecimalValue(BigDecimal value) {
        if (value != null) {
            this.value = value.toString();
        } else {
            this.value = null;
        }
    }

    /**
     * Returns the decimal value as a double. This method is provided for backwards
     * compatibility, use {@link PricingField#getDecimalValue()} instead.
     *
     * @return decimal value as a double
     */
    @XmlTransient
    public Double getDoubleValue() {
        BigDecimal value = getDecimalValue();
        return (value != null ? value.doubleValue() : null);
    }

    /**
     * @return decimal value as a float
     * @see #getDoubleValue()
     */
    @XmlTransient
    public Double getFloatValue() {
        return getDoubleValue();
    }

    @XmlTransient
    public Boolean getBooleanValue() {
        if (value == null) return null;
        return Boolean.valueOf(this.value);
    }

    public void setBooleanValue(Boolean value) {
        if (value != null) {
            this.value = value.toString();
        } else {
            this.value = null;
        }
    }

    @XmlTransient
    public Long getLongValue() {
        if (value == null) {
            return null;
        }
        return Long.valueOf(this.value);
    }

    public void setLongValue(Long value) {
        if (value != null) {
            this.value = value.toString();
        } else {
            this.value = null;
        }
    }

    @Override
    public String toString() {
        return "name: " + name
                + " type: " + type
                + " value: " + getValue()
                + " position: " + position
                + " resultId: " + resultId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PricingField that = (PricingField) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (type != that.type) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    public enum Type {STRING, INTEGER, DECIMAL, DATE, BOOLEAN, LONG}

}
