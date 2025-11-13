package org.fmm.communitymgmt.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.Invitation;
import org.fmm.communitymgmt.common.model.InvitationJsonViews;
import org.fmm.communitymgmt.common.model.common.TMembership;
import org.fmm.communitymgmt.controller.interceptors.ParamSource;
import org.fmm.communitymgmt.controller.interceptors.RequiresClaim;
import org.fmm.communitymgmt.dto.DataJwtSecContext;
import org.fmm.communitymgmt.dto.FullInvitationDTO;
import org.fmm.communitymgmt.dto.InvitationDTO;
import org.fmm.communitymgmt.dto.SignatureDTO;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
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
	
	@GetMapping("/{communityId}/invitations")
	@JsonView(InvitationJsonViews.SimpleInvitation.class)
	@RequiresClaim(claim = "secContext", jsonAttr = "myCommunitiesIds", allowList = true, parameter = "communityId", source = ParamSource.PATH)
    public ResponseEntity<List<Invitation>> listInvitations(@RequestHeader Map<String, String> headers, 
    		Authentication authentication, @CurrentSecurityContext SecurityContext context,
    		@AuthenticationPrincipal OAuth2Token principal, @PathVariable(name = "communityId") Integer communityId) {
		SignedJWT dataJwt = null;

        if (principal != null) {
        	try {
            	dataJwt = initJwt(headers);
            	communityId = validateCommunityIdFromJwt(dataJwt, communityId);
            	
        	} catch (ParseException pe) {
        		return ResponseEntity.badRequest().build();
        	}
			if (communityId != null)
				return ResponseEntity.ok(enrollmentService.getInvitationsByCommunity(communityId));
			else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).build();
			}
        }
		return ResponseEntity.notFound().build();
    }
	
	@GetMapping("persons/{personId}/invitations")
    public ResponseEntity<List<FullInvitationDTO>> listInvitationsByPerson(@RequestHeader Map<String, String> headers, 
    		Authentication authentication, @CurrentSecurityContext SecurityContext context,
    		@AuthenticationPrincipal OAuth2Token principal, @PathVariable(name = "personId") Integer personId) {
		List<Invitation> list = null;
		List<FullInvitationDTO> dtos = null;
        if (principal != null) {
			if (personId != null) {
				list = enrollmentService.getInvitationsByPerson(personId); 
				dtos = list.stream()
						.map( it -> {
							return FullInvitationDTO.fromInvitation(it);
						}).toList();
				return ResponseEntity.ok(dtos);
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).build();
			}
        }
		return ResponseEntity.notFound().build();
    }
	
	@GetMapping("persons/{personId}/invitation")
    public ResponseEntity<FullInvitationDTO> invitationsByPerson(@RequestHeader Map<String, String> headers, 
    		Authentication authentication, @CurrentSecurityContext SecurityContext context,
    		@AuthenticationPrincipal OAuth2Token principal, @PathVariable(name = "personId") Integer personId) {
		List<Invitation> list = null;
		List<FullInvitationDTO> dtos = null;
        if (principal != null) {
			if (personId != null) {
				list = enrollmentService.getInvitationsByPerson(personId); 
				dtos = list.stream()
						.map( it -> {
							return FullInvitationDTO.fromInvitation(it);
						}).toList();
//				return ResponseEntity.notFound().build();
//				return ResponseEntity.ok(new FullInvitationDTO());
				return ResponseEntity.ok(dtos.get(0));
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).build();
			}
        }
		return ResponseEntity.notFound().build();
    }
	
	/**
	 * TODO Revisar seguridad, sólo puede crear invitaciones el responsable, quizá un cónyuge, pero sería mejor otro método
	 * TODO La invitación debe venir con la firma, que habría que comprobar primero y guardar después.
	 * @param invitation
	 * @param headers
	 * @param authentication
	 * @param context
	 * @param principal
	 * @return
	 */
	@PostMapping("/{communityId}/invitations")
    public ResponseEntity<Invitation> createInvitation(@RequestBody InvitationDTO invitationDTO, @RequestHeader Map<String, String> headers, 
    		Authentication authentication, @CurrentSecurityContext SecurityContext context,
    		@AuthenticationPrincipal OAuth2Token principal, @PathVariable(name = "communityId") Integer communityId
    		) {
		Long iat = System.currentTimeMillis();
		Invitation invitation = new Invitation();
		invitation.setCommunity(communityService.getCommunityById(invitationDTO.getCommunityId()).get());
		// De front, viene en días
		invitation.setExp(iat + invitationDTO.getExp()*24*60*60*1000);
		invitation.setIat(iat);
		invitation.setResponsiblePublicKey(invitationDTO.getKpub());
		invitation.setName(invitationDTO.getName());
		invitation.setNbf(iat);
		invitation.setResponsiblePublicKey(invitationDTO.getSignature());
		invitation.setState(invitationDTO.getState());
		
		TMembership type = new TMembership();
		if (invitationDTO.getInvitationType() == null)
			invitationDTO.setInvitationType(1);
		type.setId(invitationDTO.getInvitationType());
		invitation.setInvitationType(type);
		invitation.setForMarriage(invitationDTO.getForMarriage());
		return ResponseEntity.ok(this.enrollmentService.createInvitation(invitation));
	}
	
	/**
	 * Creates the invitations structures
	 * @param invitationDTO
	 * @param headers
	 * @param authentication
	 * @param context
	 * @param principal
	 * @param communityId
	 * @return
	 */
