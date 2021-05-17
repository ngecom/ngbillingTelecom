/*
 jBilling - The Enterprise Open Source Billing System
 Copyright (C) 2003-2011 Enterprise jBilling Software Ltd. and Emiliano Conde

 This file is part of jbilling.

 jbilling is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 jbilling is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with jbilling.  If not, see <http://www.gnu.org/licenses/>.

 This source was modified by Web Data Technologies LLP (www.webdatatechnologies.in) since 15 Nov 2015.
 You may download the latest source from webdataconsulting.github.io.

 */
package com.ngbilling.core.server.persistence.dto.notification;

import com.ngbilling.core.server.persistence.dto.util.AbstractDescription;
import com.ngbilling.core.server.persistence.dto.util.NotificationCategoryDTO;
import com.ngbilling.core.server.util.ServerConstants;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "notification_message_type")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class NotificationMessageTypeDTO extends AbstractDescription implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private NotificationCategoryDTO category;
    private Set<NotificationMessageDTO> notificationMessages = new HashSet<NotificationMessageDTO>(
            0);
    private int versionNum;

    public NotificationMessageTypeDTO() {
    }

    public NotificationMessageTypeDTO(int id) {
        this.id = id;
    }

    public NotificationMessageTypeDTO(int id, NotificationCategoryDTO category,
                                      Set<NotificationMessageDTO> notificationMessages) {
        this.id = id;
        this.category = category;
        this.notificationMessages = notificationMessages;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_message_type_GEN")
    @SequenceGenerator(
            name = "notification_message_type_GEN",
            allocationSize = 1
    )
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    public NotificationCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(NotificationCategoryDTO category) {
        this.category = category;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "notificationMessageType")
    public Set<NotificationMessageDTO> getNotificationMessages() {
        return this.notificationMessages;
    }

    public void setNotificationMessages(
            Set<NotificationMessageDTO> notificationMessages) {
        this.notificationMessages = notificationMessages;
    }

    @Version
    @Column(name = "OPTLOCK")
    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

    @Transient
    protected String getTable() {
        return ServerConstants.TABLE_NOTIFICATION_MESSAGE_TYPE;
    }

}
