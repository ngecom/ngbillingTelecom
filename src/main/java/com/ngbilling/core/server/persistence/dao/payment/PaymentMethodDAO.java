package com.ngbilling.core.server.persistence.dao.payment;

import com.ngbilling.core.server.persistence.dto.payment.PaymentMethodDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodDAO extends JpaRepository<PaymentMethodDTO, Integer> {

}
