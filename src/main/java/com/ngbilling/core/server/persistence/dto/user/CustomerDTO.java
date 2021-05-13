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
package com.ngbilling.core.server.persistence.dto.user;


import com.ngbilling.core.common.util.FormatLogger;
import com.ngbilling.core.payload.request.user.UserWS;
import com.ngbilling.core.server.persistence.dao.invoice.InvoiceDeliveryMethodDAO;
import com.ngbilling.core.server.persistence.dao.user.AccountTypeDAO;
import com.ngbilling.core.server.persistence.dao.user.PartnerDAO;
import com.ngbilling.core.server.persistence.dao.user.UserDAO;
import com.ngbilling.core.server.persistence.dto.invoice.InvoiceDeliveryMethodDTO;
import com.ngbilling.core.server.persistence.dto.metafield.CustomerAccountInfoTypeMetaField;
import com.ngbilling.core.server.persistence.dto.metafield.GroupCustomizedEntity;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;
import com.ngbilling.core.server.persistence.dto.partner.PartnerDTO;
import com.ngbilling.core.server.persistence.dto.util.EntityType;
import com.ngbilling.core.server.service.user.UserService;
import com.ngbilling.core.server.util.metafield.MetaFieldHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;


@Entity
@TableGenerator(
        name = "customer_GEN",
        table = "jbilling_seqs",
        pkColumnName = "name",
        valueColumnName = "next_id",
        pkColumnValue = "customer",
        allocationSize = 100
)
// No cache, mutable and critical
@Table(name = "customer")
public class CustomerDTO extends GroupCustomizedEntity implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final FormatLogger LOG = new FormatLogger(CustomerDTO.class);

    private int id;
    private UserDTO baseUser;
    private InvoiceDeliveryMethodDTO invoiceDeliveryMethod;
    private PartnerDTO partner;
    private Integer referralFeePaid;
    private Integer autoPaymentType;
    private Integer dueDateUnitId;
    private Integer dueDateValue;
    private Integer dfFm;
    private CustomerDTO parent;
    private Set<CustomerDTO> children = new HashSet<CustomerDTO>(0);
    private Set<CustomerNoteDTO> customerNotes = new HashSet<CustomerNoteDTO>(0);
    private Integer isParent;
    private int excludeAging;
    private Integer invoiceChild;
    private boolean useParentPricing = false;
    private BigDecimal dynamicBalance;
    private BigDecimal autoRecharge;
    private BigDecimal creditLimit;
    private BigDecimal creditNotificationLimit1;
    private BigDecimal creditNotificationLimit2;
    private int versionNum;
    private Date lastInvoiceDate;
    private Date nextInvoiceDate;

    private MainSubscriptionDTO mainSubscription;
    private AccountTypeDTO accountType;
    private String invoiceDesign;

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PartnerDAO partnerDAO;

    @Autowired
    private AccountTypeDAO accountTypeDAO;

    @Autowired
    private MetaFieldHelper metaFieldHelper;

    @Autowired
    private InvoiceDeliveryMethodDAO invoiceDeliveryMethodDAO;

    @Autowired
    private UserService userService;

    //#4501 - custom auto recharge
    private BigDecimal rechargeThreshold;
    private BigDecimal monthlyLimit;
    private BigDecimal currentMonthlyAmount;
    private Date currentMonth;

    private Set<CustomerAccountInfoTypeMetaField> customerAccountInfoTypeMetaFields = new HashSet<CustomerAccountInfoTypeMetaField>();
    private Map<Integer, List<MetaFieldValue>> aitMetaFieldMap = new HashMap<Integer, List<MetaFieldValue>>(0);

    public CustomerDTO() {
    }

    public CustomerDTO(int id) {
        this.id = id;
    }

    public CustomerDTO(int id, InvoiceDeliveryMethodDTO invoiceDeliveryMethod, int excludeAging) {
        this.id = id;
        this.invoiceDeliveryMethod = invoiceDeliveryMethod;
        this.excludeAging = excludeAging;
    }

    public CustomerDTO(int id, UserDTO baseUser, InvoiceDeliveryMethodDTO invoiceDeliveryMethod, PartnerDTO partner,
                       Integer referralFeePaid, Integer autoPaymentType, Integer dueDateUnitId,
                       Integer dueDateValue, Integer dfFm, CustomerDTO parent, Integer isParent, int excludeAging,
                       Integer invoiceChild, MainSubscriptionDTO mainSubscription, AccountTypeDTO accountType,
                       String invoiceDesign, Date nextInvoiceDate) {
        this.id = id;
        this.baseUser = baseUser;
        this.invoiceDeliveryMethod = invoiceDeliveryMethod;
        this.partner = partner;
        this.referralFeePaid = referralFeePaid;
        this.autoPaymentType = autoPaymentType;
        this.dueDateUnitId = dueDateUnitId;
        this.dueDateValue = dueDateValue;
        this.dfFm = dfFm;
        this.parent = parent;
        this.isParent = isParent;
        this.excludeAging = excludeAging;
        this.invoiceChild = invoiceChild;
        this.mainSubscription = mainSubscription;
        this.accountType = accountType;
        this.invoiceDesign = invoiceDesign;
        this.nextInvoiceDate = nextInvoiceDate;
    }

    public CustomerDTO(Integer entityId, UserWS user) {
        setBaseUser(userDAO.findById(user.getUserId()).get());

        if (user.getPartnerId() != null) {
            setPartner(partnerDAO.findById(user.getPartnerId()).get());
        }

        if (user.getParentId() != null) {
            setParent(new CustomerDTO(user.getParentId()));
        }

        if (user.getIsParent() != null) {
            setIsParent(user.getIsParent().booleanValue() ? 1 : 0);
        }

        if (user.getInvoiceChild() != null) {
            setInvoiceChild(user.getInvoiceChild() ? 1 : 0);
        }

        if (user.getUseParentPricing() != null) {
            setUseParentPricing(user.useParentPricing());
        }

        if (user.getInvoiceDeliveryMethodId() != null) {
            InvoiceDeliveryMethodDTO deliveryMethod = invoiceDeliveryMethodDAO.findById(user.getInvoiceDeliveryMethodId()).get();
            setInvoiceDeliveryMethod(deliveryMethod);
        }

        setMainSubscription(userService.convertMainSubscriptionFromWS(user.getMainSubscription(), entityId));

        setCreditLimit(user.getCreditLimit() == null ? null : new BigDecimal(user.getCreditLimit()));
        setDynamicBalance(user.getDynamicBalance() == null ? null : new BigDecimal(user.getDynamicBalance()));
        setAutoRecharge(user.getAutoRecharge() == null ? null : new BigDecimal(user.getAutoRecharge()));

        setCreditNotificationLimit1(user.getCreditLimitNotification1AsDecimal());
        setCreditNotificationLimit2(user.getCreditLimitNotification2AsDecimal());

        setAutoPaymentType(user.getAutomaticPaymentType());

        setDueDateUnitId(user.getDueDateUnitId());
        setDueDateValue(user.getDueDateValue());

        setNextInvoiceDate(user.getNextInvoiceDate());

        setExcludeAging(user.getExcludeAgeing() != null && user.getExcludeAgeing() ? 1 : 0);

        if (null != user.getAccountTypeId()) {
            AccountTypeDTO accountType = accountTypeDAO.findById(user.getAccountTypeId()).get();
            setAccountType(accountType);
        }

        setInvoiceDesign(user.getInvoiceDesign());

        setRechargeThreshold(user.getRechargeThresholdAsDecimal());
        setMonthlyLimit(user.getMonthlyLimitAsDecimal());

        LOG.debug("Customer created with auto-recharge: %s incoming var, %s", getAutoRecharge(), user.getAutoRecharge());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customer_GEN")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserDTO getBaseUser() {
        return this.baseUser;
    }

    public void setBaseUser(UserDTO baseUser) {
        this.baseUser = baseUser;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public Set<CustomerNoteDTO> getCustomerNotes() {
        return customerNotes;
    }

    public void setCustomerNotes(Set<CustomerNoteDTO> customerNotes) {
        this.customerNotes = customerNotes;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_delivery_method_id", nullable = false)
    public InvoiceDeliveryMethodDTO getInvoiceDeliveryMethod() {
        return this.invoiceDeliveryMethod;
    }

    public void setInvoiceDeliveryMethod(InvoiceDeliveryMethodDTO invoiceDeliveryMethod) {
        this.invoiceDeliveryMethod = invoiceDeliveryMethod;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id")
    public PartnerDTO getPartner() {
        return this.partner;
    }

    public void setPartner(PartnerDTO partner) {
        this.partner = partner;
    }

    @Column(name = "referral_fee_paid")
    public Integer getReferralFeePaid() {
        return this.referralFeePaid;
    }

    public void setReferralFeePaid(Integer referralFeePaid) {
        this.referralFeePaid = referralFeePaid;
    }

    @Column(name = "auto_payment_type")
    public Integer getAutoPaymentType() {
        return this.autoPaymentType;
    }

    public void setAutoPaymentType(Integer autoPaymentType) {
        this.autoPaymentType = autoPaymentType;
    }

    @Column(name = "due_date_unit_id")
    public Integer getDueDateUnitId() {
        return this.dueDateUnitId;
    }

    public void setDueDateUnitId(Integer dueDateUnitId) {
        this.dueDateUnitId = dueDateUnitId;
    }

    @Column(name = "due_date_value")
    public Integer getDueDateValue() {
        return this.dueDateValue;
    }

    public void setDueDateValue(Integer dueDateValue) {
        this.dueDateValue = dueDateValue;
    }

    @Column(name = "df_fm")
    public Integer getDfFm() {
        return this.dfFm;
    }

    public void setDfFm(Integer dfFm) {
        this.dfFm = dfFm;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    public Set<CustomerDTO> getChildren() {
        return children;
    }

    public void setChildren(Set<CustomerDTO> children) {
        this.children = children;
    }


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public CustomerDTO getParent() {
        return this.parent;
    }

    public void setParent(CustomerDTO parent) {
        this.parent = parent;
    }

    @Column(name = "is_parent")
    public Integer getIsParent() {
        return this.isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    @Column(name = "exclude_aging", nullable = false)
    public int getExcludeAging() {
        return this.excludeAging;
    }

    public void setExcludeAging(int excludeAging) {
        this.excludeAging = excludeAging;
    }

    @Column(name = "invoice_child")
    public Integer getInvoiceChild() {
        return this.invoiceChild;
    }

    public void setInvoiceChild(Integer invoiceChild) {
        this.invoiceChild = invoiceChild;
    }

    @Column(name = "use_parent_pricing", nullable = false)
    public boolean getUseParentPricing() {
        return useParentPricing;
    }

    public void setUseParentPricing(boolean useParentPricing) {
        this.useParentPricing = useParentPricing;
    }

    public boolean useParentPricing() {
        return useParentPricing;
    }

    @Column(name = "auto_recharge")
    public BigDecimal getAutoRecharge() {
        if (autoRecharge == null)
            return BigDecimal.ZERO;

        return autoRecharge;
    }

    public void setAutoRecharge(BigDecimal autoRecharge) {
        this.autoRecharge = autoRecharge;
    }

    @Column(name = "credit_limit")
    public BigDecimal getCreditLimit() {
        if (creditLimit == null) {
            return BigDecimal.ZERO;
        }
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Column(name = "credit_notification_limit1")
    public BigDecimal getCreditNotificationLimit1() {
        return creditNotificationLimit1;
    }

    public void setCreditNotificationLimit1(BigDecimal creditNotificationLimit1) {
        this.creditNotificationLimit1 = creditNotificationLimit1;
    }

    @Column(name = "credit_notification_limit2")
    public BigDecimal getCreditNotificationLimit2() {
        return creditNotificationLimit2;
    }

    public void setCreditNotificationLimit2(BigDecimal creditNotificationLimit2) {
        this.creditNotificationLimit2 = creditNotificationLimit2;
    }

    @Column(name = "dynamic_balance")
    public BigDecimal getDynamicBalance() {
        if (dynamicBalance == null) {
            return BigDecimal.ZERO;
        }
        return dynamicBalance;
    }

    public void setDynamicBalance(BigDecimal dynamicBalance) {
        this.dynamicBalance = dynamicBalance;
    }

    @Embedded
    public MainSubscriptionDTO getMainSubscription() {
        return mainSubscription;
    }

    public void setMainSubscription(MainSubscriptionDTO mainSubscription) {
        this.mainSubscription = mainSubscription;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type_id", nullable = true)
    public AccountTypeDTO getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeDTO accountType) {
        this.accountType = accountType;
    }

    @Column(name = "invoice_design", length = 100)
    public String getInvoiceDesign() {
        return invoiceDesign;
    }

    public void setInvoiceDesign(String invoiceDesign) {
        this.invoiceDesign = invoiceDesign;
    }

    @Version
    @Column(name = "OPTLOCK")
    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Column(name = "next_inovice_date", length = 29)
    public Date getNextInvoiceDate() {
        return this.nextInvoiceDate;
    }

    public void setNextInvoiceDate(Date nextInvoiceDate) {
        this.nextInvoiceDate = nextInvoiceDate;
    }

    @Column(name = "recharge_threshold", nullable = true)
    public BigDecimal getRechargeThreshold() {
        return rechargeThreshold;
    }

    public void setRechargeThreshold(BigDecimal rechargeThreshold) {
        this.rechargeThreshold = rechargeThreshold;
    }

    @Column(name = "monthly_limit", nullable = true)
    public BigDecimal getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(BigDecimal monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    @Column(name = "current_monthly_amount", nullable = true)
    public BigDecimal getCurrentMonthlyAmount() {
        return currentMonthlyAmount;
    }

    public void setCurrentMonthlyAmount(BigDecimal currentMonthlyAmount) {
        this.currentMonthlyAmount = currentMonthlyAmount;
    }

    @Column(name = "current_month", nullable = true)
    public Date getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(Date currentMonth) {
        this.currentMonth = currentMonth;
    }


    @Transient
    public EntityType[] getCustomizedEntityType() {
        return new EntityType[]{EntityType.CUSTOMER, EntityType.ACCOUNT_TYPE};
    }

    @Transient
    public Integer getTotalSubAccounts() {
        LOG.debug("sub acounts = %s", getChildren().size());
        return (getChildren().size() == 0) ? null : getChildren().size();
    }

    @Transient
    public boolean invoiceAsChild() {
        return (invoiceChild == null || invoiceChild.intValue() == 0);
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", baseUser.userId=" + (baseUser != null ? baseUser.getUserId() : null) +
                ", baseUser.userName=" + (baseUser != null ? baseUser.getUserName() : null) +
                ", dynamicBalance = " + this.dynamicBalance +
                ", credit limit = " + this.creditLimit +
                ", nextInvoiceDate=" + this.nextInvoiceDate +
                '}';
    }

    @Transient
    public void setAitMetaField(Integer entityId, Integer groupId, String name, Object value) {
        metaFieldHelper.setAitMetaField(entityId, this, groupId, name, value);
    }

    @Transient
    public void setAitMetaField(MetaFieldValue field, Integer groupId) {
        if (getAitMetaFieldMap().containsKey(groupId)) {
            getAitMetaFieldMap().get(groupId).add(field);
        } else {
            List<MetaFieldValue> fields = new ArrayList<MetaFieldValue>();
            fields.add(field);
            getAitMetaFieldMap().put(groupId, fields);
        }
    }

    @Transient
    public Map<Integer, List<MetaFieldValue>> getAitMetaFieldMap() {
        return aitMetaFieldMap;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<CustomerAccountInfoTypeMetaField> getCustomerAccountInfoTypeMetaFields() {
        return this.customerAccountInfoTypeMetaFields;
    }

    public void setCustomerAccountInfoTypeMetaFields(Set<CustomerAccountInfoTypeMetaField> customerAccountInfoTypeMetaFields) {
        this.customerAccountInfoTypeMetaFields = customerAccountInfoTypeMetaFields;
    }

    @Transient
    public void addCustomerAccountInfoTypeMetaField(MetaFieldValue value, AccountInformationTypeDTO accountInfoType, Date effectiveDate) {
        CustomerAccountInfoTypeMetaField existed = getCustomerAccountInfoTypeMetaField(value.getField().getName(), accountInfoType.getId(), effectiveDate);
        if (existed != null) {
            existed.getMetaFieldValue().setValue(value.getValue());
        } else {
            CustomerAccountInfoTypeMetaField metaField = new CustomerAccountInfoTypeMetaField(this, accountInfoType, value, effectiveDate);
            this.customerAccountInfoTypeMetaFields.add(metaField);
        }
    }

    @Transient
    public void insertCustomerAccountInfoTypeMetaField(MetaFieldValue value, AccountInformationTypeDTO accountInfoType, Date effectiveDate) {
        CustomerAccountInfoTypeMetaField existed = getCustomerAccountInfoTypeMetaField(value.getField().getName(), accountInfoType.getId(), effectiveDate);
        if (existed == null) {
            CustomerAccountInfoTypeMetaField metaField = new CustomerAccountInfoTypeMetaField(this, accountInfoType, value, effectiveDate);
            this.customerAccountInfoTypeMetaFields.add(metaField);
        }
    }

    @Transient
    public void removeCustomerAccountInfoTypeMetaFields(Integer groupId, Date effectiveDate) {
        for (CustomerAccountInfoTypeMetaField existed : getCustomerAccountInfoTypeMetaFields(groupId, effectiveDate)) {
            this.customerAccountInfoTypeMetaFields.remove(existed);
        }
    }

    @Transient
    public CustomerAccountInfoTypeMetaField getCustomerAccountInfoTypeMetaField(String name, Integer groupId, Date effectiveDate) {
        for (CustomerAccountInfoTypeMetaField infoTypeMetaField : customerAccountInfoTypeMetaFields) {
            if (groupId == infoTypeMetaField.getAccountInfoType().getId() && effectiveDate.equals(infoTypeMetaField.getEffectiveDate())) {
                MetaFieldValue suspect = infoTypeMetaField.getMetaFieldValue();
                if (suspect.getField().getName().equals(name)) {
                    return infoTypeMetaField;
                }
            }
        }
        return null;
    }

    @Transient
    public List<CustomerAccountInfoTypeMetaField> getCustomerAccountInfoTypeMetaFields(Integer groupId, Date effectiveDate) {
        List<CustomerAccountInfoTypeMetaField> fields = new ArrayList<CustomerAccountInfoTypeMetaField>();
        for (CustomerAccountInfoTypeMetaField infoTypeMetaField : customerAccountInfoTypeMetaFields) {
            if (groupId == infoTypeMetaField.getAccountInfoType().getId() && effectiveDate.equals(infoTypeMetaField.getEffectiveDate())) {
                fields.add(infoTypeMetaField);
            }
        }
        return fields;
    }

    @Transient
    public void removeAitMetaFields() {
        this.customerAccountInfoTypeMetaFields.removeAll(this.customerAccountInfoTypeMetaFields);
    }

    @Override
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "payment_information_meta_fields_map",
            joinColumns = @JoinColumn(name = "payment_information_id"),
            inverseJoinColumns = @JoinColumn(name = "meta_field_value_id")
    )
    public List<MetaFieldValue> getMetaFields() {
        // TODO Auto-generated method stub
        return getMetaFieldsList();
    }

}
