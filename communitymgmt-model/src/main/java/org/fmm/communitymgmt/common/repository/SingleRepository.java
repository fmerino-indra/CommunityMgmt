package org.fmm.communitymgmt.common.repository;

import java.util.Optional;

import org.fmm.communitymgmt.common.model.RSingle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface SingleRepository extends JpaRepository<RSingle, Integer> {
    @Query("SELECT rs FROM RSingle rs"
    		+ " WHERE rs.person.id = :personId")
    Optional<RSingle> findRSingleByPersonId(@Param("personId")Integer personId);

}
