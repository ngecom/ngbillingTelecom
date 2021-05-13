package com.ngbilling.core.server.persistence.dao.report;

import com.ngbilling.core.server.persistence.dto.report.ReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDAO extends JpaRepository<ReportDTO, Integer> {

}
