package org.fmm.communitymgmt.common.repository;

import org.fmm.communitymgmt.common.model.CommunitySettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface CommunitySettingsRepository extends JpaRepository<CommunitySettings, Integer> {
	/*
	@Query("SELECT c FROM CommunitySettings cs"
			+ " JOIN FETCH cs.community c "
			+ " WHERE c.id = :communityId")
	Optional<CommunitySettings> findCommunitySettingsById(@Param("communityId") Integer id);
*/	
}
