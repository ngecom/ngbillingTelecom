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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ngbilling.core.server.persistence.dto.invoice.InvoiceLineDTO;
import com.ngbilling.core.server.persistence.dto.order.OrderLineDTO;
import com.ngbilling.core.server.persistence.dto.user.AccountTypeDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.util.AbstractDescription;
import com.ngbilling.core.server.persistence.dto.util.ItemDependencyType;
import com.ngbilling.core.server.util.ServerConstants;

@Entity
@Table(name = "item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemDTO extends AbstractDescription {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private CompanyDTO entity;
    private Set<CompanyDTO> entities = new HashSet<CompanyDTO>(0);
    private String internalNumber;
    private String glCode;
    private BigDecimal percentage;
    private Set<ItemTypeDTO> excludedTypes = new HashSet<ItemTypeDTO>();
    private Integer priceManual;
    private Integer deleted;
    private Integer hasDecimals;
    private Set<ItemTypeDTO> itemTypes = new HashSet<ItemTypeDTO>(0);
    private Set<InvoiceLineDTO> invoiceLines = new HashSet<InvoiceLineDTO>(0);
    private Set<ItemPriceDTO> itemPrices = new HashSet<ItemPriceDTO>(0);
    private Set<OrderLineDTO> orderLineDTOs = new HashSet<OrderLineDTO>(0);
    private boolean standardAvailability = true;
    private boolean global = false;
    private List<AccountTypeDTO> accountTypeAvailability = new ArrayList<AccountTypeDTO>();


    /**
     * If the item will do asset management. Only possible if one linked ItemTypeDTO allows asset management
     */
    private Integer assetManagementEnabled;
    private int versionNum;

    private Set<ItemDependencyDTO> dependencies = new HashSet<ItemDependencyDTO>();

    // transient
    private Integer[] types = null;
    private Integer[] excludedTypeIds = null;
    private Integer[] accountTypeIds = null;
    private Integer[] dependencyIds = null;


    private Set<Integer> childEntityIds = null;
    private List<Integer> parentAndChildIds = null;

    private Collection<String> strTypes = null; // for rules 'contains' operator
    private String promoCode = null;
    private Integer currencyId = null;
    private BigDecimal price = null;
    private Integer orderLineTypeId = null;

    private Integer priceModelCompanyId = null;

    private Date activeSince;
    private Date activeUntil;

    private BigDecimal standardPartnerPercentage;
    private BigDecimal masterPartnerPercentage;
    private boolean isPercentage;
    private Integer reservationDuration;
    // all the prices.ItemPriceDTOEx  
    private List<?> prices = null;

    public ItemDTO() {
    }

    public ItemDTO(int id) {
        this.id = id;
    }

    public ItemDTO(int id, String internalNumber, String glCode, BigDecimal percentage, Integer priceManual,
                   Integer hasDecimals, Integer deleted, CompanyDTO entity, Integer assetManagementEnabled) {
        this.id = id;
        this.internalNumber = internalNumber;
        this.glCode = glCode;
        this.percentage = percentage;
        this.priceManual = priceManual;
        this.hasDecimals = hasDecimals;
        this.deleted = deleted;
        this.entity = entity;
        this.assetManagementEnabled = assetManagementEnabled;
    }

    public ItemDTO(int id, Integer priceManual, Integer deleted, Integer hasDecimals, Integer assetManagementEnabled) {
        this.id = id;
        this.deleted = deleted;
        this.priceManual = priceManual;
        this.hasDecimals = hasDecimals;
        this.assetManagementEnabled = assetManagementEnabled;
    }

    public ItemDTO(int id, CompanyDTO entity, String internalNumber, String glCode, BigDecimal percentage, Integer priceManual,
                   Integer deleted, Integer hasDecimals, Set<OrderLineDTO> orderLineDTOs, Set<ItemTypeDTO> itemTypes,
                   Set<InvoiceLineDTO> invoiceLines, Set<ItemPriceDTO> itemPrices) {
        this.id = id;
        this.entity = entity;
        this.internalNumber = internalNumber;
        this.glCode = glCode;
        this.percentage = percentage;
        this.priceManual = priceManual;
        this.deleted = deleted;
        this.hasDecimals = hasDecimals;
        this.orderLineDTOs = orderLineDTOs;
        this.itemTypes = itemTypes;
        this.invoiceLines = invoiceLines;
        this.itemPrices = itemPrices;
    }

    // ItemDTOEx
    public ItemDTO(int id, String number, String glCode, CompanyDTO entity, String description, Integer deleted, Integer priceManual,
                   Integer currencyId, BigDecimal price, BigDecimal percentage, Integer orderLineTypeId,
                   Integer hasDecimals, Integer assetManagementEnabled, boolean isPercentage) {

        this(id, number, glCode, percentage, priceManual, hasDecimals, deleted, entity, assetManagementEnabled);
        setDescription(description);
        setCurrencyId(currencyId);
        setOrderLineTypeId(orderLineTypeId);
        setIsPercentage(isPercentage);
        setPrice(price);
    }


    @Transient
    protected String getTable() {
        return ServerConstants.TABLE_ITEM;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_GEN")
    @SequenceGenerator(
            name = "item_GEN",
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
    @JoinColumn(name = "entity_id")
    public CompanyDTO getEntity() {
        return this.entity;
    }

    public void setEntity(CompanyDTO entity) {
        this.entity = entity;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "item_entity_map", joinColumns = {
            @JoinColumn(name = "item_id", updatable = true)}, inverseJoinColumns = {
            @JoinColumn(name = "entity_id", updatable = true)})
    public Set<CompanyDTO> getEntities() {
        return this.entities;
    }

    public void setEntities(Set<CompanyDTO> entities) {
        this.entities = entities;
    }

    @Transient
    public Set<Integer> getChildEntitiesIds() {
        if (this.childEntityIds == null) {
            this.childEntityIds = new HashSet<Integer>();
            for (CompanyDTO dto : this.entities) {
                this.childEntityIds.add(dto.getId());
            }

        }
        return this.childEntityIds;
    }

    @Column(name = "asset_management_enabled")
    public Integer getAssetManagementEnabled() {
        return assetManagementEnabled;
    }

    public void setAssetManagementEnabled(Integer assetManagementEnabled) {
        this.assetManagementEnabled = assetManagementEnabled;
    }

    @Column(name = "internal_number", length = 50)
    public String getInternalNumber() {
        return this.internalNumber;
    }

    public void setInternalNumber(String internalNumber) {
        this.internalNumber = internalNumber;
    }

    @Column(name = "gl_code", length = 50)
    public String getGlCode() {
        return glCode;
    }

    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    @Transient
    public BigDecimal getPercentage() {
        return this.percentage;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "item_type_exclude_map",
            joinColumns = {@JoinColumn(name = "item_id", updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "type_id", updatable = false)}
    )
    public Set<ItemTypeDTO> getExcludedTypes() {
        return excludedTypes;
    }

    public void setExcludedTypes(Set<ItemTypeDTO> excludedTypes) {
        this.excludedTypes = excludedTypes;
    }

    @Column(name = "price_manual", nullable = false)
    public Integer getPriceManual() {
        return this.priceManual;
    }

    public void setPriceManual(Integer priceManual) {
        this.priceManual = priceManual;
    }

    @Column(name = "deleted", nullable = false)
    public Integer getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Column(name = "has_decimals", nullable = false)
    public Integer getHasDecimals() {
        return this.hasDecimals;
    }

    public void setHasDecimals(Integer hasDecimals) {
        this.hasDecimals = hasDecimals;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "item_type_map",
            joinColumns = {@JoinColumn(name = "item_id", updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "type_id", updatable = false)}
    )
    public Set<ItemTypeDTO> getItemTypes() {
        return this.itemTypes;
    }

    public void setItemTypes(Set<ItemTypeDTO> itemTypes) {
        this.itemTypes = itemTypes;
    }

    /**
     * Strips the given prefix off of item categories and returns the resulting code. This method allows categories to
     * be used to hold identifiers and other meta-data.
     * <p/>
     * Example: item = ItemDTO{ type : ["JB_123"] } item.getCategoryCode("JB") -> "123"
     *
     * @param prefix prefix of the category code to retrieve
     * @return code minus the given prefix
     */
    public String getCategoryCode(String prefix) {
        for (ItemTypeDTO type : getItemTypes())
            if (type.getDescription().startsWith(prefix))
                return type.getDescription().replaceAll(prefix, "");
        return null;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
    public Set<ItemPriceDTO> getItemPrices() {
        return this.itemPrices;
    }

    public void setItemPrices(Set<ItemPriceDTO> itemPrices) {
        this.itemPrices = itemPrices;
    }

    @Transient
    public List<?> getPrices() {
        return prices;
    }

    @Transient
    public void setPrices(List<?> prices) {
        this.prices = prices;
    }

    @Version
    @Column(name = "OPTLOCK")
    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

    @Column(name = "standard_availability", nullable = false)
    public boolean isStandardAvailability() {
        return standardAvailability;
    }

    public void setStandardAvailability(boolean standardAvailability) {
        this.standardAvailability = standardAvailability;
    }

    @Column(name = "global", nullable = false, updatable = true)
    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public void setAccountTypeAvailability(
            List<AccountTypeDTO> accountTypeAvailability) {
        this.accountTypeAvailability = accountTypeAvailability;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item", orphanRemoval = true)
    public Set<ItemDependencyDTO> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Set<ItemDependencyDTO> dependencies) {
        this.dependencies = dependencies;
        dependencyIds = null;
    }

    @Transient
    public List<ItemDTO> getDependItems() {
        List<ItemDTO> dependItems = new ArrayList<ItemDTO>();
        for (ItemDependencyDTO itemDependencyDTO : dependencies) {
            dependItems.add((ItemDTO) itemDependencyDTO.getDependent());
        }
        return dependItems;
    }

    @Column(name = "standard_partner_percentage")
    public BigDecimal getStandardPartnerPercentage() {
        return standardPartnerPercentage;
    }

    public void setStandardPartnerPercentage(BigDecimal standardPartnerPercentage) {
        this.standardPartnerPercentage = standardPartnerPercentage;
    }

    @Column(name = "master_partner_percentage")
    public BigDecimal getMasterPartnerPercentage() {
        return masterPartnerPercentage;
    }

    public void setMasterPartnerPercentage(BigDecimal masterPartnerPercentage) {
        this.masterPartnerPercentage = masterPartnerPercentage;
    }

    @Column(name = "reservation_duration")
    public Integer getReservationDuration() {
        return reservationDuration;
    }

    public void setReservationDuration(Integer reservationDuration) {
        this.reservationDuration = reservationDuration;
    }

    public void addDependency(ItemDependencyDTO dependencyDTO) {
        dependencies.add(dependencyDTO);
        dependencyDTO.setItem(this);
    }

    @Transient
    public String getNumber() {
        return getInternalNumber();
    }

    @Transient
    public void setNumber(String number) {
        setInternalNumber(number);
    }

    @Transient
    public Integer[] getTypes() {
        if (this.types == null && itemTypes != null) {
            Integer[] types = new Integer[itemTypes.size()];
            int i = 0;
            for (ItemTypeDTO type : itemTypes) {
                types[i++] = type.getId();
            }
            setTypes(types);
        }
        return types;
    }

    /*
        Transient fields
     */

    @Transient
    public void setTypes(Integer[] types) {
        this.types = types;

        strTypes = new ArrayList<String>(types.length);
        for (Integer i : types) {
            strTypes.add(i.toString());
        }
    }

    public boolean hasType(Integer typeId) {
        return Arrays.asList(getTypes()).contains(typeId);
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "active_since", length = 13)
    public Date getActiveSince() {
        return this.activeSince;
    }

    public void setActiveSince(Date activeSince) {
        this.activeSince = activeSince;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "active_until", length = 13)
    public Date getActiveUntil() {
        return this.activeUntil;
    }

    public void setActiveUntil(Date activeUntil) {
        this.activeUntil = activeUntil;
    }

    @Transient
    public Integer[] getExcludedTypeIds() {
        if (this.excludedTypeIds == null && excludedTypes != null) {
            Integer[] types = new Integer[excludedTypes.size()];
            int i = 0;
            for (ItemTypeDTO type : excludedTypes) {
                types[i++] = type.getId();
            }
            setExcludedTypeIds(types);
        }
        return excludedTypeIds;
    }

    @Transient
    public void setExcludedTypeIds(Integer[] types) {
        this.excludedTypeIds = types;
    }

    public boolean hasExcludedType(Integer typeId) {
        return Arrays.asList(getExcludedTypeIds()).contains(typeId);
    }

    @Transient
    public Integer[] getAccountTypeIds() {
        if (this.accountTypeIds == null && accountTypeAvailability != null) {
            Integer[] types = new Integer[accountTypeAvailability.size()];
            int i = 0;
            for (AccountTypeDTO type : accountTypeAvailability) {
                types[i++] = type.getId();
            }
            setAccountTypeIds(types);
        }
        return accountTypeIds;
    }

    public void setAccountTypeIds(Integer[] accountTypeIds) {
        this.accountTypeIds = accountTypeIds;
    }

    /**
     * Return all ItemDependencyDTO objects contained in dependencies with the
     * given type.
     *
     * @param type
     * @return
     */
    public Collection<ItemDependencyDTO> getDependenciesOfType(ItemDependencyType type) {
        ArrayList<ItemDependencyDTO> result = new ArrayList<ItemDependencyDTO>();
        if (dependencies != null) {
            for (ItemDependencyDTO dependency : dependencies) {
                if (dependency.getType().equals(type)) {
                    result.add(dependency);
                }
            }
        }
        return result;
    }

    @Transient
    public Integer[] getDependencyIds() {
        if (this.dependencyIds == null && dependencies != null) {
            Integer[] dependencyIds = new Integer[dependencies.size()];
            int i = 0;
            for (ItemDependencyDTO dependency : dependencies) {
                dependencyIds[i++] = dependency.getId();
            }
            setDependencyIds(types);
        }
        return dependencyIds;
    }

    @Transient
    public void setDependencyIds(Integer[] ids) {
        this.dependencyIds = types;
    }

    /**
     * From the dependencies extract the ids of those of type {@code type }
     * which has a minimum required qty of 1
     *
     * @param type Type of dependecies to extract
     * @return
     */
    public Integer[] getMandatoryDependencyIdsOfType(ItemDependencyType type) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (dependencies != null) {
            for (ItemDependencyDTO dependency : dependencies) {
                if (dependency.getType().equals(type) && dependency.getMinimum() > 0) {
                    result.add(dependency.getDependentObjectId());
                }
            }
        }
        return result.toArray(new Integer[result.size()]);
    }

    @Transient
    public List<Integer> getParentAndChildIds() {
        return parentAndChildIds;
    }

    public void setParentAndChildIds(List<Integer> parentAndChildIds) {
        this.parentAndChildIds = parentAndChildIds;
    }

    /**
     * Rules 'contains' operator only works on a collections of strings
     *
     * @return collection of ItemTypeDTO ID's as strings.
     */
    @Transient
    public Collection<String> getStrTypes() {
        if (strTypes == null && itemTypes != null) {
            strTypes = new ArrayList<String>(itemTypes.size());
            for (ItemTypeDTO type : itemTypes)
                strTypes.add(String.valueOf(type.getId()));
        }

        return strTypes;
    }

    @Transient
    public String getPromoCode() {
        return promoCode;
    }

    @Transient
    public void setPromoCode(String string) {
        promoCode = string;
    }

    @Transient
    public Integer getEntityId() {
        return getEntity() != null ? getEntity().getId() : null;
    }

    @Transient
    public Integer getOrderLineTypeId() {
        return orderLineTypeId;
    }

    @Transient
    public void setOrderLineTypeId(Integer typeId) {
        orderLineTypeId = typeId;
    }

    @Transient
    public Integer getCurrencyId() {
        return currencyId;
    }

    @Transient
    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    @Transient
    public BigDecimal getPrice() {
        return price;
    }

    @Transient
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Transient
    public Integer getPriceModelCompanyId() {
        return priceModelCompanyId;
    }

    @Transient
    public void setPriceModelCompanyId(Integer priceModelCompanyId) {
        this.priceModelCompanyId = priceModelCompanyId;
    }

    @Override
    public String toString() {
        return "ItemDTO: id=" + getId();
    }

    public ItemTypeDTO findItemTypeWithAssetManagement() {
        for (ItemTypeDTO type : itemTypes) {
            if (type.getAllowAssetManagement().intValue() == 1) return type;
        }
        return null;
    }

    @Transient
    public boolean isPercentage() {
        return isPercentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public void setIsPercentage(boolean isPercentage) {
        this.isPercentage = isPercentage;
    }

    /*public ItemTypeDTO findItemWithAssetManagement(ItemDTO item) {
        for(ItemDTO type : item) {
            if(type.getAssetManagementEnabled().intValue() == 1) return type;
        }
        return null;
    }*/
    @Transient
    public String[] getFieldNames() {
        String headers[] = new String[]{
                "id",
                "productCode",
                "itemTypes",
                "hasDecimals",
                "priceManual",
                "percentage",
                "prices",
        };

        List<String> list = new ArrayList<String>(Arrays.asList(headers));

        return list.toArray(new String[list.size()]);
    }

    @Transient
    public Object[][] getFieldValues() {
        StringBuilder itemTypes = new StringBuilder();
        for (ItemTypeDTO type : this.itemTypes) {
            itemTypes.append(type.getDescription()).append(' ');
        }
        StringBuilder prices = new StringBuilder();
        for (Iterator<ItemPriceDTO> it = this.itemPrices.iterator(); it.hasNext(); ) {
            ItemPriceDTO price = it.next();
            prices.append(price.getPrice()).append(" ").append(price.getCurrency().getCode());
            if (it.hasNext()) prices.append(",");
        }

        Object values[][] = new Object[][]{
                {
                        id,
                        internalNumber,
                        itemTypes.toString(),
                        hasDecimals,
                        priceManual,
                        percentage,
                        prices.toString(),
                }
        };

        List<Object> aitList = new ArrayList<>(Arrays.asList(values[0]));

        Object obj[][] = new Object[][]{aitList.toArray()};
        return obj;
    }


}
