package com.ngbilling.core.server.persistence.dao.invoice;

import com.ngbilling.core.server.persistence.dto.invoice.InvoiceDeliveryMethodDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDeliveryMethodDAO extends JpaRepository<InvoiceDeliveryMethodDTO, Integer> {

}
