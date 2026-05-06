package org.fmm.communitymgmt.common.repository.calendar;

import java.util.List;

import org.fmm.communitymgmt.common.model.calendar.AbstractPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface AbstractPeriodRepository extends JpaRepository<AbstractPeriod, Integer> {
    @Query("SELECT ap FROM AbstractPeriod ap"
    		+ " INNER JOIN FETCH ap.year ly"
    		+ " WHERE ly.year = :year")
    List<AbstractPeriod> findPeriodByYear(@Param("year")Integer year);
}
