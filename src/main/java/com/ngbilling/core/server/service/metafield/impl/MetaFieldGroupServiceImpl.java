package com.ngbilling.core.server.service.metafield.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngbilling.core.payload.request.metafield.MetaFieldGroupWS;
import com.ngbilling.core.payload.request.metafield.MetaFieldWS;
import com.ngbilling.core.payload.request.user.AccountInformationTypeWS;
import com.ngbilling.core.payload.request.util.InternationalDescriptionWS;
import com.ngbilling.core.server.persistence.dao.metafield.MetaFieldDAO;
import com.ngbilling.core.server.persistence.dao.metafield.MetaFieldGroupDAO;
import com.ngbilling.core.server.persistence.dto.metafield.MetaField;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldGroup;
import com.ngbilling.core.server.persistence.dto.util.EntityType;
import com.ngbilling.core.server.service.metafield.MetaFieldGroupService;
import com.ngbilling.core.server.service.metafield.MetaFieldService;

@Service
public class MetaFieldGroupServiceImpl implements MetaFieldGroupService {

    private MetaFieldGroup group = null;

    @Autowired
    private MetaFieldGroupDAO metaFieldGroupDAO;

    @Autowired
    private MetaFieldDAO metaFieldDAO;

    @Autowired
    private MetaFieldService metaFieldService;

    private static String getErrorsAsString(String[] errors) {
        StringBuilder builder = new StringBuilder();
        if (errors != null) {
            builder.append(". Errors: ");
            for (String error : errors) {
                builder.append(error);
                builder.append(System.getProperty(","));
            }
        }
        return builder.toString();
    }

    @Override
    public MetaFieldGroup getEntity() {
        // TODO Auto-generated method stub
        return group;
    }

    @Override
    public Integer save() throws Exception {
        // TODO Auto-generated method stub
        try {
            MetaFieldGroup groupWithSameName = metaFieldGroupDAO.getGroupByName(group.getEntity().getId(), group.getEntityType(), group.getDescription());
            String[] error = new String[]{"MetaFieldGroupWS,mfGroup,cannot.save.metafieldgroup.db.error"};
            if (groupWithSameName != null) {
                throw new Exception("Exception saving metafield group to database.MetaFieldGroup" +
                        " with same name [" + group.getDescription() + "] is existed in db" + getErrorsAsString(error));

            }

            group = metaFieldGroupDAO.save(group);
            //group.setDescription(group.getDescription(), CommonConstants.LANGUAGE_ENGLISH_ID);

            return group.getId();
        } catch (Exception e) {

            String[] error = new String[]{"MetaFieldGroupWS,metafieldgroup,metafieldgroup.db.error.cannot.save"};

            throw new Exception("Exception saving metafield group to database " + getErrorsAsString(error),
                    e);
        }
    }

    @Override
    public void update(MetaFieldGroup metaFieldGroup) throws Exception {
        // TODO Auto-generated method stub
        try {
            MetaFieldGroup group = metaFieldGroupDAO.findById(metaFieldGroup.getId()).get();

            group.setDateUpdated(new Date());
            group.setDisplayOrder(metaFieldGroup.getDisplayOrder());
            group.setDescription(metaFieldGroup.getDescription());

            group.setMetaFields(metaFieldGroup.getMetaFields());
            metaFieldGroupDAO.save(group);
        } catch (Exception e) {
            String[] error = new String[]{"MetaFieldGroupWS,mfGroup,cannot.update.metafieldgroup.db.error"};
            throw new Exception("Exception when updating metafield group to database" + getErrorsAsString(error),
                    e);
        }

    }

    @Override
    public void delete() throws Exception {
        // TODO Auto-generated method stub
        try {
            metaFieldGroupDAO.delete(group);
        } catch (Exception e) {
            String[] error = new String[]{"MetaFieldGroupWS,mfGroup,cannot.delete.metafieldgroup.db.error"};
            throw new Exception("Exception  deleting metafield group" +
                    getErrorsAsString(error), e);
        }

    }

    @Override
    public List<MetaFieldGroup> getAvailableFieldGroups(Integer entityId, EntityType entityType) {
        // TODO Auto-generated method stub
        return metaFieldGroupDAO.getAvailableFieldGroups(entityId, entityType);
    }

    @Override
    public MetaFieldGroupWS[] convertMetaFieldGroupsToWS(Collection<MetaFieldGroup> metaFieldGroups) throws Exception {
        // TODO Auto-generated method stub
        MetaFieldGroupWS[] metaFieldGroupArray = new MetaFieldGroupWS[metaFieldGroups.size()];
        int idx = 0;
        for (MetaFieldGroup metaFieldGroup : metaFieldGroups) {
            metaFieldGroupArray[idx++] = getWS(metaFieldGroup);
        }
        return metaFieldGroupArray;

    }

