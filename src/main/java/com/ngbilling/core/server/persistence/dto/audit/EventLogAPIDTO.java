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
package com.ngbilling.core.server.persistence.dto.audit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "event_api_log")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class EventLogAPIDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_logApi_GEN")
    @SequenceGenerator(
            name = "event_logApi_GEN",
            allocationSize = 1
    )
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "request_datetime", nullable = false, length = 29)
    private Date requestDatetime;

    @Column(name = "response_datetime", nullable = false, length = 29)
    private Date responseDatetime;

    @Column(name = "user_name", length = 50)
    private String userName;

    @Column(name = "api_name", length = 100)
    private String apiName;

    @Column(name = "request", length = 5000)
    private String request;

    @Column(name = "response", length = 5000)
    private String response;

    @Column(name = "connectionUrl", length = 500)
    private String connectionUrl;

    @Column(name = "db_Value", length = 1000)
    private String dbValue;

    public EventLogAPIDTO() {
    }

    public Date getRequestDatetime() {
        return requestDatetime;
    }

    public void setRequestDatetime(Date requestDatetime) {
        this.requestDatetime = requestDatetime;
    }

    public Date getResponseDatetime() {
        return responseDatetime;
    }

    public void setResponseDatetime(Date responseDatetime) {
        this.responseDatetime = responseDatetime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getDbValue() {
        return dbValue;
    }

    public void setDbValue(String dbValue) {
        this.dbValue = dbValue;
    }
}


