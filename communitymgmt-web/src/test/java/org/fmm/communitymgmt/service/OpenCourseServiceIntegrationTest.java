package org.fmm.communitymgmt.service;

import org.fmm.communitymgmt.common.config.YamlPropertySourceFactory;
import org.fmm.communitymgmt.service.planning.OpenCourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest 
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@PropertySource(value="classpath:application-test.yaml", factory = YamlPropertySourceFactory.class)
public class OpenCourseServiceIntegrationTest {

	@Autowired
	private OpenCourseService openCourseService;
	
//	@BeforeAll
//	public void initAll() {
//	}
	
	@Test
	public void testOpenCourse() {
		openCourseService.openCourse(2025);
	}
}
