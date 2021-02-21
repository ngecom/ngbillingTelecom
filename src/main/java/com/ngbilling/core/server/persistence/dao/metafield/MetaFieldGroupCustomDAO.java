package com.ngbilling.core.server.persistence.dao.metafield;

import java.util.List;

import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldGroup;
import com.ngbilling.core.server.persistence.dto.util.EntityType;

public interface MetaFieldGroupCustomDAO {

	public List<MetaFieldGroup> getAvailableFieldGroups(Integer entityId, EntityType entityType);
	public MetaFieldGroup getGroupByName(Integer entityId, EntityType entityType, String name);
}
