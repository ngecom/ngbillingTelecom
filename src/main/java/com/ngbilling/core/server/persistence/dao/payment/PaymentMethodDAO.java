package com.ngbilling.core.server.persistence.dao.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngbilling.core.server.persistence.dto.payment.PaymentMethodDTO;

public interface PaymentMethodDAO extends JpaRepository<PaymentMethodDTO, Integer> {

}
