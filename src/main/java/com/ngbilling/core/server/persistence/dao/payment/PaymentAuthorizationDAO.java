package com.ngbilling.core.server.persistence.dao.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.payment.PaymentAuthorizationDTO;

@Repository
public interface PaymentAuthorizationDAO extends JpaRepository<PaymentAuthorizationDTO, Integer>{

}
