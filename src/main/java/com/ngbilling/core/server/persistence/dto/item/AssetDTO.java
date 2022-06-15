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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ngbilling.core.server.persistence.dto.order.OrderLineDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.util.ServerConstants;


/**
 * Assets are linked to a product (ItemDTO). Each asset has an identifier which must be unique per the
 * category (ItemTypeDTO) the product belongs to.
 * An asset always has a status. The statuses are also defined per category.
 * An asset may be linked to an OrderLineDTO.
 *
 * @author Gerhard
 * @since 15/04/13
 */
@Entity
@Table(name = "asset")
@NamedQueries({
        @NamedQuery(name = "AssetDTO.countForItem",
                query = "select count(a.id) from AssetDTO a where a.item.id = :item_id " +
                        "and deleted=0"),
        @NamedQuery(name = "AssetDTO.identifierForIdentifierAndCategory",
                query = "select a from AssetDTO a " +
                        "join a.item.itemTypes types " +
                        "where types.id = :item_type_id " +
                        "and a.identifier = :identifier " +
                        "and a.deleted=0"),
        @NamedQuery(name = "AssetDTO.idsForItemType",
                query = "select a.id from AssetDTO a " +
                        "join a.item.itemTypes types " +
                        "where types.id = :item_type_id " +
                        "and a.deleted=0"),
        @NamedQuery(name = "AssetDTO.idsForItem",
                query = "select a.id from AssetDTO a " +
                        "where a.item.id = :item_id " +
                        "and a.deleted=0"),
        @NamedQuery(name = "AssetDTO.getForItemAndIdentifier",
                query = "select a from AssetDTO a " +
                        "where a.item.id = :itemId " +
                        "and a.identifier = :identifier " +
                        "and a.deleted=0")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AssetDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    //unique per ItemTypeDTO
    private String identifier;
    private AssetStatusDTO assetStatus;
    private CompanyDTO entity;
    private int deleted;
    private ItemDTO item;
    private Set<AssetTransitionDTO> transitions = new HashSet<AssetTransitionDTO>(0);
    private Set<AssetAssignmentDTO> assignments = new HashSet<AssetAssignmentDTO>(0);
    private int versionNum;
    private Date createDatetime;
    private OrderLineDTO orderLine;
    private String notes;
    /**
     * Can only contain assets is a group
     */
    private Set<AssetDTO> containedAssets = new HashSet<AssetDTO>(0);
    /**
     * Parent group
     */
    private AssetDTO group;

    //transient properties
    private OrderLineDTO prevOrderLine;
    private boolean unlinkedFromLine = false;
    private boolean isTouched = false;
    private boolean global = false;
    private Set<CompanyDTO> entities = new HashSet<CompanyDTO>(0);
    private List<Integer> childEntityIds = null;

    private boolean isReserved = false;

    public AssetDTO() {
    }

    public AssetDTO(AssetDTO dto) {
        this.id = dto.getId();
        this.identifier = dto.getIdentifier();
        this.assetStatus = dto.getAssetStatus();
        this.entity = dto.getEntity();
        this.deleted = dto.getDeleted();
        this.item = dto.getItem();
        this.versionNum = dto.getVersionNum();
        this.createDatetime = dto.getCreateDatetime();
        this.orderLine = dto.getOrderLine();
        this.notes = dto.getNotes();
        this.group = dto.getGroup();
        this.global = dto.isGlobal();
        setEntities(dto.getEntities());
        setContainedAssets(dto.getContainedAssets());
        setTransitions(dto.getTransitions());
        setAssignments(dto.getAssignments());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_GEN")
    @SequenceGenerator(
            name = "asset_GEN",
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
    @JoinColumn(name = "group_id")
    public AssetDTO getGroup() {
        return group;
    }

    public void setGroup(AssetDTO group) {
        this.group = group;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
    public Set<AssetDTO> getContainedAssets() {
        return containedAssets;
    }

    public void setContainedAssets(Set<AssetDTO> containedAssets) {
        this.containedAssets = containedAssets;
    }

    @Column(name = "notes", length = 1000)
    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Column(name = "create_datetime", nullable = false, length = 29)
    public Date getCreateDatetime() {
        return this.createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_line_id")
    public OrderLineDTO getOrderLine() {
        return this.orderLine;
    }

    public void setOrderLine(OrderLineDTO orderLine) {
        this.orderLine = orderLine;
    }

    @Transient
    public OrderLineDTO getPrevOrderLine() {
        return prevOrderLine;
    }

    public void setPrevOrderLine(OrderLineDTO dto) {
        this.prevOrderLine = dto;
    }

    @Column(name = "identifier", nullable = false, length = 200)
    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    public AssetStatusDTO getAssetStatus() {
        return this.assetStatus;
    }

    public void setAssetStatus(AssetStatusDTO assetStatus) {
        this.assetStatus = assetStatus;
    }

    @Transient
    public boolean isUnlinkedFromLine() {
        return unlinkedFromLine;
    }

    public void setUnlinkedFromLine(boolean unlinkedFromLine) {
        this.unlinkedFromLine = unlinkedFromLine;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id")
    public CompanyDTO getEntity() {
        return this.entity;
    }

    public void setEntity(CompanyDTO entity) {
        this.entity = entity;
    }

    @Column(name = "deleted", nullable = false)
    public int getDeleted() {
        return this.deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    public ItemDTO getItem() {
        return this.item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "asset")
    public Set<AssetTransitionDTO> getTransitions() {
        return this.transitions;
    }

    public void setTransitions(Set<AssetTransitionDTO> transitions) {
        this.transitions = transitions;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "asset")
    public Set<AssetAssignmentDTO> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<AssetAssignmentDTO> assignments) {
        this.assignments = assignments;
    }

    @Version
    @Column(name = "optlock")
    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    /**
     * Add an asset to the set of contained assets
     *
     * @param assetDTO
     */
    public void addContainedAsset(AssetDTO assetDTO) {
        assetDTO.setGroup(this);
        containedAssets.add(assetDTO);
    }

    /**
     * Remove the asset from the set of contained assets
     *
     * @param assetDTO
     */
    public void removeContainedAsset(AssetDTO assetDTO) {
        assetDTO.setGroup(null);
        containedAssets.remove(assetDTO);
    }

    /**
     * Add a collection of assets to the set of contained assets.
     *
     * @param assetDTOs
     */
    public void addContainedAssets(Collection<AssetDTO> assetDTOs) {
        for (AssetDTO dto : assetDTOs) {
            addContainedAsset(dto);
        }
    }


    @Transient
    protected String getTable() {
        return ServerConstants.TABLE_ASSET;
    }

    @Column(name = "global", nullable = false, updatable = true)
    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    /**
     * Load all lazy dependencies of entity if needed
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "asset_entity_map", joinColumns = {
            @JoinColumn(name = "asset_id", updatable = true)}, inverseJoinColumns = {
            @JoinColumn(name = "entity_id", updatable = true)})
    public Set<CompanyDTO> getEntities() {
        return entities;
    }

    public void setEntities(Set<CompanyDTO> entities) {
        this.entities = entities;
    }

    @Transient
    public Integer getEntityId() {
        return getEntity() != null ? getEntity().getId() : null;
    }

    public void touch() {
        // touch entity only once
        if (isTouched) return;
        isTouched = true;

        getCreateDatetime();
        if (getItem() != null) {
            getItem().getInternalNumber();
        }

        if (getOrderLine() != null) {
            getOrderLine().touch();
        }
    }

    @Override
    public String toString() {
        return "AssetDTO{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", assetStatus=" + (assetStatus != null ? assetStatus.getId() : null) +
                ", deleted=" + deleted +
                ", versionNum=" + versionNum +
                ", createDatetime=" + createDatetime +
                ", notes='" + notes + '\'' +
                ", orderLine=" + (orderLine != null ? orderLine.getId() : null) +
                ", item=" + (item != null ? item.getId() : null) +
                ", isReserved='" + isReserved + '\'' +
                '}';
    }


    @Transient
    public List<Integer> getChildEntitiesIds() {
        if (this.childEntityIds == null) {
            this.childEntityIds = new ArrayList<Integer>();
            for (CompanyDTO dto : this.entities) {
                this.childEntityIds.add(dto.getId());
            }

        }
        return this.childEntityIds;
    }

    @Transient
    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }
}
