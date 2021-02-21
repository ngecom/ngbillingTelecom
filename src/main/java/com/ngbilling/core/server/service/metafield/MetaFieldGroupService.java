package com.ngbilling.core.server.service.metafield;

import java.util.Collection;
import java.util.List;

import com.ngbilling.core.payload.request.metafield.MetaFieldGroupWS;
import com.ngbilling.core.payload.request.user.AccountInformationTypeWS;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldGroup;
import com.ngbilling.core.server.persistence.dto.util.EntityType;

public interface MetaFieldGroupService {

	public  MetaFieldGroup getEntity();
	public Integer save() throws Exception;
	public void update(MetaFieldGroup metaFieldGroup) throws Exception;
	public void delete() throws Exception ;
	public List<MetaFieldGroup> getAvailableFieldGroups(Integer entityId, EntityType entityType);
	
	public  MetaFieldGroupWS[] convertMetaFieldGroupsToWS(Collection<MetaFieldGroup> metaFieldGroups) throws Exception;
	
	public  AccountInformationTypeWS getAccountInformationTypeWS(MetaFieldGroup dto) throws Exception;
	public MetaFieldGroupWS getWS(MetaFieldGroup groupDTO) throws Exception;
	public  MetaFieldGroup getDTO(MetaFieldGroupWS ws) throws Exception;
	
}
