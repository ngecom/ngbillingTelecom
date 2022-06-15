/*
 jBilling - The Enterprise Open Source Billing System
 Copyright (C) 2003-2011 Enterprise jBilling Software Ltd. and Emiliano Conde

 This file is part of jbilling.

 jbilling is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 jbilling is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with jbilling.  If not, see <http://www.gnu.org/licenses/>.

 This source was modified by Web Data Technologies LLP (www.webdatatechnologies.in) since 15 Nov 2015.
 You may download the latest source from webdataconsulting.github.io.

 */

package com.ngbilling.core.payload.request.user;

import java.util.Collection;
import java.util.Locale;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.ngbilling.core.server.persistence.dto.user.UserDTO;

/**
 * A spring security {@link org.springframework.security.core.userdetails.UserDetails}
 * implementation that includes the users company id.
 * <p>
 * The UserDetailsService expects to encounter usernames that also include a company ID token. This
 * class maintains the username in this format. Do not change this behaviour as it will break "remember me"
 * and other advanced security features that use the UserDetailsService!
 *
 * @author Brian Cowdery
 * @since 04-10-2010
 */
public class CompanyUserDetails extends User {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final UserDTO user;
    private final Locale locale;
    private final Integer mainRoleId;
    private final Integer companyId;
    private final Integer currencyId;
    private final Integer languageId;
    private final Integer userId;

    public CompanyUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
                              boolean credentialsNonExpired, boolean accountNonLocked,
                              Collection<GrantedAuthority> authorities,
                              UserDTO user, Locale locale,
                              Integer id, Integer mainRoleId, Integer companyId, Integer currencyId, Integer languageId) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
        this.user = user;
        this.locale = locale;
        this.mainRoleId = mainRoleId;
        this.companyId = companyId;
        this.currencyId = currencyId;
        this.languageId = languageId;
        this.userId = id;
    }

    /**
     * Returns the user.
     *
     * @return user
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * Returns the username without the company ID token.
     * <p>
     * This class must store the company ID with the username when returned from
     * {@link #getUsername()} for "remember me" and other advanced security features
     * to work with our implementation of the UserDetailsService.
     *
     * @return raw username
     */
    public String getPlainUsername() {
        return user.getUserName();
    }

    /**
     * Returns the users {@link Locale} according to their language and/or country code.
     *
     * @return user locale
     * @see com.sapienter.jbilling.server.user.UserBL#getLocale()
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Returns the users main role ID.
     *
     * @return main role id
     */
    public Integer getMainRoleId() {
        return mainRoleId;
    }

    /**
     * Returns the user ID as an Integer. This is the same as calling {@link #getId()}.
     *
     * @return user ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Returns the users company ID.
     *
     * @return user company ID
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * Returns the users currency ID.
     *
     * @return user currency ID
     */
    public Integer getCurrencyId() {
        return currencyId;
    }

    /**
     * Returns the users language ID.
     *
     * @return user language ID
     */
    public Integer getLanguageId() {
        return languageId;
    }

    /**
     * Returns first name for greeting.
     *
     * @return
     */
    public String getGreetingName() {
        return this.user.getContact().getFirstName();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CompanyUserDetails");
        sb.append("{id=").append(userId);
        sb.append(", username=").append("'").append(getUsername()).append("'");
        sb.append(", mainRoleId=").append(getMainRoleId());
        sb.append(", companyId=").append(getCompanyId());
        sb.append(", currencyId=").append(getCurrencyId());
        sb.append(", languageId=").append(getLanguageId());
        sb.append(", enabled=").append(isEnabled());
        sb.append(", accountExpired=").append(!isAccountNonExpired());
        sb.append(", credentialsExpired=").append(!isCredentialsNonExpired());
        sb.append(", accountLocked=").append(!isAccountNonLocked());
        sb.append('}');
        return sb.toString();
    }

}
