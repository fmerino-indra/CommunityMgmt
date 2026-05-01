package org.fmm.communitymgmt.service.planning;

import java.time.LocalDate;
import java.util.List;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.calendar.Event;

public interface CommunityPlanningService {
    void preparePlanning(LocalDate fromLDT, LocalDate toLDT, Community community);
    void planning(LocalDate fromLDT, LocalDate toLDT, Community community);
	List<Event> getPlanning(Integer communityId, Integer year, Integer month);

}