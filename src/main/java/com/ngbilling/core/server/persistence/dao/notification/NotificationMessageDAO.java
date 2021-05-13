package com.ngbilling.core.server.persistence.dao.notification;

import com.ngbilling.core.server.persistence.dto.notification.NotificationMessageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationMessageDAO extends JpaRepository<NotificationMessageDTO, Integer> {

    @Query("select u from NotificationMessageDTO u where u.entity.id = :entityId and u.notificationMessageType.id = :typeId "
            + "and u.language.id = :language")
    public NotificationMessageDTO findIt(Integer typeId, Integer entityId,
                                         Integer language);


}
