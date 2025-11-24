package org.fmm.communitymgmt.service.planning;

import java.time.LocalDate;

import org.fmm.communitymgmt.common.model.Community;

public interface CommunityPlanningService {
    void preparePlanning(LocalDate fromLDT, LocalDate toLDT, Community community);
    void planning(LocalDate fromLDT, LocalDate toLDT, Community community);

}