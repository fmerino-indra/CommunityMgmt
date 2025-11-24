package org.fmm.communitymgmt.common.repository.templates;

import java.util.List;

import org.fmm.communitymgmt.common.model.templates.CelebrationEventTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface CelebrationEventTemplateRepository extends JpaRepository<CelebrationEventTemplate, Integer> {
	@Query("SELECT cet FROM CelebrationEventTemplate cet"
			+ " WHERE cet.cct.id = :cycleId")
	List<CelebrationEventTemplate> listEventTemplatesByCycleId(@Param("cycleId") Integer cycleId);

}
