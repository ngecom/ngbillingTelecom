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
package com.ngbilling.core.server.persistence.dto.item;

import com.ngbilling.core.payload.request.item.AssetStatusDTOEx;
import com.ngbilling.core.server.persistence.dto.util.AbstractDescription;
import com.ngbilling.core.server.util.ServerConstants;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Assets linked to a product always have a status.
 * Asset statuses are configured per ItemType.
 * Each ItemType must have one 'default' status which gets assigned on asset creation
 * and one 'order saved' status which get assigned when the asset gets linked to a product.
 *
 * @author Gerhard
 * @since 15/04/13
 */
@Entity
@Table(name = "asset_status")
@NamedQueries({
        @NamedQuery(name = "AssetStatusDTO.findForItemType",
                query = "select at from AssetStatusDTO at where at.itemType.id = :item_type_id " +
                        "and at.deleted=0 " +
                        "order by id asc"),
        @NamedQuery(name = "AssetStatusDTO.findForItemTypeNotInternal",
                query = "select at from AssetStatusDTO at where at.itemType.id = :item_type_id " +
                        "and at.deleted=0 and at.isInternal=0 " +
                        "order by id asc"),
        @NamedQuery(name = "AssetStatusDTO.findDefaultStatusForItem",
                query = "select at from AssetStatusDTO at " +
                        "join at.itemType.items it " +
                        "where it.id = :item_id " +
                        "and at.deleted=0 and at.isDefault=1 "),
        @NamedQuery(name = "AssetStatusDTO.findAvailableStatusForItem",
                query = "select at from AssetStatusDTO at " +
                        "join at.itemType.items it " +
                        "where it.id = :item_id " +
                        "and at.deleted=0 and at.isAvailable=1 ")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AssetStatusDTO extends AbstractDescription {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private ItemTypeDTO itemType;
    private int isDefault;
    private int isOrderSaved;
    private int isAvailable;
    private int isInternal;
    private int versionNum;
    private int deleted;

    public AssetStatusDTO() {

    }

    public AssetStatusDTO(int id) {
        this.id = id;
    }

    public AssetStatusDTO(AssetStatusDTOEx ex) {
        this.id = ex.getId();
        this.isDefault = ex.getIsDefault();
        this.isOrderSaved = ex.getIsOrderSaved();
        this.isAvailable = ex.getIsAvailable();
        this.isInternal = ex.getIsInternal();
        setDescription(ex.getDescription());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_status_GEN")
    @SequenceGenerator(
            name = "asset_status_GEN",
            allocationSize = 1
    )
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "deleted", nullable = false)
    public int getDeleted() {
        return this.deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_type_id")
    public ItemTypeDTO getItemType() {
        return this.itemType;
    }

    public void setItemType(ItemTypeDTO itemType) {
        this.itemType = itemType;
    }

    @Column(name = "is_internal")
    public int getIsInternal() {
        return this.isInternal;
    }

    public void setIsInternal(int isInternal1) {
        this.isInternal = isInternal1;
    }

    @Column(name = "is_order_saved")
    public int getIsOrderSaved() {
        return this.isOrderSaved;
    }

    public void setIsOrderSaved(int isOrderSaved) {
        this.isOrderSaved = isOrderSaved;
    }

    @Column(name = "is_default")
    public int getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    @Column(name = "is_available")
    public int getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Transient
    protected String getTable() {
        return ServerConstants.TABLE_ASSET_STATUS;
    }

    @Version
    @Column(name = "optlock")
    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Override
    public String toString() {
        return "AssetStatusDTO{" +
                "id=" + id +
                ", itemType=" + (itemType != null ? itemType.getId() : null) +
                ", isDefault=" + isDefault +
                ", isOrderSaved=" + isOrderSaved +
                ", isAvailable=" + isAvailable +
                ", isInternal=" + isInternal +
                ", versionNum=" + versionNum +
                ", deleted=" + deleted +
                '}';
    }
}