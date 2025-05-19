package org.fmm.communitymgmt.common.repository;

import java.util.List;
import java.util.Optional;

import org.fmm.communitymgmt.common.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {
	/**
	 * Communities of a Person
	 * @param id
	 * @return
	 */
    @Query("SELECT m FROM Membership m "
    		+ " INNER JOIN FETCH m.community c"
    		+ " INNER JOIN FETCH m.membershipType t"
    		+ " INNER JOIN FETCH m.person p"
    		+ " LEFT JOIN FETCH m.charges"
    		+ " WHERE p.id = :id"
    		)
    List<Membership> findMembershipWithChargesByPersonId(@Param("id")Integer id);

    /**
     * Community of a Person of a type, should there be only one
     * @TODO TBC if there should be only one other type 
     * @param id
     * @param type
     * @return
     */
    @Query("SELECT m FROM Membership m "
    		+ " INNER JOIN FETCH m.community c"
    		+ " INNER JOIN FETCH m.membershipType t"
    		+ " INNER JOIN FETCH m.person p"
    		+ " LEFT JOIN FETCH m.charges"
    		+ " WHERE p.id = :id "
    		+ " AND t.id = :type"
    		)
    Optional<Membership> findMembershipWithChargesByPersonIdAndTMembership(@Param("id")Integer id, @Param("type")Integer type);
    
	/**
	 * Members of a Community
	 * @param id
	 * @return
	 */
    @Query("SELECT ms FROM Membership ms "
    		+ " INNER JOIN FETCH ms.community c"
    		+ " INNER JOIN FETCH ms.membershipType t"
    		+ " INNER JOIN FETCH ms.relationship r"
    		+ " LEFT JOIN RMarriage rm on TYPE(r) = RMarriage"
    		+ " LEFT JOIN rm.husband h"
    		+ " LEFT JOIN rm.wife w"
    		+ " LEFT JOIN RSingle rs ON TYPE(r) = RSingle"
    		+ " LEFT JOIN rs.person s"
    		+ " LEFT JOIN ROther ro ON type(r) = ROther"
      		+ " LEFT JOIN ro.relatedPersons o"
    		+ " WHERE c.id = :communityId"
      		+ " ORDER BY r.orderList, r.relationshipName")
    List<Membership> findMembershipByCommunityId(@Param("communityId")Integer id);
    
}