//	@PutMapping("/{communityId}/invitations/{invitationId}")
	@PutMapping("/{communityId}/invitations")
//	@JsonView(InvitationJsonViews.SimpleInvitation.class)
    public ResponseEntity<FullInvitationDTO> updateInvitation(@RequestBody InvitationDTO invitationDTO, @RequestHeader Map<String, String> headers, 
    		Authentication authentication, @CurrentSecurityContext SecurityContext context,
    		@AuthenticationPrincipal OAuth2Token principal, @PathVariable(name = "communityId") Integer communityId) {
		Invitation invitation = null;
		Integer personId = null;
		Community community = communityService.getCommunityById(invitationDTO.getCommunityId()).get();
		invitation = InvitationDTO.toInvitation(invitationDTO, community);
		
        if (principal != null) {
			if (communityId != null && communityId.equals(invitationDTO.getCommunityId())) {
				personId = invitationDTO.getPersonId();
            	invitation = enrollmentService.createAllInvitationStructure(invitation,personId);
				return ResponseEntity.ok(FullInvitationDTO.fromInvitation(invitation));
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).build();
			}
        }
		return ResponseEntity.notFound().build();
	}
	/**
	 * TODO
	 * @param invitationDTO
	 * @param headers
	 * @param authentication
	 * @param context
	 * @param principal
	 * @param communityId
	 */
	@PostMapping("/{communityId}/invitations/{invitationId}/signature")
    public ResponseEntity<FullInvitationDTO> updateBrotherSignature(
    		@RequestHeader Map<String, String> headers, 
    		Authentication authentication, @CurrentSecurityContext SecurityContext context,
    		@AuthenticationPrincipal OAuth2Token principal, 
    		@PathVariable(name = "communityId") Integer communityId,
    		@PathVariable(name = "invitationId") Integer invitationId,
    		@RequestBody SignatureDTO signatureDTO) {

		Invitation invitation = null;
		if (principal != null) {
			
        	invitation = enrollmentService.updateBrotherSignature(communityId, invitationId, signatureDTO);
        	if (invitation != null)
        		return ResponseEntity.ok(FullInvitationDTO.fromInvitation(invitation));
        }
		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping("/{communityId}/invitations/{invitationId}/accept")
	@RequiresClaim(claim = "communityId", parameter = "communityId")
    public ResponseEntity<String> acceptBrother(
    		@RequestHeader Map<String, String> headers, 
    		Authentication authentication, @CurrentSecurityContext SecurityContext context,
    		@AuthenticationPrincipal OAuth2Token principal, 
    		@PathVariable(name = "communityId") Integer communityId,
    		@PathVariable(name = "invitationId") Integer invitationId) {

		if (principal != null) {
			try {
				enrollmentService.acceptBrother(communityId, invitationId);
			} catch (RuntimeException re) {
				return ResponseEntity.notFound().build();
			} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
			}
    		return ResponseEntity.ok("Relationship created");
        }
		return ResponseEntity.notFound().build();
		
	}
	
	private SignedJWT initJwt(Map<String, String> headers) throws ParseException {
    	String jwtString = headers.get("x-data-jwt");
		SignedJWT dataJwt = UserInfoDTO.parse(jwtString);
    	dataJwt.getJWTClaimsSet();
    	return dataJwt;
	}
	
	private Integer validateCommunityIdFromJwt(SignedJWT dataJwt, Integer communityId) throws NumberFormatException, ParseException {
		String idsStr = null;
		try {
			idsStr = dataJwt.getJWTClaimsSet().getStringClaim("secContext");			
		} catch (Exception e) {
			
		}
		try {
			if (DataJwtSecContext.fromJson(idsStr).getMyCommunitiesIds().contains(communityId))
				return communityId;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
	}
	
	@SuppressWarnings("unused")
	@Deprecated
	private Integer getPersonIdFromJwt(SignedJWT dataJwt) {
		String secContext = null;
		try {
			secContext = dataJwt.getJWTClaimsSet().getStringClaim("secContext");			
		} catch (Exception e) {
			
		}
		try {
			return DataJwtSecContext.fromJson(secContext).getPersonId();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}