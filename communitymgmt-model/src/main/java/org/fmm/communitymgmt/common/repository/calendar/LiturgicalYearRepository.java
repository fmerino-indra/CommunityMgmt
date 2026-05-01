package org.fmm.communitymgmt.common.repository.calendar;

import java.util.Optional;

import org.fmm.communitymgmt.common.model.calendar.LiturgicalYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface LiturgicalYearRepository extends JpaRepository<LiturgicalYear, Integer> {
    @Query("SELECT ly FROM LiturgicalYear ly"
    		+ " WHERE ly.year = :year")
    Optional<LiturgicalYear> findLiturgicalYearByYear(@Param("year")Integer year);
}
