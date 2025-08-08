package org.fmm.communitymgmt.common.repository;

import java.util.List;

import org.fmm.communitymgmt.common.model.RMarriage;
import org.fmm.communitymgmt.common.model.RSingle;
import org.fmm.communitymgmt.common.model.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Integer> {

    @Query("SELECT rm FROM RMarriage rm"
    		+ " LEFT JOIN FETCH rm.husband h"
    		+ " LEFT JOIN FETCH rm.wife w ")
    List<RMarriage> findAllRelationshipsWithMarriages();

    @Query("SELECT rs FROM RSingle rs"
    		+ " LEFT JOIN FETCH rs.person p")
    List<RSingle> findAllRelationshipsWithSingles();

    
    // También puedes usar @Query si necesitas una consulta personalizada
    @Query("SELECT r FROM Relationship r WHERE TYPE(r) = :subclass")
    List<Relationship> findAllBySubclass(@Param("subclass") Class<?> subclass);
    
    @Query("SELECT r FROM Relationship r"
    		+ " LEFT JOIN RMarriage rm on TYPE(r) = RMarriage"
    		+ " LEFT JOIN rm.husband h"
    		+ " LEFT JOIN rm.wife w"
    		+ " LEFT JOIN RSingle rs ON TYPE(r) = RSingle"
    		+ " LEFT JOIN rs.person s"
    		+ " LEFT JOIN ROther ro ON type(r) = ROther"
      		+ " LEFT JOIN ro.relatedPersons o"
      		+ " WHERE r.id IN ("
      		+ "   SELECT m.relationship.id"
      		+ "   FROM Membership m"
      		+ "   INNER JOIN m.community c"
      		+ "   WHERE c.id = :communityId"
      		+ " )"
    		+ " ORDER BY r.orderValue, r.relationshipName")
    List<Relationship> listCommunityTiny(@Param("communityId")Integer communityId);
    
}
