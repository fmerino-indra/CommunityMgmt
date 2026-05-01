package org.fmm.communitymgmt.common.repository.calendar;

import java.util.List;

import org.fmm.communitymgmt.common.model.calendar.LiturgicalFeast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface LiturgicalFeastRepository extends JpaRepository<LiturgicalFeast, Integer> {
    @Query("SELECT lf FROM LiturgicalFeast lf"
    		+ " INNER JOIN FETCH lf.year ly"
    		+ " WHERE ly.year = :year")
    List<LiturgicalFeast> findLiturgicalFeastsByYear(@Param("year")Integer year);
}
