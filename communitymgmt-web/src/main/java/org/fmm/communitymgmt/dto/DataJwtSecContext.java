package org.fmm.communitymgmt.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataJwtSecContext {
/*
				.claim("communityId", selectedCommunity.getId())
				.claim("allCommunities", allCommunities)
				.claim("communityIds", idList)
				.claim("communityNumber", selectedCommunity.getCommunityNumber())
				.claim("person", person.getId())
				.claim("name", ((Person)person).getName())

 */
	private List<Integer> myCommunitiesIds = null;
	private Map<Integer, List<Integer>> myCharges = null;
	private Integer personId = null;
	private String name = null;
	
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static String toJson(DataJwtSecContext secContext) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(secContext);
		
	}
	public List<Integer> getMyCommunitiesIds() {
		return myCommunitiesIds;
	}
	public void setMyCommunitiesIds(List<Integer> myCommunitiesIds) {
		this.myCommunitiesIds = myCommunitiesIds;
	}
	public Map<Integer, List<Integer>> getMyCharges() {
		return myCharges;
	}
	public void setMyCharges(Map<Integer, List<Integer>> myCharges) {
		this.myCharges = myCharges;
	}
}
