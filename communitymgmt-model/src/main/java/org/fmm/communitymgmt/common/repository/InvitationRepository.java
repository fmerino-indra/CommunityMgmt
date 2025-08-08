package org.fmm.communitymgmt.common.repository;

import java.util.List;
import java.util.Optional;

import org.fmm.communitymgmt.common.model.Invitation;
import org.fmm.communitymgmt.common.util.InvitationState;
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
    		+ " ORDER BY i.state"
    		)
    List<Invitation> listAllInvitationsByCommunityId(@Param("communityId")Integer id);
    
    @Query("SELECT i FROM Invitation i "
    		+ " JOIN FETCH i.community c"
    		+ " LEFT JOIN i.person p"
    		+ " WHERE p.id = :personId"
    		+ " AND i.state = :state"
    		)
    List<Invitation> listAllInvitationsByPersonId(@Param("personId")Integer personId, @Param("state") InvitationState state);
    
    @Query("SELECT i FROM Invitation i "
    		+ " INNER JOIN FETCH i.community c"
    		+ " INNER JOIN FETCH i.invitationType t"
    		+ " LEFT JOIN i.person p"
    		+ " WHERE i.id = :id")
    Optional<Invitation> findFullInvitationById(@Param("id")Integer id);

}
