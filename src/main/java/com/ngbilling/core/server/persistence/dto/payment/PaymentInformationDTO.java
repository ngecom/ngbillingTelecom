package com.ngbilling.core.server.persistence.dto.payment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Autowired;

import com.ngbilling.core.payload.request.payment.PaymentInformationWS;
import com.ngbilling.core.server.persistence.dao.payment.PaymentMethodDAO;
import com.ngbilling.core.server.persistence.dao.payment.PaymentMethodTypeDAO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.persistence.dto.util.EntityType;

/**
 * 
 * @author khobab
 *
 */
@Entity
@TableGenerator(
        name="payment_information_GEN",
        table="jbilling_seqs",
        pkColumnName = "name",
        valueColumnName = "next_id",
        pkColumnValue="payment_information",
        allocationSize = 10
        )
@Table(name = "payment_information")
public class PaymentInformationDTO  implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	// transient fields
	boolean blacklisted = false;
	
	public PaymentInformationDTO() {
		// default constructor
	}
	
	public PaymentInformationDTO(Integer processingOrder, UserDTO user, PaymentMethodTypeDTO paymentMethodTye) {
		this.processingOrder = processingOrder;
		this.user = user;
		this.paymentMethodType = paymentMethodTye;
	}
	
	public PaymentInformationDTO(PaymentInformationWS ws, Integer entityId) {
		if(ws.getId() != null) {
			setId(ws.getId());
		}
		
		setProcessingOrder(ws.getProcessingOrder());
		setPaymentMethodType(paymentMethodTypeDAO.findById(ws.getPaymentMethodTypeId()).get());
		setPaymentMethod(paymentMethodDAO.findById(ws.getPaymentMethodId()).get());
		
		if(ws.getPaymentMethodId() != null) {
			setPaymentMethod(new PaymentMethodDTO(ws.getPaymentMethodId()));
		}
		
	}
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE, generator = "payment_information_GEN")
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

    public void setPaymentMethodType(PaymentMethodTypeDTO paymentMethodType){
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
    
    public void setVersionNum(int versionNum){
    	this.versionNum = versionNum;
    }

	@Transient
	public EntityType[] getCustomizedEntityType() {
		return new EntityType[] { EntityType.PAYMENT_METHOD_TYPE };
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
}
