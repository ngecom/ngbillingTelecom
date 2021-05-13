package com.ngbilling.core.server.persistence.dao.metafield.impl;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.metafield.MetaFieldGroupCustomDAO;
import com.ngbilling.core.server.persistence.dao.metafield.MetaFieldGroupDAO;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldGroup;
import com.ngbilling.core.server.persistence.dto.util.EntityType;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class MetaFieldGroupCustomDAOImpl extends AbstractJpaDAO<MetaFieldGroup> implements MetaFieldGroupCustomDAO {


    @Override
    public List<MetaFieldGroup> getAvailableFieldGroups(Integer entityId, EntityType entityType) {
        return null;
    }

    @Override
    public MetaFieldGroup getGroupByName(Integer entityId, EntityType entityType, String name) {
        return null;
    }
}
