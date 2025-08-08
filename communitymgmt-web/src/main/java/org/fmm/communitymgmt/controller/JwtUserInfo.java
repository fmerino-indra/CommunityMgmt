package org.fmm.communitymgmt.controller;

import java.util.Map;

import org.fmm.oauth.springsocial.security.oauth2.user.OAuth2UserInfo;
import org.fmm.oauth.springsocial.security.oauth2.user.UserInfo;
import org.springframework.security.oauth2.jwt.Jwt;

@Deprecated
public class JwtUserInfo extends OAuth2UserInfo implements UserInfo {
//	private UserInfo userInfo;
	private Jwt jwt;
	
	public JwtUserInfo(Map<String, Object> attributes, Jwt jwt) {
		super(attributes);
		this.jwt = jwt;
	}
	
	public String getUpn() {
		return getEmail();
	}

	@Override
	public String getName() {
		return jwt.getClaim("name");
	}

	@Override
	public String getEmail() {
		return jwt.getClaim("email");
	}

	@Override
	public String getImageUrl() {
		return jwt.getClaim("picture");
	}

	@Override
	public Boolean getEmailVerified() {
		return jwt.getClaim("email_verified");
	}

	@Override
	public String getProviderId() {
		return jwt.getSubject();
	}
}
