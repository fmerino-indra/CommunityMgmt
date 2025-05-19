package org.fmm.communitymgmt.service;

import java.util.Optional;

import org.fmm.communitymgmt.common.model.Community;

public interface PersonInChargeService {
	public Community createCommunity(String communityNumber, String parish, String address, String addressNumber, String postalCode, String city, String country, Boolean activated);
    public Optional<Community> getCommunity(Integer idCommunity);
}