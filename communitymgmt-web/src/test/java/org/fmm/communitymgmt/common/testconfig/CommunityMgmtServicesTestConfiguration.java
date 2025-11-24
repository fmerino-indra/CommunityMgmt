package org.fmm.communitymgmt.common.testconfig;

import jakarta.annotation.PostConstruct;

//@TestConfiguration
/*
@Configuration

@EntityScan(basePackages = {"org.fmm.communitymgmt.common.model","org.fmm.oauth2.common.model.model"})
@EnableJpaRepositories(basePackages = {"org.fmm.communitymgmt.common.repository","org.fmm.oauth2.common.model.repository"})

@ComponentScan(basePackages= {"org.fmm.communitymgmt.service"})
@PropertySource(value="classpath:lib-test-application.yaml", factory = YamlPropertySourceFactory.class)
*/
public class CommunityMgmtServicesTestConfiguration {
	@PostConstruct
	public void init() {
		System.out.println("TestPropertiesConfig ha sido cargado por Spring");
	}
}
