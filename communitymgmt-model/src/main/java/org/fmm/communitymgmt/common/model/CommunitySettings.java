package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import java.time.LocalTime;

import org.fmm.communitymgmt.common.model.common.MStepOfCatechumenateStage;
import org.fmm.communitymgmt.common.model.templates.CelebrationCycleTemplate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;


/**
 * The persistent class for the community database table.
 * 
 */
@Entity
public class CommunitySettings implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="community_id")
	private Integer communityId;

//	@Id
//	@Column(name="community_id") -> Da error diciendo que se use @PrimaryKeyJoinColumn
	@OneToOne
	@MapsId
	@JoinColumn(name = "community_id")
	private Community community;
	
	// Number of brothers of the community
	private Integer brothers;
	
	// Preferred size of the groups 
	private Integer groupSize;
	
	// Day of the word (1-7 or 0-6)
	private Integer wordDay;
	
	// Number of week (sunday) of the monthly Convivence (
	private Integer convivenceWeek; // 1-4
	private LocalTime convivenceTime;
	private LocalTime eucharistTime;
	private LocalTime wordTime;
	
	@ManyToOne
	private MStepOfCatechumenateStage step;
	
	@ManyToOne
	private CelebrationCycleTemplate cycle;
	
	public CommunitySettings() {
	}
	public Integer getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
	public Integer getBrothers() {
		return brothers;
	}

	public void setBrothers(Integer brothers) {
		this.brothers = brothers;
	}

	public Integer getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(Integer groupSize) {
		this.groupSize = groupSize;
	}

	public Integer getWordDay() {
		return wordDay;
	}

	public void setWordDay(Integer wordDay) {
		this.wordDay = wordDay;
	}

	public LocalTime getEucharistTime() {
		return eucharistTime;
	}

	public void setEucharistTime(LocalTime eucharistTime) {
		this.eucharistTime = eucharistTime;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}
	public Integer getConvivenceWeek() {
		return convivenceWeek;
	}
	public void setConvivenceWeek(Integer convivenceWeek) {
		this.convivenceWeek = convivenceWeek;
	}
	public LocalTime getConvivenceTime() {
		return convivenceTime;
	}
	public void setConvivenceTime(LocalTime convivenceTime) {
		this.convivenceTime = convivenceTime;
	}
	public LocalTime getWordTime() {
		return wordTime;
	}
	public void setWordTime(LocalTime wordTime) {
		this.wordTime = wordTime;
	}
	public MStepOfCatechumenateStage getStep() {
		return step;
	}
	public void setStep(MStepOfCatechumenateStage step) {
		this.step = step;
	}
	public CelebrationCycleTemplate getCycle() {
		return cycle;
	}
	public void setCycle(CelebrationCycleTemplate cycle) {
		this.cycle = cycle;
	}
}