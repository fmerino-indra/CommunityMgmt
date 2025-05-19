package org.fmm.communitymgmt.contrroller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.fmm.communitymgmt.common.model.Invitation;
import org.fmm.communitymgmt.dto.InvitationDTO;
import org.fmm.communitymgmt.dto.UserInfoDTO;
import org.fmm.communitymgmt.service.CommunityService;
import org.fmm.communitymgmt.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jwt.SignedJWT;

@RestController
@RequestMapping("/api/enrollment")
public class CommunityMgmtEnrollmentController {
//	@Autowired
//	private UserInfoService userInfoService;
	@Autowired
	private EnrollmentService enrollmentService;

	@Autowired
	private CommunityService communityService;
	
	@GetMapping("/invitations")
    public ResponseEntity<List<Invitation>> listInvitations(@RequestHeader Map<String, String> headers, 
    		Authentication authentication, @CurrentSecurityContext SecurityContext context,
    		@AuthenticationPrincipal OAuth2Token principal) {
		SignedJWT dataJwt = null;
		Integer communityId = null;
//		Jwt jwtPrincipal = null;
//		Optional<UserInfoDTO> optUserInfoDto = null;
//		UserInfoDTO userInfoDTO = null;
		

        if (principal != null) {
        	try {
            	dataJwt = initJwt(headers);
            	communityId = getCommunityFromJwt(dataJwt);
            	
        	} catch (ParseException pe) {
        		return ResponseEntity.badRequest().build();
        	}
			if (communityId != null)
				return ResponseEntity.ok(enrollmentService.getInvitationsByCommunity(communityId));
			else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).build();
			}
        	
/*        	
    		jwtPrincipal = (Jwt)principal;
			optUserInfoDto = userInfoService.getSocialUserInfoByProviderId(jwtPrincipal.getSubject(),jwtPrincipal);
			
			if (optUserInfoDto.isPresent()) {
				userInfoDTO = optUserInfoDto.get();
				if (userInfoDTO.getCommunity() != null)
					return ResponseEntity.ok(enrollmentService.getInvitationsByCommunity(userInfoDTO.getCommunity().getId()));
				else {
					return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).build();
				}
			}
*/			
        }
		return ResponseEntity.notFound().build();
    }
	
	/**
	 * TODO Revisar seguridad
	 * @param invitation
	 * @param headers
	 * @param authentication
	 * @param context
	 * @param principal
	 * @return
	 */
	@PostMapping("/invitations")
    public ResponseEntity<Invitation> createInvitation(@RequestBody InvitationDTO invitationDTO, @RequestHeader Map<String, String> headers, 
    		Authentication authentication, @CurrentSecurityContext SecurityContext context,
    		@AuthenticationPrincipal OAuth2Token principal) {
		Long iat = System.currentTimeMillis();
		Invitation invitation = new Invitation();
		invitation.setCommunity(communityService.getCommunityById(invitationDTO.getCommunityId()).get());
		invitation.setExp(iat + invitationDTO.getExp()*24*60*60*1000);
		invitation.setIat(iat);
		invitation.setKpub(invitationDTO.getKpub());
		invitation.setName(invitationDTO.getName());
		invitation.setNbf(iat);
		invitation.setSignature(invitationDTO.getSignature());
		invitation.setState(invitationDTO.getState());
		return ResponseEntity.ok(this.enrollmentService.createInvitation(invitation));
	}
	
	private SignedJWT initJwt(Map<String, String> headers) throws ParseException {
    	String jwtString = headers.get("x-data-jwt");
		SignedJWT dataJwt = UserInfoDTO.parse(jwtString);
    	dataJwt.getJWTClaimsSet();
    	return dataJwt;
	}
	
	private Integer getCommunityFromJwt(SignedJWT dataJwt) throws NumberFormatException, ParseException {
    	return Integer.valueOf(dataJwt.getJWTClaimsSet().getClaim("communityId").toString());
    	
		
		
	}
}