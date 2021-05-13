package com.ngbilling.core.server.persistence.dao.item;

import com.ngbilling.core.server.persistence.dto.item.ItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDAO extends JpaRepository<ItemDTO, Integer>, ItemCustomDAO {


}
