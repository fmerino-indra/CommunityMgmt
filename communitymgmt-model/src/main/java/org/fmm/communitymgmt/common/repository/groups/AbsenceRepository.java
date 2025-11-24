package org.fmm.communitymgmt.common.repository.groups;

import java.time.LocalDate;
import java.util.List;

import org.fmm.communitymgmt.common.model.groups.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Integer> {
    @Query("SELECT DISTINCT e FROM Absence e"
            + " INNER JOIN FETCH e.person p"
            + " LEFT JOIN FETCH p.mobileNumbers mn"
            + " LEFT JOIN FETCH p.emailAccounts ea"
            + " WHERE e.day = :day"
            + " ORDER BY e.day ASC ")
    List<Absence> findAbsenceByDate(@Param("day")LocalDate day);
}
