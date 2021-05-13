package com.ngbilling.core.server.persistence.dao.invoice;

import com.ngbilling.core.server.persistence.dto.invoice.InvoiceStatusDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceStatusDAO extends JpaRepository<InvoiceStatusDTO, Integer> {

    public InvoiceStatusDTO findByStatusValue(Integer statusId);
}
