/*
 * JBILLING CONFIDENTIAL
 * _____________________
 *
 * [2003] - [2012] Enterprise jBilling Software Ltd.
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Enterprise jBilling Software.
 * The intellectual and technical concepts contained
 * herein are proprietary to Enterprise jBilling Software
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden.
 */
package com.ngbilling.core.server.persistence.dto.mediation;

import com.ngbilling.core.payload.request.configuration.MediationRecordWS;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "mediation_record")
// no cache : it is hardly ever re-read 
public class MediationRecordDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String key;
    private Date started;
    private MediationProcess process;
    private MediationRecordStatusDTO recordStatus;
    private Collection<MediationRecordLineDTO> lines = new ArrayList<MediationRecordLineDTO>();
    private int optlock;

    protected MediationRecordDTO() {
    }

    public MediationRecordDTO(String key, Date started, MediationProcess process, MediationRecordStatusDTO recordStatus) {
        this.key = key;
        this.started = started;
        this.process = process;
        this.recordStatus = recordStatus;
    }

    public MediationRecordDTO(MediationRecordWS ws, MediationProcess process, MediationRecordStatusDTO recordStatus,
                              Collection<MediationRecordLineDTO> lines) {

        this.id = ws.getId();
        this.key = ws.getKey();
        this.started = ws.getStarted();
        this.process = process;
        this.recordStatus = recordStatus;
        this.lines = lines;
    }

   @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mediation_record_GEN")
    @SequenceGenerator(
            name = "mediation_record_GEN",
            allocationSize = 1
    )
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "id_key", nullable = false)
    public String getKey() {
        return key;
    }

    protected void setKey(String key) {
        this.key = key;
    }

    @Column(name = "start_datetime")
    public Date getStarted() {
        return started;
    }

    protected void setStarted(Date started) {
        this.started = started;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mediation_process_id")
    public MediationProcess getProcess() {
        return process;
    }

    public void setProcess(MediationProcess process) {
        this.process = process;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    public MediationRecordStatusDTO getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(MediationRecordStatusDTO recordStatus) {
        this.recordStatus = recordStatus;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "record")
    public Collection<MediationRecordLineDTO> getLines() {
        return lines;
    }

    public void setLines(Collection<MediationRecordLineDTO> lines) {
        this.lines = lines;
    }

    @Version
    @Column(name = "OPTLOCK")
    public int getOptlock() {
        return optlock;
    }

    protected void setOptlock(int optlock) {
        this.optlock = optlock;
    }
}
