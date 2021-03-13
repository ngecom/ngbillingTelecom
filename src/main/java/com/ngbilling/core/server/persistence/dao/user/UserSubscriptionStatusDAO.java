package com.ngbilling.core.server.persistence.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngbilling.core.server.persistence.dto.user.SubscriberStatusDTO;

public interface UserSubscriptionStatusDAO extends JpaRepository<SubscriberStatusDTO, Integer> {

}
 