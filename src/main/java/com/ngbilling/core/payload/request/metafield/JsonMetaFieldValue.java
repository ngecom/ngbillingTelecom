package com.ngbilling.core.payload.request.metafield;


import java.io.StringReader;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.codehaus.jettison.json.JSONObject;

import com.ngbilling.core.common.util.FormatLogger;
import com.ngbilling.core.server.persistence.dto.metafield.MetaField;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;

@Entity
@DiscriminatorValue("json")
public class JsonMetaFieldValue extends MetaFieldValue<String> {

    private static final FormatLogger LOG = new FormatLogger(JsonMetaFieldValue.class);

    private String json;

    public JsonMetaFieldValue() {
    }

    public JsonMetaFieldValue(MetaField name) {
        super(name);
    }

    @Column(name = "string_value", nullable = true)
    public String getValue() {
        return json;
    }

    public void setValue(String json) {
        this.json = json;
    }

    @Transient
    public JSONObject getJsonValue() {
        if (json == null)
            return null;

        try {
            return json != null ? (JSONObject) new JSONParser(new StringReader(json)).parse() : null;
        } catch (ParseException e) {
            LOG.error("Could not parse string as JSON object.", e);
            return null;
        }
    }

    public void setJsonValue(JSONObject value) {
        this.json = value != null ? value.toString() : null;
    }

    @Override
    @Transient
    public boolean isEmpty() {
        return StringUtils.isBlank(json);
    }
}
