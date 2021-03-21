package com.ngbilling.core.server.persistence.dao.mediation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ngbilling.core.server.persistence.dto.mediation.MediationRecordLineDTO;

public interface MediationRecordLineDAO extends JpaRepository<MediationRecordLineDTO, Integer>{
	
	@Query(" select a " +
            "   from MediationRecordLineDTO a " +
            "  where a.orderLine.purchaseOrder.id = ?1 " +
            "    and a.orderLine.deleted = 0 " +
            "  order by a.orderLine.id, a.id")
    public List<MediationRecordLineDTO> findByOrder(Integer orderId);
	

	    /**
	     * Find all MediationRecordLineDTO events incorporated into the given
	     * invoice.
	     *
	     * @param invoiceId invoice id
	     * @return list of mediation events, empty list if none found
	     */
	@Query("select recordLine " +
	            "from MediationRecordLineDTO recordLine " +
	            "    inner join recordLine.orderLine.purchaseOrder as purchaseOrder " +
	            "    inner join purchaseOrder.orderProcesses orderProcess " +
	            "where orderProcess.invoice.id = ?1")
	public List<MediationRecordLineDTO> findByInvoice(Integer invoiceId);

}
