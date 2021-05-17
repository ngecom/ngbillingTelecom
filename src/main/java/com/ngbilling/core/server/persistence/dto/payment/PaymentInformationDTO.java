package com.ngbilling.core.server.persistence.dto.payment;

import com.ngbilling.core.payload.request.payment.PaymentInformationWS;
import com.ngbilling.core.server.persistence.dao.payment.PaymentMethodDAO;
import com.ngbilling.core.server.persistence.dao.payment.PaymentMethodTypeDAO;
import com.ngbilling.core.server.persistence.dto.metafield.GroupCustomizedEntity;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.persistence.dto.util.EntityType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author khobab
 */
@Entity
@Table(name = "payment_information")
public class PaymentInformationDTO extends GroupCustomizedEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // transient fields
    boolean blacklisted = false;
    private Integer id;
    private Integer processingOrder;
    private Integer deleted = 0;
    private UserDTO user;
    private PaymentMethodTypeDTO paymentMethodType;
    private int versionNum;
    private PaymentMethodDTO paymentMethod;
    @Autowired
    private PaymentMethodDAO paymentMethodDAO;
    @Autowired
    private PaymentMethodTypeDAO paymentMethodTypeDAO;

    public PaymentInformationDTO() {
        // default constructor
    }

    public PaymentInformationDTO(Integer processingOrder, UserDTO user, PaymentMethodTypeDTO paymentMethodTye) {
        this.processingOrder = processingOrder;
        this.user = user;
        this.paymentMethodType = paymentMethodTye;
    }

    public PaymentInformationDTO(PaymentInformationWS ws, Integer entityId) {
        if (ws.getId() != null) {
            setId(ws.getId());
        }

        setProcessingOrder(ws.getProcessingOrder());
        setPaymentMethodType(paymentMethodTypeDAO.findById(ws.getPaymentMethodTypeId()).get());
        setPaymentMethod(paymentMethodDAO.findById(ws.getPaymentMethodId()).get());

        if (ws.getPaymentMethodId() != null) {
            setPaymentMethod(new PaymentMethodDTO(ws.getPaymentMethodId()));
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_information_GEN")
    @SequenceGenerator(
            name = "payment_information_GEN",
            allocationSize = 1
    )

    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserDTO getUser() {
        return this.user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    public PaymentMethodTypeDTO getPaymentMethodType() {
        return this.paymentMethodType;
    }

    public void setPaymentMethodType(PaymentMethodTypeDTO paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    @Transient
    public PaymentMethodDTO getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Column(name = "processing_order")
    public Integer getProcessingOrder() {
        return processingOrder;
    }

    public void setProcessingOrder(Integer processingOrder) {
        this.processingOrder = processingOrder;
    }

    @Column(name = "deleted")
    public Integer getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Version
    @Column(name = "OPTLOCK")
    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

    @Transient
    public EntityType[] getCustomizedEntityType() {
        return new EntityType[]{EntityType.PAYMENT_METHOD_TYPE};
    }

    @Transient
    public boolean isBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(boolean blacklisted) {
        this.blacklisted = blacklisted;
    }

    @Transient
    public PaymentInformationDTO getDTO() {
        PaymentInformationDTO paymentInformation = new PaymentInformationDTO();
        paymentInformation.setId(this.id);
        paymentInformation.setPaymentMethod(this.paymentMethod);
        paymentInformation.setPaymentMethodType(this.paymentMethodType);
        paymentInformation.setProcessingOrder(this.processingOrder);
        paymentInformation.setUser(this.user);
        return paymentInformation;
    }

    @Transient
    public boolean isNumberObsucred(String ccNumber) {
        return ccNumber != null && ccNumber.contains("*");
    }

    @Transient
    public PaymentInformationDTO getSaveableDTO() {
        PaymentInformationDTO paymentInformation = new PaymentInformationDTO();
        paymentInformation.setPaymentMethod(this.paymentMethod);
        paymentInformation.setPaymentMethodType(this.paymentMethodType);
        paymentInformation.setProcessingOrder(this.processingOrder);
        //paymentInformation.setPayments(this.payments);
        //paymentInformation.setUser(this.user);
        return paymentInformation;
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
