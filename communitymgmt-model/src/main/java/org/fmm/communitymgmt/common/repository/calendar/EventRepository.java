package org.fmm.communitymgmt.common.repository.calendar;

import java.util.List;

import org.fmm.communitymgmt.common.model.calendar.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("SELECT ev FROM Event ev"
    		+ " WHERE ev.community.id = :communityId"
    		+ " and ev.year = :year "
    		+ " and ev.month = :month")
    List<Event> findAllEventByYearAndMonth(@Param("communityId") Integer communityId, @Param("year") Integer year, @Param("month") Integer month);
	
}
