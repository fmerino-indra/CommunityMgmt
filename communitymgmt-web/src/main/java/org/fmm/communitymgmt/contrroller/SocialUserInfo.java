package org.fmm.communitymgmt.contrroller;

import java.util.Map;

import org.fmm.oauth.springsocial.security.oauth2.user.UserInfo;

public class SocialUserInfo implements UserInfo {
	private Map<String, Object> attributes = Map.of();
	// Id de la BBDD
	private Integer id;
	private String provider;
	private String providerId;
	private String name;
	private String email;
	private String imageUrl;
	private Boolean emailVerified;
	private String registrationId;
	private String iss;
	
	public SocialUserInfo() {
		super();
	}
	public SocialUserInfo(Integer id, String provider, String providerId, String name, String email, String imageUrl, Boolean emailVerified) {
		super();
		this.id = id;
		this.provider = provider;
		this.providerId = providerId;
		this.name = name;
		this.email = email;
		this.imageUrl = imageUrl;
		this.emailVerified = emailVerified;
	}
	
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getImageUrl() {
		return imageUrl;
	}

	@Override
	public Boolean getEmailVerified() {
		// TODO Auto-generated method stub
		return emailVerified;
	}

	@Override
	public String getRegistrationId() {
		// TODO Auto-generated method stub
		return registrationId;
	}

	@Override
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	@Override
	public String getIss() {
		return iss;
	}

	@Override
	public void setIss(String iss) {
		this.iss=iss;
	}


	public String getProvider() {
		return provider;
	}


	public String getProviderId() {
		return providerId;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
}
