package org.fmm.communitymgmt.controller;

import java.util.Map;
import java.util.Optional;

import org.fmm.communitymgmt.dto.UserInfoDTO;
import org.fmm.communitymgmt.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/userinfo")
public class CommunityMgmtUserInfoController {
	
	@Autowired
	private UserInfoService userInfoService;
//	
//	@GetMapping("/userInfo")
//    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
//    	if (principal != null) {
//	    	Map<String, Object> elMapa = principal.getAttributes();
//	    	return elMapa;
//    	} else 
//    		return new HashMap<String, Object>();
//    }
	/**
	 * Devuelve el SocialUser
	 * Al ser API, no hay sesión ni @AuthenticationPrincipal OAuth2User principal
	 * @param headers
	 * @param authentication
	 * @param context
	 * @return
	 */
	@GetMapping("/userinfo")
    public ResponseEntity<UserInfoDTO> user(@RequestHeader Map<String, String> headers, 
    		Authentication authentication, @CurrentSecurityContext SecurityContext context,
    		@AuthenticationPrincipal OAuth2User principal) {
		String jwtStr = null;
		Jwt jwt = null;
		Authentication auth = null;
		JwtAuthenticationToken jwtAuthenticationToken = null;
//		SocialUserInfo socialUserInfo = null;
		
		Optional<UserInfoDTO> optUserInfoDto = null;
		UserInfoDTO userInfoDTO = null;
		
		if (headers.containsKey("authorization") && context != null) {
            context = SecurityContextHolder.getContext();
            auth = context.getAuthentication();
            if (auth instanceof JwtAuthenticationToken) {
            	jwtAuthenticationToken = (JwtAuthenticationToken)auth;
    			jwt = jwtAuthenticationToken.getToken();

	            if (jwt != null) {
					jwtStr = headers.get("authorization").substring(7);
					System.out.println("JWT authorization: "+jwtStr);
					
					optUserInfoDto = userInfoService.getSocialUserInfoByProviderId(jwt.getSubject(),jwt);
					
					if (optUserInfoDto.isPresent()) {
						
						userInfoDTO = optUserInfoDto.get();
						if (userInfoDTO.getMyCommunities() != null && !userInfoDTO.getMyCommunities().isEmpty()) {
							try {
								userInfoDTO.generateJWT();
							} catch (Exception e) {
								return ResponseEntity.internalServerError().build();
							}
						}
						return ResponseEntity.ok(userInfoDTO);
					} else {
						// OLD, to delete
						// If doesn't exist, build a SocialUserInfo with Jwt values
						/*
						userInfoDTO = new UserInfoDTO();
						
						socialUserInfo = new SocialUserInfo(
								null, 
								"google",
								jwt.getSubject(),
								jwt.getClaim("name"),
								jwt.getClaim("email"),
								jwt.getClaim("picture"),
								jwt.getClaim("email_verified")
								);
						userInfoDTO.setSocialUserInfo(socialUserInfo);
						*/
						// TODO Generalizar para diferentes providers
//						socialUser = new SocialUser();
//						socialUser.setEmail(jwt.getClaim("email"));
//						socialUser.setEmailVerified(jwt.getClaim("email_verified"));
//						socialUser.setImageUrl(jwt.getClaim("picture"));
//						socialUser.setIss(JwtUserInfo.GOOGLE_ISS);
//						socialUser.setProvider(AuthProvider.google.toString());
					}
	            }
            }
		}
		return ResponseEntity.notFound().build();
    }
	
	@PostMapping("/userinfo")
    public ResponseEntity<UserInfoDTO> createUserInfo(@RequestBody UserInfoDTO userInfoDTO,
    		@AuthenticationPrincipal OAuth2Token principal) {
		
		if (principal != null) {
			System.out.println(userInfoDTO);
		}
		userInfoDTO=userInfoService.createPersonAndSocialUser(userInfoDTO);
		return ResponseEntity.ok(userInfoDTO);
	}
	
	@PostMapping("/communities")
    public ResponseEntity<UserInfoDTO> updateUserInfo(@RequestBody UserInfoDTO userInfoDTO,
    		@AuthenticationPrincipal OAuth2Token principal) {
		
		if (principal != null) {
			System.out.println(userInfoDTO);
		}
		userInfoDTO=userInfoService.createCommunity(userInfoDTO);
		return ResponseEntity.ok(userInfoDTO);
	}
	
}