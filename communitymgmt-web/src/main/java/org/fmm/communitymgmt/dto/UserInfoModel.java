package org.fmm.communitymgmt.dto;

import java.util.List;

import org.fmm.communitymgmt.common.model.Membership;
import org.fmm.communitymgmt.common.model.Person;
import org.fmm.communitymgmt.contrroller.SocialUserInfo;

public class UserInfoModel {
	private SocialUserInfo socialUserInfo = null;
	private Person person = null;
	private List<Membership> allMembership = null; 
	
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
	public List<Membership> getAllMembership() {
		return allMembership;
	}
	public void setAllMembership(List<Membership> allMembership) {
		this.allMembership = allMembership;
	}
	
}
