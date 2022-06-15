package com.ngbilling.core.server.persistence.dao.invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.invoice.InvoiceDTO;

@Repository
public interface InvoiceDAO extends JpaRepository<InvoiceDTO, Integer> {

}
