package org.fmm.communitymgmt.common.testconfig;

import org.fmm.communitymgmt.common.config.YamlPropertySourceFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.annotation.PostConstruct;

@Configuration
//@EnableAutoConfiguration
@EntityScan(basePackages = {"org.fmm.communitymgmt.common.model"})
@EnableJpaRepositories(basePackages = {"org.fmm.communitymgmt.common.repository"})
@PropertySource(value="classpath:lib-test-application.yaml", factory = YamlPropertySourceFactory.class)
public class CommunityMgmtCommonTestConfiguration {
	@PostConstruct
	public void init() {
		System.out.println("TestPropertiesConfig ha sido cargado por Spring");
	}
}
