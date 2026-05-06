package org.fmm.communitymgmt.service.planning;

import org.fmm.communitymgmt.common.model.calendar.LiturgicalYear;

public interface OpenCourseService {
    LiturgicalYear openCourse(int year);
//    List<LiturgicalPeriod> createPeriods(int year);
}