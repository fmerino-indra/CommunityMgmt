package org.fmm.communitymgmt.common.repository;

import org.fmm.communitymgmt.common.model.EmailAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface EmailAccountRepository extends JpaRepository<EmailAccount, Integer> {
}