    @Override
    public AccountInformationTypeWS getAccountInformationTypeWS(MetaFieldGroup dto) throws Exception {
        // TODO Auto-generated method stub
        AccountInformationTypeWS ws = new AccountInformationTypeWS();
        if (null != dto) {

            ws.setId(dto.getId());

            ws.setDateCreated(dto.getDateCreated());
            ws.setDateUpdated(dto.getDateUpdated());
            ws.setDisplayOrder(dto.getDisplayOrder());
            ws.setEntityId(dto.getEntity().getId());
            ws.setEntityType(dto.getEntityType());

            if (dto.getMetaFields() != null && dto.getMetaFields().size() > 0) {
                Set<MetaFieldWS> tmpMetaFields = new HashSet<MetaFieldWS>();
                for (MetaField metafield : dto.getMetaFields()) {
                    tmpMetaFields.add(metaFieldService.getWS(metafield));
                }
                ws.setMetaFields(tmpMetaFields.toArray(new MetaFieldWS[tmpMetaFields.size()]));
            }
            //if (dto.getDescription(ServerConstants.LANGUAGE_ENGLISH_ID) != null) {
             //   List<InternationalDescriptionWS> tmpDescriptions = new ArrayList<InternationalDescriptionWS>(1);
                //tmpDescriptions.add(DescriptionBL.getInternationalDescriptionWS(dto.getDescriptionDTO(ServerConstants.LANGUAGE_ENGLISH_ID)));
                //ws.setDescriptions(tmpDescriptions);
           // }

        }
        return ws;
    }

    @Override
    public MetaFieldGroupWS getWS(MetaFieldGroup groupDTO) throws Exception {
        // TODO Auto-generated method stub
        MetaFieldGroupWS ws = new MetaFieldGroupWS();
        if (groupDTO != null) {


            ws.setId(groupDTO.getId());
            ws.setDateCreated(groupDTO.getDateCreated());
            ws.setDateUpdated(groupDTO.getDateUpdated());
            ws.setDisplayOrder(groupDTO.getDisplayOrder());
            ws.setEntityId(groupDTO.getEntity().getId());
            ws.setEntityType(groupDTO.getEntityType());

            if (groupDTO.getMetaFields() != null && groupDTO.getMetaFields().size() > 0) {
                Set<MetaFieldWS> tmpMetaFields = new HashSet<MetaFieldWS>();
                for (MetaField metafield : groupDTO.getMetaFields()) {
                    tmpMetaFields.add(metaFieldService.getWS(metafield));
                }
                ws.setMetaFields(tmpMetaFields.toArray(new MetaFieldWS[tmpMetaFields.size()]));
            }
            //if (groupDTO.getDescription(ServerConstants.LANGUAGE_ENGLISH_ID) != null) {
                //List<InternationalDescriptionWS> tmpDescriptions = new ArrayList<InternationalDescriptionWS>(1);
                //tmpDescriptions.add(DescriptionBL.getInternationalDescriptionWS(groupDTO.getDescriptionDTO(ServerConstants.LANGUAGE_ENGLISH_ID)));
                //ws.setDescriptions(tmpDescriptions);
           // }

        }
        return ws;

    }

    @Override
    public MetaFieldGroup getDTO(MetaFieldGroupWS ws) throws Exception {
        // TODO Auto-generated method stub
        MetaFieldGroup mfGroup = new MetaFieldGroup();

        mfGroup.setDisplayOrder(ws.getDisplayOrder());
        mfGroup.setEntityType(ws.getEntityType());
        mfGroup.setId(ws.getId());
        try {

            if (ws.getMetaFields() != null) {
                MetaField metaField;
                Set<MetaField> metafieldsDTO = new HashSet<MetaField>();
                for (MetaFieldWS metafieldWS : ws.getMetaFields()) {
                    metaField = metaFieldDAO.findById(metafieldWS.getId()).get();
                    if (metaField != null) {
                        metafieldsDTO.add(metaField);
                    }
                }
                mfGroup.setMetaFields(metafieldsDTO);
            }

            if (ws.getId() > 0) {
                List<InternationalDescriptionWS> descriptions = ws.getDescriptions();
                for (InternationalDescriptionWS description : descriptions) {
                    if (description.getLanguageId() != null
                            && description.getContent() != null) {
                        if (description.isDeleted()) {
                            //mfGroup.deleteDescription(description
                            //		.getLanguageId());
                        } else {
                            //mfGroup.setDescription(description.getContent(),
                                 //   description.getLanguageId());
                        }
                    }
                }
            }
        } catch (Exception e) {

            String[] error = new String[]{
                    "MetaFieldGroupWS,metafieldGroups,cannot.convert.metafieldgroupws.error"};
            throw new Exception("Exception converting MetaFieldGroupWS to DTO object" + getErrorsAsString(error), e);

        }
        return mfGroup;

    }


}
