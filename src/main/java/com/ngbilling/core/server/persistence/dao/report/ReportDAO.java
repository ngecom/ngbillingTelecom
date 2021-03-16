package com.ngbilling.core.server.persistence.dao.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.report.ReportDTO;

@Repository
public interface ReportDAO extends JpaRepository<ReportDTO, Integer>{

}
