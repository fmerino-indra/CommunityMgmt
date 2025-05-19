package org.fmm.communitymgmt.dto;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.fmm.communitymgmt.common.model.Membership;
import org.fmm.communitymgmt.common.model.Person;
import org.fmm.communitymgmt.contrroller.SocialUserInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class UserInfoDTO {
	private SocialUserInfo socialUserInfo = null;
	private Person person = null;
	// Is the selected community, only if person has only one
	private CommunityDTO selectedCommunity = null;
	private List<CommunityDTO> myCommunities = null;
	
	private String dataJWT = null;
	
	public SocialUserInfo getSocialUserInfo() {
		return socialUserInfo;
	}
	public void setSocialUserInfo(SocialUserInfo userInfo) {
		this.socialUserInfo = userInfo;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = (Person)person;
	}
	public List<CommunityDTO> getMyCommunities() {
		return myCommunities;
	}
//	public void setMyCommunities(List<CommunityDTO> myCommunities) {
//		this.myCommunities = myCommunities;
//	}
	public String getDataJWT() {
		return dataJWT;
	}
	
	/**
	 * TODO JWKS, crear claves duraderas, etc.
	 * @throws JOSEException
	 */
	
	public void generateJWT() throws JOSEException, JsonProcessingException {
		Long iat = System.currentTimeMillis();
		//Instant iatInstant = Instant.now();
		Instant iatInstant = Instant.ofEpochMilli(iat);
		Instant expInstant = Instant.ofEpochMilli(iat + 60*60*1000);
		DataJwtSecContext secContext = new DataJwtSecContext();
		
		RSAKey rsaJWK = new RSAKeyGenerator(2048)
				.keyID("claveDePruebas")
				.generate();
		
		RSAKey rsaPublicJWK = rsaJWK.toPublicJWK();
		System.out.println(rsaPublicJWK.toJSONString());
		JWSSigner signer = new RSASSASigner(rsaJWK);
		List<Integer> idList = null;
		Map<Integer, List<Integer>> comResponsibles = null;
		if (this.getMyCommunities() != null) {
			idList = this.getMyCommunities().stream()
					.map(c -> c.getMyCommunityData().getId())
					.collect(Collectors.toList());
			
			comResponsibles = getMyCommunities().stream()
			    .collect(Collectors.<CommunityDTO, Integer, List<Integer>>toMap(
			        (CommunityDTO c) -> c.getMyCommunityData().getId(),  // clave: Integer
			        (CommunityDTO c) -> c.getMyCharges().stream()
			                             .map(t -> t.getId())   // valor: List<Integer>
			                             .collect(Collectors.toList())
			    ));
		}
		
		
		secContext.setMyCommunitiesIds(idList);
		secContext.setMyCharges(comResponsibles);
		secContext.setPersonId(person.getId());
		secContext.setName(((Person)person).getName());
		
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.issuer("CommunityMgmtBack") // Convendría poner una URL
				.subject(socialUserInfo.getProviderId())
				.audience(socialUserInfo.getEmail())
				.issueTime(Date.from(iatInstant))
				.notBeforeTime(Date.from(iatInstant))
				.expirationTime(Date.from(expInstant))
				.claim("secContext", DataJwtSecContext.toJson(secContext))
				.build();

		JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS512)
				.keyID(rsaJWK.getKeyID())
				.build();

		SignedJWT signedJWT = new SignedJWT(header, claimsSet);
		signedJWT.sign(signer);
		String s = signedJWT.serialize();
		this.dataJWT = s;
		System.out.println(s);
	}
	
	public static SignedJWT parse(String jwtString) throws ParseException {
		return SignedJWT.parse(jwtString);
	}
	public void fromMembership(List<Membership> memberships) {
		if (memberships != null) {
			myCommunities =
				memberships.stream()
					.map(m -> {
						CommunityDTO dto = new CommunityDTO();
						dto.setMyCommunityData(m.getCommunity());
						dto.setMyCharges(m.getCharges());
						return dto;
					})
					.collect(Collectors.toList());
		}
	}
	public CommunityDTO getSelectedCommunity() {
		return selectedCommunity;
	}
	public void setSelectedCommunity(CommunityDTO selectedCommunity) {
		this.selectedCommunity = selectedCommunity;
	}
}
