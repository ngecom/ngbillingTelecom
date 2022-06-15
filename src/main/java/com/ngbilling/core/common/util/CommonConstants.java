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

package com.ngbilling.core.common.util;


import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;

/**
 * @author Emil
 */
public class CommonConstants {

    public static final Date EPOCH_DATE = new DateTime(1970, 1, 1, 0, 0, 0, 0).withTime(0, 0, 0, 0).toDate();

    public static final Integer INTEGER_TRUE = Integer.valueOf(1);
    public static final Integer INTEGER_FALSE = Integer.valueOf(0);

    public static final String LIST_TYPE_ITEM_TYPE = "type";
    public static final String LIST_TYPE_CUSTOMER = "customer";
    public static final String LIST_TYPE_CUSTOMER_SIMPLE = "customerSimple";
    public static final String LIST_TYPE_PARTNERS_CUSTOMER = "partnersCustomer";
    public static final String LIST_TYPE_SUB_ACCOUNTS = "sub_accounts";
    public static final String LIST_TYPE_ITEM = "item";
    public static final String LIST_TYPE_ITEM_ORDER = "itemOrder";
    public static final String LIST_TYPE_ITEM_USER_PRICE = "price";
    public static final String LIST_TYPE_PROMOTION = "promotion";
    public static final String LIST_TYPE_PAYMENT = "payment";
    public static final String LIST_TYPE_PAYMENT_USER = "paymentUser";
    public static final String LIST_TYPE_ORDER = "order";
    public static final String LIST_TYPE_INVOICE = "invoice";
    public static final String LIST_TYPE_REFUND = "refund";
    public static final String LIST_TYPE_INVOICE_GRAL = "invoiceGeneral";
    public static final String LIST_TYPE_PROCESS = "process";
    public static final String LIST_TYPE_PROCESS_INVOICES = "processInvoices";
    public static final String LIST_TYPE_PROCESS_RUN_SUCCESSFULL_USERS = "processRunSuccessfullUsers";
    public static final String LIST_TYPE_PROCESS_RUN_FAILED_USERS = "processRunFailedUsers";
    public static final String LIST_TYPE_PROCESS_ORDERS = "processOrders";
    public static final String LIST_TYPE_NOTIFICATION_TYPE = "notificationType";
    public static final String LIST_TYPE_PARTNER = "partner";
    public static final String LIST_TYPE_PAYOUT = "payout";
    public static final String LIST_TYPE_INVOICE_ORDER = "invoicesOrder";

    // results from payments
    // this has to by in synch with how the database is initialized
    public static final Integer RESULT_OK = 1;
    public static final Integer RESULT_FAIL = 2;
    public static final Integer RESULT_UNAVAILABLE = 3;
    public static final Integer RESULT_ENTERED = 4;
    // a special one, to represent 'no result' (for filers, routers, etc)
    public static final Integer RESULT_NULL = 0;

    // user types, these have to by in synch with the user_type table
    // these are needed in the server side and the jsps
    public static final Integer TYPE_INTERNAL = 1;
    public static final Integer TYPE_ROOT = 2;
    public static final Integer TYPE_CLERK = 3;
    public static final Integer TYPE_PARTNER = 4;
    public static final Integer TYPE_CUSTOMER = 5;

    // payment methods (db - synch)
    public static final Integer PAYMENT_METHOD_CHEQUE = 1;
    public static final Integer PAYMENT_METHOD_VISA = 2;
    public static final Integer PAYMENT_METHOD_MASTERCARD = 3;
    public static final Integer PAYMENT_METHOD_AMEX = 4;
    public static final Integer PAYMENT_METHOD_ACH = 5;
    public static final Integer PAYMENT_METHOD_DISCOVER = 6;
    public static final Integer PAYMENT_METHOD_DINERS = 7;
    public static final Integer PAYMENT_METHOD_PAYPAL = 8;
    public static final Integer PAYMENT_METHOD_GATEWAY_KEY = 9;
    public static final Integer PAYMENT_METHOD_INSTAL_PAYMENT = 10;
    public static final Integer PAYMENT_METHOD_JCB = 11;
    public static final Integer PAYMENT_METHOD_LASER = 12;
    public static final Integer PAYMENT_METHOD_MAESTRO = 13;
    public static final Integer PAYMENT_METHOD_VISA_ELECTRON = 14;
    public static final Integer PAYMENT_METHOD_CREDIT = 15;

