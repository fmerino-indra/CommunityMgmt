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
@Deprecated
public interface RelationshipRepository extends JpaRepository<Relationship, Integer> {
    // Obtener todas las instancias de Relationship (que incluye todas las subclases)
//    List<Relationship> findBy();

    @Query("SELECT rm FROM RMarriage rm"
    		+ " LEFT JOIN FETCH rm.husband h"
    		+ " LEFT JOIN FETCH rm.wife w ")
    List<RMarriage> findAllRelationshipsWithMarriages();

    @Query("SELECT rs FROM RSingle rs"
    		+ " LEFT JOIN FETCH rs.person p")
    List<RSingle> findAllRelationshipsWithSingles();

    
    // Obtener solo instancias de RMarriage
//    List<RMarriage> findByMarriageDetailsNotNull();

    // Obtener solo instancias de RSingle
//    List<RSingle> findBySingleDetailsNotNull();
    
    // También puedes usar @Query si necesitas una consulta personalizada
    @Query("SELECT r FROM Relationship r WHERE TYPE(r) = :subclass")
    List<Relationship> findAllBySubclass(@Param("subclass") Class<?> subclass);
    
/* Esta cuando eran 2 relaciones 1:n    
    @Query("SELECT r FROM Relationship r"
    		+ " LEFT JOIN RMarriage rm on TYPE(r) = RMarriage"
    		+ " LEFT JOIN rm.husband h"
    		+ " LEFT JOIN rm.wife w"
    		+ " LEFT JOIN RSingle rs ON TYPE(r) = RSingle"
    		+ " LEFT JOIN rs.person s"
    		+ " LEFT JOIN ROther ro ON type(r) = ROther"
    		+ " LEFT JOIN ROthersPerson rop ON TYPE(r) = ROther"
    		+ " LEFT JOIN rop.person o"
    		+ " ORDER BY r.orderList, r.relationshipName")
    List<Relationship> listCommunityOrderByDefault();
*/
    @Deprecated
    @Query("SELECT r FROM Relationship r"
    		+ " LEFT JOIN RMarriage rm on TYPE(r) = RMarriage"
    		+ " LEFT JOIN rm.husband h"
    		+ " LEFT JOIN rm.wife w"
    		+ " LEFT JOIN RSingle rs ON TYPE(r) = RSingle"
    		+ " LEFT JOIN rs.person s"
    		+ " LEFT JOIN ROther ro ON type(r) = ROther"
      		+ " LEFT JOIN ro.relatedPersons o"
    		+ " ORDER BY r.orderList, r.relationshipName")
    List<Relationship> listCommunityTiny(@Param("communityId")Integer communityId);
    
    @Deprecated
    @Query("SELECT r FROM Relationship r"
    		+ " LEFT JOIN RMarriage rm on TYPE(r) = RMarriage"
    		+ " LEFT JOIN rm.husband h"
    		+ " LEFT JOIN rm.wife w"
    		+ " LEFT JOIN RSingle rs ON TYPE(r) = RSingle"
    		+ " LEFT JOIN rs.person s"
    		+ " LEFT JOIN ROther ro ON type(r) = ROther"
      		+ " LEFT JOIN ro.relatedPersons o"
    		+ " ORDER BY r.orderList, r.relationshipName")
    List<Relationship> listCommunityTinyOld(@Param("communityId")Integer communityId);
    
}
