/*
 * JBILLING CONFIDENTIAL
 * _____________________
 *
 * [2003] - [2012] Enterprise jBilling Software Ltd.
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Enterprise jBilling Software.
 * The intellectual and technical concepts contained
 * herein are proprietary to Enterprise jBilling Software
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden.
 */
package com.ngbilling.core.payload.request.configuration;

import java.util.ArrayList;
import java.util.List;

public class Format {
    private List<FormatField> fields = null;

    public Format() {
        fields = new ArrayList<FormatField>();
    }

    public void addField(FormatField newField) {
        fields.add(newField);
    }

    public List<FormatField> getFields() {
        return fields;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (FormatField field : fields) {
            sb.append("field: ").append(field.toString()).append("\n");
        }
        return sb.toString();
    }
}
