package com.ngbilling.core.server.persistence.dao.notification;

import com.ngbilling.core.server.persistence.dto.notification.NotificationMessageSectionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationMessageSectionDAO extends JpaRepository<NotificationMessageSectionDTO, Integer> {

}