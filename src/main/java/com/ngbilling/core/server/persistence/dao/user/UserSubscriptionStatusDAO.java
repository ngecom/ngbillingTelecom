package com.ngbilling.core.server.persistence.dao.user;

import com.ngbilling.core.server.persistence.dto.user.SubscriberStatusDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSubscriptionStatusDAO extends JpaRepository<SubscriberStatusDTO, Integer> {

}
 