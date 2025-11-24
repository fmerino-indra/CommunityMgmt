package org.fmm.communitymgmt.common.repository.common;

import org.fmm.communitymgmt.common.model.common.TTripod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface TTripodRepository extends JpaRepository<TTripod, Integer> {
}
