package org.fmm.communitymgmt.common.repository;

import org.fmm.communitymgmt.common.model.RMarriage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface MarriageRepository extends JpaRepository<RMarriage, Integer> {
}
