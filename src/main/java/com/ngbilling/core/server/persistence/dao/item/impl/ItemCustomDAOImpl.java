package com.ngbilling.core.server.persistence.dao.item.impl;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.item.ItemCustomDAO;
import com.ngbilling.core.server.persistence.dao.item.ItemDAO;
import com.ngbilling.core.server.persistence.dto.item.ItemDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ItemCustomDAOImpl extends AbstractJpaDAO<ItemDTO> implements ItemCustomDAO {


    private static final String CURRENCY_USAGE_FOR_ENTITY_SQL =
            "select count(*) from " +
                    " item i, " +
                    " item_price ipt " +
                    " where " +
                    "     ipt.item_id = i.id " +
                    " and ipt.currency_id = :currencyId " +
                    " and i.entity_id = :entityId " +
                    " and i.deleted = 0 ";
    private static final String IS_SUBSCRIBED_BY_ITEM = "SELECT * FROM order_line ol, plan p, base_user u, purchase_order po, order_status os "
            + "WHERE p.item_id = ol.item_id and po.id = ol.order_id and u.id = po.user_id and po.status_id = os.id "
            + "and p.id = (SELECT id FROM plan WHERE item_id = :item_id) "
            + "and u.id = :user_id " + "and ol.deleted = 0 "
            + "and po.deleted = 0 " + "and po.period_id <> 1 "
            + "and os.order_status_flag <> 1";
    private static final String FIND_ITEMS_BY_INTERNAL_NUMBER = "select u from ItemDTO u where u.internalNumber = ?1";
    private static final String FIND_ALL_BY_ITEM_TYPE = "select u from ItemDTO u where u.itemTypes.id = ?1 "
            + "and u.deleted = 0 order by u.id desc";
    private static final String FIND_ITEMS_BY_CATEGORY_PREFIX = "select u from ItemDTO u where "
            + "u.itemTypes.description like CONCAT(?1, '%')";
    private static final String FIND_PRODUCT_COUNT_BY_INTERNAL_NUMBER = "select count(*) from ItemDTO u left join u.entities it where internalNumber = ?1 and deleted = 0 and it.id = ?2  ";
    private static final String IS_NEW = FIND_PRODUCT_COUNT_BY_INTERNAL_NUMBER + " and u.id != ?3";
    private static final String FIND_BY_ENTITY_ID = "SELECT t FROM ItemDTO t "
            + "left join t.entities it " +

            "WHERE it.id = ?1)";
    private static final String FIND_ITEMS = "SELECT  t FROM ItemDTO t "
            + "left join t.entities it " +

            "WHERE t.global = true or it.entities.id in (:entities)";
    private static final String PRODUCT_VISIBLE_TO_PARENT_COMPANY_SQL =
            "select count(*) from item i " +
                    " left join item_entity_map ie on ie.item_id = i.id where " +
                    " i.id = :itemId and " +
                    " (ie.entity_id = :entityId or i.entity_id = :entityId) and" +
                    " i.deleted = 0";
    private static final String PRODUCT_AVAILABLE_TO_PARENT_COMPANY_SQL =
            "select count(*) from item i " +
                    " left join item_entity_map ie on ie.item_id = i.id where " +
                    " i.id = :itemId and " +
                    " (ie.entity_id = :entityId) and" +
                    " i.deleted = 0";
    private static final String PRODUCT_VISIBLE_TO_CHILD_HIERARCHY_SQL =
            "select count(*) from item i " +
                    "left outer join item_entity_map icem " +
                    "on i.id = icem.item_id " +
                    "where i.id = :itemId " +
                    "and  i.deleted = 0 " +
                    "and  (i.entity_id = :childCompanyId or " +
                    " icem.entity_id = :childCompanyId or " +
                    "((icem.entity_id = :parentCompanyId or icem.entity_id is null) and " +
                    "i.global = true));";

    @Override
    public Long findProductCountByCurrencyAndEntity(Integer currencyId, Integer entityId) {
        // TODO Auto-generated method stub
        Query query = getEntityManager().createNamedQuery(CURRENCY_USAGE_FOR_ENTITY_SQL
        );

        query.setParameter("currencyId", currencyId);
        query.setParameter("entityId", entityId);
        return (Long) query.getSingleResult();
    }

    @Override
    public boolean isSubscribedByItem(Integer userId, Integer itemId) {
        // TODO Auto-generated method stub
        Query query = getEntityManager().createNamedQuery(IS_SUBSCRIBED_BY_ITEM);

        query.setParameter("user_id", userId);
        query.setParameter("item_id", itemId);

        return query.getResultList() != null && query.getResultList().size() > 0;
    }

    @Override
    public List<ItemDTO> findItemsByInternalNumber(String internalNumber) {
        // TODO Auto-generated method stub
        TypedQuery<ItemDTO> query = getEntityManager().createQuery(FIND_ITEMS_BY_INTERNAL_NUMBER,
                ItemDTO.class);

        query.setParameter(1, internalNumber);
        return query.getResultList();
    }

    @Override
    public List<ItemDTO> findAllByItemType(Integer itemTypeId) {
        // TODO Auto-generated method stub
        TypedQuery<ItemDTO> query = getEntityManager().createQuery(FIND_ALL_BY_ITEM_TYPE,
                ItemDTO.class);

        query.setParameter(1, itemTypeId);
        return query.getResultList();
    }

    @Override
    public List<ItemDTO> findItemsByCategoryPrefix(String prefix) {
        // TODO Auto-generated method stub
        TypedQuery<ItemDTO> query = getEntityManager().createQuery(FIND_ITEMS_BY_CATEGORY_PREFIX,
                ItemDTO.class);

        query.setParameter(1, prefix);
        return query.getResultList();
    }

    @Override
    public Long findProductCountByInternalNumber(String internalNumber, Integer entityId, boolean isNew, Integer id) {
        // TODO Auto-generated method stub
        String query = isNew ? IS_NEW : FIND_PRODUCT_COUNT_BY_INTERNAL_NUMBER;
        TypedQuery<Long> q = getEntityManager().createQuery(query,
                Long.class);

        q.setParameter(1, internalNumber);
        q.setParameter(2, entityId);
        if (isNew)
            q.setParameter(3, id);


        return q.getSingleResult();
    }

    @Override
    public List<ItemDTO> findByEntityId(Integer entityId) {
        // TODO Auto-generated method stub
        TypedQuery<ItemDTO> query = getEntityManager().createQuery(FIND_BY_ENTITY_ID, ItemDTO.class
        );

        query.setParameter(1, entityId);
        return query.getResultList();
    }

    @Override
    public List<ItemDTO> findItems(Integer entity, List<Integer> entities, boolean isRoot) {
        // TODO Auto-generated method stub
        //String entityString = entities.stream().toString().collect(Collectors.joining(",");

        String sql = FIND_ITEMS;

        String sqlFinal = ((isRoot) ? (sql + (" or it.entities.parent.id = :id")) : sql) + " order by t.id asc";
        Query query = getEntityManager().createNamedQuery(sqlFinal);

        if (isRoot)
            query.setParameter("id", entity);
        query.setParameter("entities", entities);

        return query.getResultList();
    }

    @Override
    public boolean isProductVisibleToCompany(Integer itemId, Integer entityId, Integer parentId) {
        // TODO Auto-generated method stub
        Query query;

        if (null == parentId) {
            query = getEntityManager().createNamedQuery(PRODUCT_VISIBLE_TO_PARENT_COMPANY_SQL);
            query.setParameter("itemId", itemId);
            query.setParameter("entityId", entityId);
        } else {
            query = getEntityManager().createNamedQuery(PRODUCT_VISIBLE_TO_CHILD_HIERARCHY_SQL);

            query.setParameter("itemId", itemId);
            query.setParameter("findAllByItemType", entityId);
            query.setParameter("parentCompanyId", parentId);
        }

        return (Long) query.getSingleResult() != null && (Long) query.getSingleResult() > 0;
    }

}
