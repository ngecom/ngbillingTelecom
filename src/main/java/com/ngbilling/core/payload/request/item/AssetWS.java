/*
 * JBILLING CONFIDENTIAL
 * _____________________
 *
 * [2003] - [2013] Enterprise jBilling Software Ltd.
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
package com.ngbilling.core.payload.request.item;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Gerhard
 * @see com.sapienter.jbilling.server.item.db.AssetDTO
 * @since 15/04/13
 */
public class AssetWS {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotNull(message = "validation.error.null.asset.identifier")
    @Size(min = 1, max = 200, message = "validation.error.size,1,200")
    private String identifier;
    private Date createDatetime;
    private String status;
    @NotNull(message = "validation.error.null.asset.status")
    private Integer assetStatusId;
    @NotNull(message = "validation.error.null.item")
    private Integer itemId;
    private Integer orderLineId;
    private int deleted;
    @Size(min = 0, max = 1000, message = "validation.error.length.max,1000")
    private String notes;
    private Integer entityId;
    private Integer[] containedAssetIds;
    private Integer groupId;
    private boolean global = false;

    private Set<Integer> entities = new HashSet<>(0);

    private AssetAssignmentWS[] assignments;

    public AssetWS() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer[] getContainedAssetIds() {
        return containedAssetIds;
    }

    public void setContainedAssetIds(Integer[] containedAssetIds) {
        this.containedAssetIds = containedAssetIds;
    }

    public Integer getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(Integer orderLineId) {
        this.orderLineId = orderLineId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAssetStatusId() {
        return assetStatusId;
    }

    public void setAssetStatusId(Integer assetStatusId) {
        this.assetStatusId = assetStatusId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public Set<Integer> getEntities() {
        return entities;
    }

    public void setEntities(Set<Integer> entities) {
        this.entities = entities;
    }

    public AssetAssignmentWS[] getAssignments() {
        return assignments;
    }

    public void setAssignments(AssetAssignmentWS[] assignments) {
        this.assignments = assignments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetWS assetWS = (AssetWS) o;

        if (identifier != null ? !identifier.equals(assetWS.identifier) : assetWS.identifier != null) return false;
        if (itemId != null ? !itemId.equals(assetWS.itemId) : assetWS.itemId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = identifier != null ? identifier.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AssetWS{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", createDatetime=" + createDatetime +
                ", status='" + status + '\'' +
                ", assetStatusId=" + assetStatusId +
                ", itemId=" + itemId +
                ", orderLineId=" + orderLineId +
                ", deleted=" + deleted +
                ", notes='" + notes + '\'' +
                ", entityId=" + entityId +
                ", containedAssetIds=" + Arrays.toString(containedAssetIds) +
                ", groupId=" + groupId +
                '}';
    }
}
