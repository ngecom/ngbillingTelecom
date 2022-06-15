package com.ngbilling.core.server.persistence.dao.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.ngbilling.core.server.persistence.dto.item.ItemDependencyDTO;

@NoRepositoryBean
public interface ItemDependencyDAO extends JpaRepository<ItemDependencyDTO, Integer> {

    @Query("select count(a.id) from ItemDependencyOnItemDTO a where a.dependent.id = :item_id ")
    public int countByDependentItem(Integer itemId);

}
