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
package com.ngbilling.core.server.persistence.dto.process;

import java.util.Date;

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

import com.ngbilling.core.server.persistence.dto.user.UserDTO;

@Entity
@Table(name = "process_run_user")
// No cache
public class ProcessRunUserDTO implements java.io.Serializable {

    public static final Integer STATUS_FAILED = 0;
    public static final Integer STATUS_SUCCEEDED = 1;
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private ProcessRunDTO processRun;
    private UserDTO user;
    private Integer status;
    private Date created;

    private int versionNum;

    public ProcessRunUserDTO() {
    }

    public ProcessRunUserDTO(int id, ProcessRunDTO processRun, UserDTO user, Integer status, Date created) {
        this.id = id;
        this.processRun = processRun;
        this.user = user;
        this.status = status;
        this.created = created;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "process_run_user_GEN")
    @SequenceGenerator(
            name = "process_run_user_GEN",
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
    @JoinColumn(name = "process_run_id")
    public ProcessRunDTO getProcessRun() {
        return this.processRun;
    }

    public void setProcessRun(ProcessRunDTO processRun) {
        this.processRun = processRun;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public UserDTO getUser() {
        return this.user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "created", nullable = false, length = 29)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Version
    @Column(name = "OPTLOCK")
    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append(" ProcessRunUserDTO: id: ")
                .append(id)
                .append(" user: ")
                .append(user)
                .append(" created: ")
                .append(created)
                .append(" status: ")
                .append(status);

        return ret.toString();
    }

}
