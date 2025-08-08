package org.fmm.communitymgmt.controller;

import java.util.Optional;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.exceptions.BusinessNotFoundException;
import org.fmm.communitymgmt.service.PersonInChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunityMgmtPersonInChargeController {
	@Autowired
	PersonInChargeService personInChargeService;

	@PostMapping("/communities")
    public Community createCommunity(@AuthenticationPrincipal OAuth2User principal,
    		String communityNumber, String parish, String address, String addressNumber, String postalCode, String city, String country, Boolean activated) {
		return personInChargeService.createCommunity(communityNumber, parish, address, addressNumber, postalCode, city, country, activated);
    }

	/**
	 * Lo mantengo para ver el ejemplo de optCommunity...
	 * @param idCommunity
	 * @return
	 */
//	@GetMapping("/communities/{idCommunity}")
	public ResponseEntity<Community> getCommunity1(@PathVariable("idCommunity") Integer idCommunity) {
		Optional<Community> optCommunity = null;
		optCommunity = personInChargeService.getCommunity(idCommunity);
		/**
		 * map 	Es un método de Optional, comprueba si ese optional isPresent().
		 * 		Luego incluye lo que haya en la expresión y el propio objeto 
		 * 		La expresión (referencia a método) ResponseEntity::ok es equivalente a la lambda (community) -> ResponseEntity.ok(community) 
		 * 		(es decir, toma la Community encontrada y la envuelve en un objeto ResponseEntity con el estado HTTP 200).
		 * El método de Option orElseGet
		 */
		return optCommunity
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/communities/{idCommunity}")
	public ResponseEntity<Community> getCommunity(@AuthenticationPrincipal OAuth2User principal, @PathVariable("idCommunity") Integer idCommunity) {
		Optional<Community> optCommunity = null;
		try {
			optCommunity = personInChargeService.getCommunity(idCommunity);
			return optCommunity.map(ResponseEntity::ok)
					.orElseThrow(() -> new BusinessNotFoundException("", idCommunity));
		} catch (BusinessNotFoundException bnfe) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
}