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

package com.ngbilling.core.server.util.pricing;

import com.ngbilling.core.server.validator.metafield.ValidationRuleModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.BigDecimalValidator;
import org.joda.time.LocalTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Simple utilities for parsing price model attributes.
 *
 * @author Brian Cowdery
 * @since 02/02/11
 */
public class AttributeUtils {

    /**
     * Validates that all the required attributes of the given strategy are present and of the
     * correct type.
     *
     * @param attributes attribute map
     * @param strategy   strategy to validate against
     * @throws Exception if attributes are missing or of an incorrect type
     */
    public static void validateAttributes(Map<String, String> attributes, List<AttributeDefinition> attributeDefinitions)
            throws Exception {

        String holder = null;
        List<String> errors = new ArrayList<String>();

        for (AttributeDefinition definition : attributeDefinitions) {
            String name = definition.getName();
            String value = attributes.get(name);

            // validate required attributes
            if (definition.isRequired() && (value == null || value.trim().equals(""))) {
                errors.add(holder + "," + name + ",validation.error.is.required");
            }

            // validate attribute types
            try {
                switch (definition.getType()) {
                    case STRING:
                        // a string is a string...
                        break;
                    case TIME:
                        parseTime(value);
                        break;
                    case INTEGER:
                        parseInteger(value);
                        break;
                    case DECIMAL:
                        parseDecimal(value);
                        break;
                }
            } catch (Exception validationException) {
                errors.add(holder + "," + name + "," + validationException);
            }
        }

        // throw new validation exception with complete error list
        if (!errors.isEmpty()) {
            throw new Exception(holder + " attributes failed validation." +
                    errors.toArray(new String[errors.size()]));
        }
    }

    public static LocalTime getTime(Map<String, String> attributes, String name) throws Exception {
        return parseTime(attributes.get(name));
    }

    /**
     * Parses the given value as LocalTime. If the value cannot be parsed, an exception will be thrown.
     *
     * @param value value to parse
     * @return parsed LocalTime
     * @throws Exception if value cannot be parsed as LocalTime
     */
    public static LocalTime parseTime(String value) throws Exception {
        String[] time = value.split(":");

        if (time.length != 2)
            throw new Exception("Cannot parse attribute value '" + value + "' as a time of day." +
                    new String[]{"validation.error.not.time.of.day"});

        try {
            return new LocalTime(Integer.valueOf(time[0]), Integer.valueOf(time[1]));
        } catch (NumberFormatException e) {
            throw new Exception("Cannot parse attribute value '" + value + "' as a time of day." +
                    new String[]{"validation.error.not.time.of.day"});
        }
    }

    public static Integer getInteger(Map<String, String> attributes, String name) throws Exception {
        return parseInteger(attributes.get(name));
    }

    /**
     * Overloaded getInteger with checkEmpty boolean argument if passed as true
     * will check if there is an empty string or spaces being passed to parse.
     *
     * @param attributes
     * @param name
     * @param checkEmpty
     * @return
     */
    public static Integer getInteger(Map<String, String> attributes, String name, boolean checkEmpty) throws Exception {
        return parseInteger(attributes.get(name), checkEmpty);
    }

    /**
     * Parses the given value as an Integer. If the value cannot be parsed, an exception will be thrown.
     *
     * @param value value to parse
     * @return parsed integer
     * @throws Exception if value cannot be parsed as an integer
     */
    public static Integer parseInteger(String value) throws Exception {
        if (value != null) {
            try {
                return Integer.valueOf(value);
            } catch (NumberFormatException e) {
                throw new Exception("Cannot parse attribute value '" + value + "' as an integer." +
                        new String[]{"validation.error.not.a.integer"});
            }
        }
        return null;
    }

    /**
     * Overloaded parseInteger with checkEmpty boolean argument if passed as true
     * will check if there is an empty string or spaces being passed to parse.
     *
     * @param value
     * @param checkEmpty
     * @return
     */
    public static Integer parseInteger(String value, boolean checkEmpty) throws Exception {
        if (value != null) {
            try {
                if (checkEmpty && !value.trim().isEmpty())
                    return Integer.valueOf(value);
            } catch (NumberFormatException e) {
                throw new Exception("Cannot parse attribute value '" + value + "' as an integer." +
                        new String[]{"validation.error.not.a.integer"});
            }
        }
        return null;
    }

    public static BigDecimal getDecimal(Map<String, String> attributes, String name) throws Exception {
        return parseDecimal(attributes.get(name));
    }

    /**
     * Parses the given value as a BigDecimal. If the value cannot be parsed, an exception will be thrown.
     *
     * @param value value to parse
     * @return parsed integer
     * @throws Exception if value cannot be parsed as an BigDecimal
     */
    public static BigDecimal parseDecimal(String value) throws Exception {
        if (value != null) {
            try {
                if (StringUtils.isEmpty(value)) {
                    return null;
                } else {
                    //validate decimal attribute's value for range
                    BigDecimal decimalValue = new BigDecimal(value);

                    if (decimalValue != null) {
                        BigDecimalValidator validator = new BigDecimalValidator();
                        Double min = -999999999999.9999999999d;
                        Double max = +999999999999.9999999999d; //12 integer, 10 fraction

                        if (!validator.isInRange(decimalValue, min, max))
                            throw new Exception("Cannot parse attribute value '" + decimalValue + "' as a decimal number." +
                                    new String[]{"validation.error.invalid.rate.or.fraction"});
                    }
                    return decimalValue;
                }
            } catch (NumberFormatException e) {
                throw new Exception("Cannot parse attribute value '" + value + "' as a decimal number." +
                        new String[]{"validation.error.not.a.number"});
            }
        }
        return null;
    }

    /**
     * Validates that all the required attributes of the given validation rule that are present and of the
     * correct type.
     *
     * @param attributes attribute map
     * @param strategy   strategy to validate against
     * @throws Exception if attributes are missing or of an incorrect type
     */
    public static void validateRuleAttributes(Map<String, String> attributes, ValidationRuleModel strategy)
            throws Exception {
        String strategyName = strategy.getClass().getSimpleName();
        List<String> errors = new ArrayList<>();
        List<AttributeDefinition> definitions = strategy.getAttributeDefinitions();
        for (AttributeDefinition definition : definitions) {
            String name = definition.getName();
            String value = attributes.get(name);

            // validate required attributes
            if (definition.isRequired() && (value == null || value.trim().equals(""))) {
                errors.add(strategyName + "," + name + ",validation.error.is.required");
            } else {
                // validate attribute types
                try {
                    switch (definition.getType()) {
                        case STRING:
                            // a string is a string...
                            break;
                        case TIME:
                            parseTime(value);
                            break;
                        case INTEGER:
                            parseInteger(value);
                            break;
                        case DECIMAL:
                            parseDecimal(value);
                            break;
                    }
                } catch (Exception validationException) {
                    errors.add(strategyName + "," + name + "," + validationException);
                }
            }
        }

        // throw new validation exception with complete error list
        if (!errors.isEmpty()) {
            throw new Exception(strategyName + " attributes failed validation." +
                    errors);
        }
    }
}
