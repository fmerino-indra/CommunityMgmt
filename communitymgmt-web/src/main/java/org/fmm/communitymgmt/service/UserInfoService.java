package org.fmm.communitymgmt.service;

import java.util.Optional;

import org.fmm.communitymgmt.dto.UserInfoDTO;
import org.fmm.oauth2.common.model.model.SocialUser;
import org.springframework.security.oauth2.jwt.Jwt;

public interface UserInfoService {
    public Optional<SocialUser> getUserInfo(String upn, String iss);
    public Optional<SocialUser> getSocialUserByProviderId(String providerId);
    public Optional<UserInfoDTO> getSocialUserInfoByProviderId(String providerId, Jwt jwt);
    public UserInfoDTO createPersonAndSocialUser(UserInfoDTO userInfoDTO);
}