package com.ngbilling.core.server.persistence.dto.payment;

import static org.springframework.util.ObjectUtils.nullSafeEquals;
import static org.springframework.util.ObjectUtils.nullSafeHashCode;

import java.io.Serializable;
import java.util.Comparator;

public class PaymentInformationWS implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer processingOrder;
	private Integer paymentMethodTypeId;
	private Integer paymentMethodId;
	
	public PaymentInformationWS() {
		
	}
	
	public PaymentInformationWS(Integer id, Integer processingOrder, Integer paymentMethodTypeId) {
		this.id = id;
		this.processingOrder = processingOrder;
		this.paymentMethodTypeId = paymentMethodTypeId;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProcessingOrder() {
		return processingOrder;
	}

	public void setProcessingOrder(Integer processingOrder) {
		this.processingOrder = processingOrder;
	}

	public Integer getPaymentMethodTypeId() {
		return paymentMethodTypeId;
	}

	public void setPaymentMethodTypeId(Integer paymentMethodTypeId) {
		this.paymentMethodTypeId = paymentMethodTypeId;
	}
	
	public Integer getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(Integer paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	
	public static Comparator<PaymentInformationWS> ProcessingOrderComparator =
            (o1, o2) -> {
                Integer o1ProcessingOrder = o1.getProcessingOrder();
                Integer o2ProcessingOrder = o2.getProcessingOrder();

                return o1ProcessingOrder.compareTo(o2ProcessingOrder);
            };

	@Override
	public String toString() {
		return "PaymentInformationWS{" +
				"id=" + id +
				", processingOrder=" + processingOrder +
				", paymentMethodTypeId=" + paymentMethodTypeId +
				", paymentMethodId=" + paymentMethodId +
				'}';
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentInformationWS that = (PaymentInformationWS) o;

        if (! nullSafeEquals(paymentMethodId, that.paymentMethodId)) return false;
        if (! nullSafeEquals(paymentMethodTypeId, that.paymentMethodTypeId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nullSafeHashCode(paymentMethodTypeId);
        result = 31 * result + nullSafeHashCode(paymentMethodId);
        return result;
    }
}
