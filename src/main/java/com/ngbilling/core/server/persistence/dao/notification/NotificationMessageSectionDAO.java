package com.ngbilling.core.server.persistence.dao.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.notification.NotificationMessageSectionDTO;

@Repository
public interface NotificationMessageSectionDAO extends JpaRepository<NotificationMessageSectionDTO, Integer>{

}