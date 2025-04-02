package org.fmm.communitymgmt.common;

import org.fmm.communitymgmt.common.config.YamlPropertySourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
//@EntityScan(basePackages = {"org.fmm.communitymgmt.common.model"})
//@EnableJpaRepositories(basePackages = {"org.fmm.communitymgmt.common.repository"})
//@PropertySource(value="classpath:lib-test-application.yaml", factory = YamlPropertySourceFactory.class)
public class CommunityMgmtCommonTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityMgmtCommonTestApplication.class, args);
	}

}
