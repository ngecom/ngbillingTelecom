package com.ngbilling.core.server.persistence.dao.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngbilling.core.server.persistence.dto.payment.PaymentMethodTypeDTO;

public interface PaymentMethodTypeDAO extends JpaRepository<PaymentMethodTypeDTO, Integer> {

}
