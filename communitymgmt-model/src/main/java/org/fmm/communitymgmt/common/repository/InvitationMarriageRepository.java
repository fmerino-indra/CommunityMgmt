package org.fmm.communitymgmt.common.repository;

import org.fmm.communitymgmt.common.model.InvitationRMarriage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface InvitationMarriageRepository extends JpaRepository<InvitationRMarriage, Integer> {
}
