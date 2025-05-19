package org.fmm.communitymgmt.contrroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.Relationship;
import org.fmm.communitymgmt.service.CommunityService;
import org.fmm.communitymgmt.service.RelationshipService;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jwt.SignedJWT;

@RestController
@RequestMapping("/api/communities")

public class CommunityMgmtListController extends AbstractCommunityMgmtController {
	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private RelationshipService relationshipService;
	@GetMapping("/{communityId}/brothers")
	public ResponseEntity<List<Relationship>> getList(@PathVariable("communityId") Integer communityId) {
		List<Relationship> response = null;
		
		response = relationshipService.getMembersOfCommunity(communityId);
		if (response != null && !response.isEmpty()) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping
	public ResponseEntity<List<Community>> getList(@RequestHeader Map<String, String> headers, 
    		Authentication authentication, @CurrentSecurityContext SecurityContext context,
    		@AuthenticationPrincipal OAuth2Token principal) {
		
		SignedJWT dataJwt = null;
		List<Community> theList = null;
		if (principal != null) {
			try {
				dataJwt=initDataJwt(headers);
				List<Integer> ids = new ArrayList<Integer>();
				ids = getCommunitiesFromJwt(dataJwt);
				theList = communityService.getCommunitiesById(ids);
				if (!theList.isEmpty())
					return ResponseEntity.ok(theList);
				else
					return ResponseEntity.notFound().build();
				
			} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
			}
		} else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
}