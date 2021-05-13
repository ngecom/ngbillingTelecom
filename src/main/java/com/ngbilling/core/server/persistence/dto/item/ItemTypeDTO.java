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
package com.ngbilling.core.server.persistence.dto.item;


import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.util.AbstractDescription;
import com.ngbilling.core.server.util.ServerConstants;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@TableGenerator(
        name = "item_type_GEN",
        table = "jbilling_seqs",
        pkColumnName = "name",
        valueColumnName = "next_id",
        pkColumnValue = "item_type",
        allocationSize = 100
)
@Table(name = "item_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemTypeDTO extends AbstractDescription implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private CompanyDTO entity;
    private Set<CompanyDTO> entities = new HashSet<CompanyDTO>(0);
    private String description;
    private int orderLineTypeId;
    private boolean internal;
    private boolean global = false;
    private Set<ItemDTO> items = new HashSet<ItemDTO>(0);
    private Set<ItemDTO> excludedItems = new HashSet<ItemDTO>();
    private Set<AssetStatusDTO> assetStatuses = new HashSet<AssetStatusDTO>(0);
    private Integer allowAssetManagement;
    private String assetIdentifierLabel;
    private int versionNum;
    private ItemTypeDTO parent;

    private boolean onePerOrder = false;
    private boolean onePerCustomer = false;

    public ItemTypeDTO() {
    }

    public ItemTypeDTO(int id) {
        this.id = id;
    }

    public ItemTypeDTO(int id, CompanyDTO entity, int orderLineTypeId) {
        this.id = id;
        this.entity = entity;
        this.orderLineTypeId = orderLineTypeId;
    }

    public ItemTypeDTO(int id, CompanyDTO entity, String description, int orderLineTypeId, Set<ItemDTO> items) {
        this.id = id;
        this.entity = entity;
        this.description = description;
        this.orderLineTypeId = orderLineTypeId;
        this.items = items;
    }

    @Transient
    protected String getTable() {
        return ServerConstants.TABLE_ITEM_TYPE;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "item_type_GEN")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false)
    public CompanyDTO getEntity() {
        return this.entity;
    }

    public void setEntity(CompanyDTO entity) {
        this.entity = entity;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "item_type_entity_map",
            joinColumns = {@JoinColumn(name = "item_type_id", updatable = true)},
            inverseJoinColumns = {@JoinColumn(name = "entity_id", updatable = true)})
    public Set<CompanyDTO> getEntities() {
        return this.entities;
    }

    public void setEntities(Set<CompanyDTO> entities) {
        this.entities = entities;
    }

    @Column(name = "allow_asset_management")
    public Integer getAllowAssetManagement() {
        return allowAssetManagement;
    }

    public void setAllowAssetManagement(Integer allowAssetManagement) {
        this.allowAssetManagement = allowAssetManagement;
    }

    @Column(name = "asset_identifier_label")
    public String getAssetIdentifierLabel() {
        return assetIdentifierLabel;
    }

    public void setAssetIdentifierLabel(String assetIdentifierLabel) {
        this.assetIdentifierLabel = assetIdentifierLabel;
    }

    @Column(name = "description", length = 100)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "order_line_type_id", nullable = false)
    public int getOrderLineTypeId() {
        return this.orderLineTypeId;
    }

    public void setOrderLineTypeId(int orderLineTypeId) {
        this.orderLineTypeId = orderLineTypeId;
    }

    @Column(name = "internal", nullable = false)
    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    @Column(name = "global", nullable = false, updatable = true)
    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "item_type_map",
            joinColumns = {@JoinColumn(name = "type_id", updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "item_id", updatable = false)}
    )
    public Set<ItemDTO> getItems() {
        return this.items;
    }

    public void setItems(Set<ItemDTO> items) {
        this.items = items;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "item_type_exclude_map",
            joinColumns = {@JoinColumn(name = "type_id", updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "item_id", updatable = false)}
    )
    public Set<ItemDTO> getExcludedItems() {
        return excludedItems;
    }

    public void setExcludedItems(Set<ItemDTO> excludedItems) {
        this.excludedItems = excludedItems;
    }

    @Version
    @Column(name = "OPTLOCK")
    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "itemType")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public Set<AssetStatusDTO> getAssetStatuses() {
        return this.assetStatuses;
    }

    public void setAssetStatuses(Set<AssetStatusDTO> assetStatuses) {
        this.assetStatuses = assetStatuses;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public ItemTypeDTO getParent() {
        return this.parent;
    }

    public void setParent(ItemTypeDTO parent) {
        this.parent = parent;
    }

    @Column(name = "one_per_order", nullable = false)
    public boolean isOnePerOrder() {
        return onePerOrder;
    }

    public void setOnePerOrder(boolean onePerOrder) {
        this.onePerOrder = onePerOrder;

        if (onePerOrder) {
            this.onePerCustomer = false;
        }
    }

    @Column(name = "one_per_customer", nullable = false)
    public boolean isOnePerCustomer() {
        return onePerCustomer;
    }

    public void setOnePerCustomer(boolean onePerCustomer) {
        this.onePerCustomer = onePerCustomer;

        if (onePerCustomer) {
            this.onePerOrder = false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemTypeDTO that = (ItemTypeDTO) o;

        if (id != that.id) return false;
        if (orderLineTypeId != that.orderLineTypeId) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        //if (entity != null ? !entity.equals(that.entity) : that.entity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        //result = 31 * result + (entity != null ? entity.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + orderLineTypeId;
        return result;
    }

    @Override
    public String toString() {
        return "ItemTypeDTO{"
                + "id=" + id
                + ", orderLineTypeId=" + orderLineTypeId
                + ", description='" + description + '\''
                + ", parent=" + (null != parent ? parent.getId() : null)
                + '}';
    }

    /**
     * Load some dependent object so object can be used disconnected from session.
     * Note that items and excluded items are not loaded.
     */
    public void touch() {
        assetStatuses.size();
        entities.size();
    }

    public void addAssetStatuses(Collection<AssetStatusDTO> assetStatusDTOs) {
        for (AssetStatusDTO status : assetStatusDTOs) {
            addAssetStatus(status);
        }
    }

    public void addAssetStatus(AssetStatusDTO statusDTO) {
        statusDTO.setItemType(this);
        assetStatuses.add(statusDTO);
    }

    public void removeAssetStatus(AssetStatusDTO statusDTO) {
        assetStatuses.remove(statusDTO);
    }

    public AssetStatusDTO findDefaultAssetStatus() {
        for (AssetStatusDTO assetStatusDTO : assetStatuses) {
            if (assetStatusDTO.getDeleted() == 0 && assetStatusDTO.getIsDefault() == 1) return assetStatusDTO;
        }
        return null;
    }

    public AssetStatusDTO findOrderSavedStatus() {
        for (AssetStatusDTO assetStatusDTO : assetStatuses) {
            if (assetStatusDTO.getDeleted() == 0 && assetStatusDTO.getIsOrderSaved() == 1) return assetStatusDTO;
        }
        return null;
    }


    @Transient
    public Integer getEntityId() {
        return getEntity() != null ? getEntity().getId() : null;
    }


}


