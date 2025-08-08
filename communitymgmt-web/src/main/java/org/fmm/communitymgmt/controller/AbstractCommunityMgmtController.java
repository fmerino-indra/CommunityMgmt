package org.fmm.communitymgmt.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.fmm.communitymgmt.dto.UserInfoDTO;

import com.nimbusds.jwt.SignedJWT;

public abstract class AbstractCommunityMgmtController {
	public static String H_X_DATA_JWT = "x-data-jwt";
	
	protected SignedJWT initDataJwt(Map<String, String> headers) throws ParseException {
    	String jwtString = headers.get(H_X_DATA_JWT);
		SignedJWT dataJwt = UserInfoDTO.parse(jwtString);
    	dataJwt.getJWTClaimsSet();
    	return dataJwt;
	}
	
	protected Integer getCommunityFromJwt(SignedJWT dataJwt) throws NumberFormatException, ParseException {
    	return Integer.valueOf(dataJwt.getJWTClaimsSet().getClaim("communityId").toString());
	}
	protected List<Integer> getCommunitiesFromJwt(SignedJWT dataJwt) throws NumberFormatException, ParseException {
		List<Integer> ids = new ArrayList<Integer>();
		
		String communityIdsStr = dataJwt.getJWTClaimsSet().getClaim("communityId").toString();
		Object aux2 = dataJwt.getJWTClaimsSet().getClaim("communityIds");
		@SuppressWarnings("unchecked")
		List<Long> longIds = (List<Long>)aux2;
		
		Stream<String> stream = Arrays.stream(communityIdsStr.split(" "));
		ids = stream.map(it -> {
			Integer aux = Integer.valueOf(it);
			return aux;
		}).toList();
		
		ids = longIds.stream()
				.map(l -> l.intValue()).toList();
    	return ids;
	}
	protected boolean belongsCommunity(List<Integer> myCommunities, Integer communityId) {
		return myCommunities.contains(communityId);
	}
}