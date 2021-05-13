package com.ngbilling.core.server.persistence.dao.payment;

import com.ngbilling.core.server.persistence.dto.payment.PaymentAuthorizationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentAuthorizationDAO extends JpaRepository<PaymentAuthorizationDTO, Integer> {

}
