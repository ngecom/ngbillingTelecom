package com.ngbilling.core.server.persistence.dao.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.item.ItemDTO;

@Repository
public interface ItemDAO extends JpaRepository<ItemDTO, Integer>, ItemCustomDAO {


}
