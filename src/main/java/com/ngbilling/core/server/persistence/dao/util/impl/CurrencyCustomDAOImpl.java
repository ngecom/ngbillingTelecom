package com.ngbilling.core.server.persistence.dao.util.impl;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.util.CurrencyCustomDAO;
import com.ngbilling.core.server.persistence.dao.util.CurrencyDAO;
import com.ngbilling.core.server.persistence.dto.order.OrderDTO;
import com.ngbilling.core.server.persistence.dto.util.CurrencyDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class CurrencyCustomDAOImpl extends AbstractJpaDAO<CurrencyDTO> implements CurrencyCustomDAO {

    @Override
    public boolean findAssociationExistsForCurrency(Integer currencyId, String currencyName) {
        // TODO Auto-generated method stub
        TypedQuery<OrderDTO> query = getEntityManager().createQuery("select u from CurrencyDTO u where " + currencyName + ".id = ?1", OrderDTO.class
        );

        query.setParameter(1, currencyId);
        return query.getSingleResult() != null;
    }

}
