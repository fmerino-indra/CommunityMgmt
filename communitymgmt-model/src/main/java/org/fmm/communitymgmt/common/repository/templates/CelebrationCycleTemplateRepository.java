package org.fmm.communitymgmt.common.repository.templates;

import org.fmm.communitymgmt.common.model.templates.CelebrationCycleTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface CelebrationCycleTemplateRepository extends JpaRepository<CelebrationCycleTemplate, Integer> {
}
