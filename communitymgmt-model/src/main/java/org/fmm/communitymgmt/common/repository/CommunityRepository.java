package org.fmm.communitymgmt.common.repository;

import java.util.List;

import org.fmm.communitymgmt.common.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {
	@Query("SELECT c FROM Community c"
			+ " WHERE c.id IN :ids")
	List<Community> listCommunitiesByIds(@Param("ids") List<Integer> idList);
	
}
