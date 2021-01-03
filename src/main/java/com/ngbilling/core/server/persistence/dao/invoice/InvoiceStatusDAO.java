package com.ngbilling.core.server.persistence.dao.invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.invoice.InvoiceStatusDTO;

@Repository
public interface InvoiceStatusDAO extends JpaRepository<InvoiceStatusDTO, Integer>{

	public InvoiceStatusDTO findByStatusValue(Integer statusId);
}
