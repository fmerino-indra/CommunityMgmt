package org.fmm.communitymgmt.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource("file:./secrets/application-secrets.properties")
@EntityScan({
    "org.fmm.communitymgmt.common"
//	,"org.fmm.oauth2.common.model"
})
@EnableJpaRepositories({
    "org.fmm.communitymgmt.common.repository"
//	,"org.fmm.oauth2.common.model.repository"
})
public class CommunityMgmtWebConfig {

}
