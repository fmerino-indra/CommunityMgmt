package org.fmm.communitymgmt.service;

import java.util.List;
import java.util.Optional;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.Membership;
import org.fmm.communitymgmt.common.model.Person;
import org.fmm.communitymgmt.common.model.TCharge;

public interface PersonInChargeService {
	public Community createCommunity(String communityNumber, String parish, String address, String addressNumber, String postalCode, String city, String country, Boolean activated);
	public Community createCommunity(Community community);
    public Optional<Community> getCommunity(Integer idCommunity);
	Membership addMember(Community community, Person person, TCharge charge);
	Membership addMember(Community community, Person person, List<TCharge> chargeList);
}