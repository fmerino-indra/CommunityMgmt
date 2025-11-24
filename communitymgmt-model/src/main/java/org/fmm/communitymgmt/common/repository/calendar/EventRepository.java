package org.fmm.communitymgmt.common.repository.calendar;

import org.fmm.communitymgmt.common.model.calendar.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
}
