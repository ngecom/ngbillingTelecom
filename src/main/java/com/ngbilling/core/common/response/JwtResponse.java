package com.ngbilling.core.common.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Integer id;
	private String username;
	private String refreshToken;
	private String organization;
	private List<String> roles;

	public JwtResponse(String accessToken,String refreshToken,Integer id, String username, String organization, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.organization = organization;
		this.refreshToken = refreshToken;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
