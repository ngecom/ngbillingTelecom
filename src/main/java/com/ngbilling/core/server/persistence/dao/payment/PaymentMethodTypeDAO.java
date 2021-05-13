package com.ngbilling.core.server.persistence.dao.payment;

import com.ngbilling.core.server.persistence.dto.payment.PaymentMethodTypeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodTypeDAO extends JpaRepository<PaymentMethodTypeDTO, Integer> {

}
