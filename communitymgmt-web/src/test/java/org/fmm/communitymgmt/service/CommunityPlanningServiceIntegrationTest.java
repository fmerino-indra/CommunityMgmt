package org.fmm.communitymgmt.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.fmm.communitymgmt.common.config.YamlPropertySourceFactory;
import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.CommunitySettings;
import org.fmm.communitymgmt.common.model.calendar.Event;
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
@PropertySource(value="classpath:application-test.yaml", factory = YamlPropertySourceFactory.class)
public class CommunityPlanningServiceIntegrationTest {

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
		List<Event> eventList = null;
		
		settings.getBrothers();
		
		eventList = planningService.getPlanning(community.getId(), 2025, 02);
		assertNotNull(eventList);
		assertTrue(eventList.size()>0);
	}
}
