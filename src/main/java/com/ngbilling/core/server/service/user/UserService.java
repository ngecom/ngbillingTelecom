package com.ngbilling.core.server.service.user;

import com.ngbilling.core.payload.request.user.MainSubscriptionWS;
import com.ngbilling.core.server.persistence.dto.contact.ContactDTO;
import com.ngbilling.core.server.persistence.dto.user.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface UserService {

    public Locale getLocale(UserDTO user);

    public Integer getMainRole(UserDTO user);

    public MainSubscriptionDTO convertMainSubscriptionFromWS(MainSubscriptionWS mainSubscriptionWS, Integer entityId);

    public CompanyDTO getEntity(Integer userId);

    public UserDTO createCompany(UserDTO userDTO);

    public UserDTO createAdminUser(UserDTO userDTO);

    public CompanyDTO findByDescription(String companyName);

    public ContactDTO createContact(ContactDTO contactUserDTO, String tableName,Integer foreignId);

    public Boolean existsByUserName(String username);

    public Integer findEmail(String email);

    public UserStatusDTO findByStatus(Integer statusID);

    public SubscriberStatusDTO findBySubscriptionStatus(Integer statusID);

    public List<RoleDTO> findRoleTypeIdAndCompanyId(Integer roleTypeId, Integer companyId);
    public List<RoleDTO> findByRoleTypeId(Integer roleTypeId);

    public RoleDTO createRole(Integer roleTypeId, UserDTO userDTO);

    public Optional<RefreshToken> findByToken(String token);
    public RefreshToken createRefreshToken(Integer userId);
    public RefreshToken verifyExpiration(RefreshToken token);
    public int deleteByUserName(String userName);

}
