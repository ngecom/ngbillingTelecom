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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;

import com.ngbilling.core.payload.request.util.NotificationMediumType;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;

@Entity
@Table(name = "notification_message")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NotificationMessageDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private NotificationMessageTypeDTO notificationMessageType;
    private CompanyDTO entity;
    private LanguageDTO language;
    private short useFlag;
    private Set<NotificationMessageSectionDTO> notificationMessageSections = new HashSet<NotificationMessageSectionDTO>(
            0);
    private int versionNum;

    private Integer includeAttachment;
    private String attachmentDesign;
    private String attachmentType;

    private Integer notifyAdmin;
    private Integer notifyPartner;
    private Integer notifyParent;
    private Integer notifyAllParents;
    private List<NotificationMediumType> mediumTypes;

    public NotificationMessageDTO() {
        this.notifyAdmin = 0;
        this.notifyPartner = 0;
        this.notifyParent = 0;
        this.notifyAllParents = 0;
    }

    public NotificationMessageDTO(int id, CompanyDTO entity,
                                  LanguageDTO language, short useFlag) {
        this.id = id;
        this.entity = entity;
        this.language = language;
        this.useFlag = useFlag;
        this.notifyAdmin = 0;
        this.notifyPartner = 0;
        this.notifyPartner = 0;
        this.notifyAllParents = 0;
    }

    public NotificationMessageDTO(int id,
                                  NotificationMessageTypeDTO notificationMessageType,
                                  CompanyDTO entity, LanguageDTO language, short useFlag,
                                  Set<NotificationMessageSectionDTO> notificationMessageSections) {
        this.id = id;
        this.notificationMessageType = notificationMessageType;
        this.entity = entity;
        this.language = language;
        this.useFlag = useFlag;
        this.notificationMessageSections = notificationMessageSections;
        this.notifyAdmin = 0;
        this.notifyPartner = 0;
        this.notifyPartner = 0;
        this.notifyAllParents = 0;
    }

    @Column(name = "include_attachment", nullable = true)
    public Integer getIncludeAttachment() {
        return includeAttachment;
    }

    public void setIncludeAttachment(Integer includeAttachment) {
        this.includeAttachment = includeAttachment;
    }

    @Column(name = "attachment_type", nullable = true)
    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    @Column(name = "attachment_design", nullable = true)
    public String getAttachmentDesign() {
        return attachmentDesign;
    }

    public void setAttachmentDesign(String attachmentDesign) {
        this.attachmentDesign = attachmentDesign;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_message_GEN")
    @SequenceGenerator(
            name = "notification_message_GEN",
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
    @JoinColumn(name = "type_id")
    public NotificationMessageTypeDTO getNotificationMessageType() {
        return this.notificationMessageType;
    }

    public void setNotificationMessageType(
            NotificationMessageTypeDTO notificationMessageType) {
        this.notificationMessageType = notificationMessageType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false)
    public CompanyDTO getEntity() {
        return this.entity;
    }

    public void setEntity(CompanyDTO entity) {
        this.entity = entity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    public LanguageDTO getLanguage() {
        return this.language;
    }

    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }

    @Column(name = "use_flag", nullable = false)
    public short getUseFlag() {
        return this.useFlag;
    }

    public void setUseFlag(short useFlag) {
        this.useFlag = useFlag;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "notificationMessage")
    @OrderBy(clause = "section")
    @Fetch(FetchMode.JOIN)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    public Set<NotificationMessageSectionDTO> getNotificationMessageSections() {
        return this.notificationMessageSections;
    }

    public void setNotificationMessageSections(
            Set<NotificationMessageSectionDTO> notificationMessageSections) {
        this.notificationMessageSections = notificationMessageSections;
    }

    @Version
    @Column(name = "OPTLOCK")
    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Column(name = "notify_admin", nullable = false)
    public Integer getNotifyAdmin() {
        return this.notifyAdmin;
    }

    public void setNotifyAdmin(Integer notifyAdmin) {
        this.notifyAdmin = notifyAdmin;
    }

    @Column(name = "notify_partner", nullable = false)
    public Integer getNotifyPartner() {
        return this.notifyPartner;
    }

    public void setNotifyPartner(Integer notifyPartner) {
        this.notifyPartner = notifyPartner;
    }

    @Column(name = "notify_parent", nullable = false)
    public Integer getNotifyParent() {
        return this.notifyParent;
    }

    public void setNotifyParent(Integer notifyParent) {
        this.notifyParent = notifyParent;
    }

    @Column(name = "notify_all_parents", nullable = false)
    public Integer getNotifyAllParents() {
        return this.notifyAllParents;
    }

    public void setNotifyAllParents(Integer notifyAllParents) {
        this.notifyAllParents = notifyAllParents;
    }

    @ElementCollection(targetClass = NotificationMediumType.class)
    @CollectionTable(name = "notification_medium_type", joinColumns = @JoinColumn(name = "notification_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "medium_type")
    public List<NotificationMediumType> getMediumTypes() {
        return mediumTypes;
    }

    public void setMediumTypes(List<NotificationMediumType> mediumTypes) {
        this.mediumTypes = mediumTypes;
    }
}
