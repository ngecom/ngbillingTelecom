package com.ngbilling.core.server.persistence.dao.payment;

import com.ngbilling.core.server.persistence.dto.payment.PaymentResultDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentResultDAO extends JpaRepository<PaymentResultDTO, Integer> {

}
