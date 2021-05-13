package com.ngbilling.core.server.persistence.dao.payment;

import com.ngbilling.core.server.persistence.dto.payment.PaymentMethodTemplateDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodTemplateDAO extends JpaRepository<PaymentMethodTemplateDTO, Integer> {

    @Query("select u from PaymentMethodTemplateDTO u where u.templateName = :templateName")
    public PaymentMethodTemplateDTO findByName(String templateName);
}

