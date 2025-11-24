package org.fmm.communitymgmt.service;

import java.time.LocalDate;

import org.fmm.communitymgmt.common.config.YamlPropertySourceFactory;
import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.CommunitySettings;
import org.fmm.communitymgmt.common.repository.CommunityRepository;
import org.fmm.communitymgmt.common.repository.CommunitySettingsRepository;
import org.fmm.communitymgmt.service.planning.CommunityPlanningService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest 
/*
(
		webEnvironment = SpringBootTest.WebEnvironment.NONE,
		classes = { CommunityMgmtServicesTestConfiguration.class}
)
*/
// Por defecto, por cada método @Test se crea una instancia de la clase.
// Esto lo cambia para compartir estado entre tests
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
//@Rollback(false)
//@DataJpaTest

// Funciona, pero es que quiero que sea otra bbdd
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@PropertySource(value="classpath:lib-test-application.yaml", factory = YamlPropertySourceFactory.class)
@PropertySource(value="classpath:application-test.yaml", factory = YamlPropertySourceFactory.class)
public class CommunityServiceIntegrationTest {

	@Autowired
	private CommunityPlanningService planningService;
	
	@Autowired
	private CommunityRepository communityRepository;
	
	@Autowired
	private CommunitySettingsRepository communitySettingsRepository;
	
	private CommunitySettings settings = null;
	private Community community = null;
	
	@BeforeAll
	public void initAll() {
		community = communityRepository.findByIdFMM(1);
		settings = communitySettingsRepository.findById(1).get();
	}
	
	@Test
	public void testPlanning() {
		LocalDate from = null;
		LocalDate until = null;
		
		from = LocalDate.of(2025, 1, 1);
		until = LocalDate.of(2025, 12, 31);
		planningService.planning(from, until, community);
	}
}
