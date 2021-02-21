package com.ngbilling.core.server.persistence.dao.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.item.ItemPriceDTO;

@Repository
public interface ItemPriceDAO extends JpaRepository<ItemPriceDTO, Integer>{

	@Query("SELECT u FROM ItemPriceDTO u WHERE u.item.id = ?1 and u.currency.id = ?2")
	public ItemPriceDTO findPrice(Integer itemId, Integer currencyId);
}
