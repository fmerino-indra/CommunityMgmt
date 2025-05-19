package org.fmm.communitymgmt.common.repository;

import java.util.List;

import org.fmm.communitymgmt.common.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Integer> {
    @Query("SELECT i FROM Invitation i "
    		+ " JOIN FETCH i.community c"
    		+ " WHERE c.id = :communityId"
    		)
    List<Invitation> listInvitationsByCommunityId(@Param("communityId")Integer id);

}
