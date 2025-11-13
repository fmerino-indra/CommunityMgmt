package org.fmm.communitymgmt.common.testconfig;

import jakarta.annotation.PostConstruct;
/*
@Configuration
@EntityScan(basePackages = {"org.fmm.communitymgmt.common.model","org.fmm.oauth2.common.model.model"})
@EnableJpaRepositories(basePackages = {"org.fmm.communitymgmt.common.repository","org.fmm.oauth2.common.model.repository"})
@PropertySource(value="classpath:lib-test-application.yaml", factory = YamlPropertySourceFactory.class)
*/
public class CommunityMgmtJpaTestConfiguration {
	@PostConstruct
	public void init() {
		System.out.println("TestPropertiesConfig ha sido cargado por Spring");
	}
}