    //payment result
    public static final Integer PAYMENT_RESULT_SUCCESSFUL = 1;
    public static final Integer PAYMENT_RESULT_FAILED = 2;
    public static final Integer PAYMENT_RESULT_PROCESSOR_UNAVAILABLE = 3;
    public static final Integer PAYMENT_RESULT_ENTERED = 4;

    // billing process review status
    public static final Integer REVIEW_STATUS_GENERATED = 1;
    public static final Integer REVIEW_STATUS_APPROVED = 2;
    public static final Integer REVIEW_STATUS_DISAPPROVED = 3;

    // user status in synch with db table user_status
    public static final Integer STATUS_ACTIVE = 1; // this HAS to be the very first status

    // subscriber status in synch with db table subscriber_status
    public static final Integer SUBSCRIBER_ACTIVE = 1;
    public static final Integer SUBSCRIBER_PENDING_UNSUBSCRIPTION = 2;
    public static final Integer SUBSCRIBER_UNSUBSCRIBED = 3;
    public static final Integer SUBSCRIBER_PENDING_EXPIRATION = 4;
    public static final Integer SUBSCRIBER_EXPIRED = 5;
    public static final Integer SUBSCRIBER_NONSUBSCRIBED = 6;
    public static final Integer SUBSCRIBER_DISCONTINUED = 7;
    // IDs of default set of order statuses pre created.
    public static final Integer DEFAULT_ORDER_INVOICE_STATUS_ID = 1;
    public static final Integer DEFAULT_ORDER_FINISHED_STATUS_ID = 2;
    public static final Integer DEFAULT_ORDER_NOT_INVOICE_STATUS_ID = 3;
    public static final Integer DEFAULT_ORDER_SUSPENDED_AGEING_STATUS_ID = 4;
    // order change status, in synch with db
    public static final Integer ORDER_CHANGE_STATUS_PENDING = 1;
    public static final Integer ORDER_CHANGE_STATUS_APPLY_ERROR = 2;
    // order change type, in synch with db
    public static final Integer ORDER_CHANGE_TYPE_DEFAULT = 1;
    // invoice status, in synch with db
    public static final Integer INVOICE_STATUS_PAID = 1;
    public static final Integer INVOICE_STATUS_UNPAID = 2;
    public static final Integer INVOICE_STATUS_UNPAID_AND_CARRIED = 3;
    // process run status, in synch with db
    public static final Integer PROCESS_RUN_STATUS_RINNING = 1;
    public static final Integer PROCESS_RUN_STATUS_SUCCESS = 2;
    public static final Integer PROCESS_RUN_STATUS_FAILED = 3;
    // invoice delivery method types
    public static final Integer D_METHOD_EMAIL = 1;
    public static final Integer D_METHOD_PAPER = 2;
    public static final Integer D_METHOD_EMAIL_AND_PAPER = 3;
    // automatic payment methods
    // how a customer wants to pay in the automatic process
    public static final Integer AUTO_PAYMENT_TYPE_CC = 1;
    public static final Integer AUTO_PAYMENT_TYPE_ACH = 2;
    public static final Integer AUTO_PAYMENT_TYPE_CHEQUE = 3;
    // types of PDF batch generation
    public static final Integer OPERATION_TYPE_CUSTOMER = 1;
    public static final Integer OPERATION_TYPE_RANGE = 2;
    public static final Integer OPERATION_TYPE_PROCESS = 3;
    public static final Integer OPERATION_TYPE_DATE = 4;
    public static final Integer OPERATION_TYPE_NUMBER = 5;
    // Payment Methods Type MetaFields Names
    public static final String METAFIELD_NAME_CC_CARDHOLDER_NAME = "cc.cardholder.name";
    public static final String METAFIELD_NAME_CC_NUMBER = "cc.number";
    public static final String METAFIELD_NAME_CC_EXPIRY_DATE = "cc.expiry.date";
    public static final String METAFIELD_NAME_CC_GATEWAY_KEY = "cc.gateway.key";
    public static final String METAFIELD_NAME_CC_TYPE = "cc.type";
    public static final String METAFIELD_NAME_CHEQUE_BANK_NAME = "cheque.bank.name";
    public static final String METAFIELD_NAME_CHEQUE_NUMBER = "cheque.number";
    public static final String METAFIELD_NAME_CHEQUE_DATE = "cheque.date";
    public static final String METAFIELD_NAME_ACH_ROUTING_NUMBER = "ach.routing.number";
    public static final String METAFIELD_NAME_ACH_CUSTOMER_NAME = "ach.customer.name";
    public static final String METAFIELD_NAME_ACH_ACCOUNT_NUMBER = "ach.account.number";
    public static final String METAFIELD_NAME_ACH_BANK_NAME = "ach.bank.name";
    public static final String METAFIELD_NAME_ACH_GATEWAY_KEY = "ach.gateway.key";
    public static final String METAFIELD_NAME_ACH_ACCOUNT_TYPE = "ach.account.type";
    /**
     * BigDecimal caculation constants <br/>
     * This value must be inline with underlying SQL data type
     */
    public static final int BIGDECIMAL_SCALE = 10;
    public static final int BIGDECIMAL_ROUND = BigDecimal.ROUND_HALF_UP;
    /**
     * Round to 2 decimals for view. Use it with formatters and/or toString
     */
    public static final int BIGDECIMAL_SCALE_STR = 2;
    public static final BigDecimal BIGDECIMAL_ONE = new BigDecimal("1");
    public static final BigDecimal BIGDECIMAL_ONE_CENT = new BigDecimal("0.01");
    public static final String PASSWORD_PATTERN_4_UNIQUE_CLASSES = "^.*(?=.{8,40})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=]).*$";
    public static final String USERNAME_PATTERN = "^[. A-Za-z0-9_-]*$";
    // codes for login resuls
    public final static Integer AUTH_OK = 0;
    public final static Integer AUTH_WRONG_CREDENTIALS = 1;
    public final static Integer AUTH_LOCKED = 2;  // invalid login creds - bad attempt locked account
    public final static Integer AUTH_EXPIRED = 3; // login creds ok - password expired and needs updating
    // mediation record status
    public final static Integer MEDIATION_RECORD_STATUS_DONE_AND_BILLABLE = Integer.valueOf(1);
    public final static Integer MEDIATION_RECORD_STATUS_DONE_AND_NOT_BILLABLE = Integer.valueOf(2);
    public final static Integer MEDIATION_RECORD_STATUS_ERROR_DETECTED = Integer.valueOf(3);
    public final static Integer MEDIATION_RECORD_STATUS_ERROR_DECLARED = Integer.valueOf(4);
    // types of balances
    public final static Integer BALANCE_NO_DYNAMIC = 1; // the default
    public final static Integer BALANCE_PRE_PAID = 2;
    public final static Integer BALANCE_CREDIT_LIMIT = 3;
    //Route Based Rate Cards
    public static final String DEFAULT_DATE_FIELD_NAME = "event_date";
    public static final String DEFAULT_DURATION_FIELD_NAME = "duration";
    public static final String ROUTE_ID_FIELD_NAME = "routeId";
    public static final String NEXT_ROUTE_FIELD_NAME = "next_route";
    public static final String PRODUCT_FIELD_NAME = "product";
    public static final String MATCHING_FIELD_TABLE_PLACEHOLDER = ":table:";
    // payment methods defined in system
    public static final String PAYMENT_CARD = "Payment Card";
    public static final String ACH = "ACH";
    public static final String ACH_CHECKING = "CHECKING";
    public static final String ACH_SAVING = "SAVINGS";
    public static final String CHEQUE = "Cheque";
    // subscription product constants
    public static final String SUBSCRIPTION_ACCOUNT_PASSWORD = "subscription_password";
    /* #10256 - Asset Reservation - 10 minutes by default */
    public static final Integer DEFAULT_ASSET_RESERVATION_TIME_IN_MS = 600000;
    public static final String JPA_UNIT_NAME_1 = "OnlineDataSource";
    public static final String JPA_UNIT_NAME_2 = "BatchDataSource";
    // these are the preference's types. This has to be in synch with the DB
    //public static Integer PREFERENCE_PAYMENT_WITH_PROCESS = 1); obsolete
    public static Integer PREFERENCE_CSS_LOCATION = 2;
    public static Integer PREFERENCE_LOGO_LOCATION = 3;
    public static Integer PREFERENCE_GRACE_PERIOD = 4;
    public static Integer PREFERENCE_PAPER_SELF_DELIVERY = 13;
    public static Integer PREFERENCE_SHOW_NOTE_IN_INVOICE = 14;
    public static Integer PREFERENCE_DAYS_ORDER_NOTIFICATION_S1 = 15;
    public static Integer PREFERENCE_DAYS_ORDER_NOTIFICATION_S2 = 16;
    public static Integer PREFERENCE_DAYS_ORDER_NOTIFICATION_S3 = 17;
    public static Integer PREFERENCE_INVOICE_PREFIX = 18;
    public static Integer PREFERENCE_INVOICE_NUMBER = 19;
    public static Integer PREFERENCE_INVOICE_DELETE = 20;
    public static Integer PREFERENCE_USE_INVOICE_REMINDERS = 21;
    public static Integer PREFERENCE_FIRST_REMINDER = 22;
    public static Integer PREFERENCE_NEXT_REMINDER = 23;
    public static Integer PREFERENCE_USE_DF_FM = 24;
    public static Integer PREFERENCE_USE_OVERDUE_PENALTY = 25;
    public static Integer PREFERENCE_PAGE_SIZE = 26;
    public static Integer PREFERENCE_USE_ORDER_ANTICIPATION = 27;
    public static Integer PREFERENCE_PAYPAL_ACCOUNT = 28;
    public static Integer PREFERENCE_PAYPAL_BUTTON_URL = 29;
    public static Integer PREFERENCE_URL_CALLBACK = 30;
    public static Integer PREFERENCE_CONTINUOUS_DATE = 31;
    public static Integer PREFERENCE_PDF_ATTACHMENT = 32;
    public static Integer PREFERENCE_ORDER_OWN_INVOICE = 33;
    public static Integer PREFERENCE_PRE_AUTHORIZE_CC = 34;
    public static Integer PREFERENCE_ORDER_IN_INVOICE_LINE = 35;
    public static Integer PREFERENCE_CUSTOMER_CONTACT_EDIT = 36;
    public static Integer PREFERENCE_LINK_AGEING_TO_SUBSCRIPTION = 38;
    public static Integer PREFERENCE_FAILED_LOGINS_LOCKOUT = 39;
    public static Integer PREFERENCE_PASSWORD_EXPIRATION = 40;
    public static Integer PREFERENCE_USE_CURRENT_ORDER = 41;
    public static Integer PREFERENCE_USE_PRO_RATING = 42;
    public static Integer PREFERENCE_USE_BLACKLIST = 43;
    public static Integer PREFERENCE_ALLOW_NEGATIVE_PAYMENTS = 44;
    public static Integer PREFERENCE_DELAY_NEGATIVE_PAYMENTS = 45;
    public static Integer PREFERENCE_ALLOW_INVOICES_WITHOUT_ORDERS = 46;
    public static Integer PREFERENCE_MEDIATION_JDBC_READER_LAST_ID = Integer.valueOf(47);
    public static Integer PREFERENCE_AUTO_RECHARGE_THRESHOLD = 49;
    public static Integer PREFERENCE_INVOICE_DECIMALS = 50;
    public static Integer PREFERENCE_MINIMUM_BALANCE_TO_IGNORE_AGEING = 51;
    public static Integer PREFERENCE_ATTACH_INVOICE_TO_NOTIFICATIONS = 52;
    public static Integer PREFERENCE_FORCE_UNIQUE_EMAILS = 53;
    public static Integer PREFERENCE_UNIQUE_PRODUCT_CODE = 55;
    public static Integer PREFERENCE_PARTNER_DEFAULT_COMMISSION_TYPE = 61;
    public static Integer PREFERENCE_SHOW_NO_IMPACT_PLAN_ITEMS = 62;
    public static Integer PREFERENCE_USE_JQGRID = 63;
    public static Integer PREFERENCE_DIAMETER_DESTINATION_REALM = 64;
    public static Integer PREFERENCE_DIAMETER_QUOTA_THRESHOLD = 65;
    public static Integer PREFERENCE_DIAMETER_SESSION_GRACE_PERIOD_SECONDS = 66;
    public static Integer PREFERENCE_DIAMETER_UNIT_DIVISOR = Integer.valueOf(67);
    public static Integer PREFERENCE_ACCOUNT_LOCKOUT_TIME = 68;
    public static Integer PREFERENCE_ITG_INVOICE_NOTIFICATION = 69;
    public static Integer PREFERENCE_EXPIRE_INACTIVE_AFTER_DAYS = 70;
    public static Integer PREFERENCE_FORGOT_PASSWORD_EXPIRATION = 71;
    public static Integer PREFERENCE_CREATE_CREDENTIALS_BY_DEFAULT = 75;
    public static Integer PREFERENCE_ASSET_RESERVATION_DURATION = 76;
    //11369 - Inactive User Account Management
    public static Integer MAX_VALUE_FOR_PREFERENCE_EXPIRE_INACTIVE_ACCOUNTS_IN_DAYS = 90;

}
