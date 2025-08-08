package org.fmm.communitymgmt.common.repository;

import java.util.Optional;

import org.fmm.communitymgmt.common.model.RMarriage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface MarriageRepository extends JpaRepository<RMarriage, Integer> {
    @Query("SELECT rm FROM RMarriage rm"
    		+ " WHERE rm.husband.id = :personId"
    		+ " OR rm.wife.id = :personId")
    Optional<RMarriage> findRMarriageByPersonId(@Param("personId")Integer personId);

}
