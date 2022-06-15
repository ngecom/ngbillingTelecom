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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "notification_message_line")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NotificationMessageLineDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private NotificationMessageSectionDTO notificationMessageSection;
    private String content;
    private int versionNum;

    public NotificationMessageLineDTO() {
    }

    public NotificationMessageLineDTO(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public NotificationMessageLineDTO(int id,
                                      NotificationMessageSectionDTO notificationMessageSection,
                                      String content) {
        this.id = id;
        this.notificationMessageSection = notificationMessageSection;
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_message_line_GEN")
    @SequenceGenerator(
            name = "notification_message_line_GEN",
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
    @JoinColumn(name = "message_section_id")
    public NotificationMessageSectionDTO getNotificationMessageSection() {
        return this.notificationMessageSection;
    }

    public void setNotificationMessageSection(
            NotificationMessageSectionDTO notificationMessageSection) {
        this.notificationMessageSection = notificationMessageSection;
    }

    @Column(name = "content", nullable = false, length = 1000)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Version
    @Column(name = "OPTLOCK")
    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

}
