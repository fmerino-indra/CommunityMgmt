package org.fmm.communitymgmt.controller;

import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;

import org.fmm.communitymgmt.common.model.Invitation;
import org.fmm.communitymgmt.config.CommunityMgmtDataTokenInterceptorConfig;
import org.fmm.communitymgmt.controller.CommunityMgmtEnrollmentController;
import org.fmm.communitymgmt.controller.interceptors.ValidateCommunityBelongingInterceptor;
import org.fmm.communitymgmt.service.CommunityService;
import org.fmm.communitymgmt.service.EnrollmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = CommunityMgmtEnrollmentController.class,
excludeAutoConfiguration = {SecurityAutoConfiguration.class,
		OAuth2ClientAutoConfiguration.class, 
		OAuth2ResourceServerAutoConfiguration.class
//		,
//		org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2WebSecurityConfiguration.class
		})
@Import({
	CommunityMgmtDataTokenInterceptorConfig.class,
	ValidateCommunityBelongingInterceptor.class
})
public class CommunityMgmtEnrollmentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockitoBean
	private EnrollmentService enrollmentService;
	
	@MockitoBean
	private CommunityService communityService;
	
	private static final String VALID_DATA_JWT = "eyJraWQiOiJjbGF2ZURlUHJ1ZWJhcyIsImFsZyI6IlJTNTEyIn0.eyJzdWIiOiIxMTQ3NDk2MTI1MTk5NzY1MTg3NTgiLCJhdWQiOiJmZWxpeC5tZXJpbm8uZm90b2dyYWZvQGdtYWlsLmNvbSIsIm5iZiI6MTc0NzU1NjI1NSwiaXNzIjoiQ29tbXVuaXR5TWdtdEJhY2siLCJleHAiOjE3NDc1NTk4NTUsImlhdCI6MTc0NzU1NjI1NSwic2VjQ29udGV4dCI6IntcImFsbENvbW11bml0aWVzSWRzXCI6WzE0LDE1XSxcInBlcnNvbklkXCI6NzYsXCJuYW1lXCI6XCJGw6lsaXhcIn0ifQ.ci4FKU7ybD1QhXyt5L2TlrUJv9U1DwNpXPtzVmy0b49LeErNMtI2BjAJ9omIpblae04-Bu5W4DjdyJ8lUsF1lcmfJJs81pKhRPJZPKuFsQSPxu5aNR8oUpnFWfwjc5JZQuLNByek3NUmrAGnSnvvbHU5WV0mElXsSCDVCQ8NkwqjtIAeRnsoc6NHEcCv7VPXRdiJkr8Qc1hZhM-0C5ywxB78CSiTgzzb4-oj2JJvihkB3h1Cq52R3m83xHKgSIVCwXFJAXyvEcJQG1cHEzsI-_By6usZyegnxKhVv2Is3Z5myj28_vaiZUOf7qDIAwUQ9wJOrbJ_z6J0jcXHMGV7Ww";
	
	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
//				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		
		Mockito.when(enrollmentService.getInvitationsByCommunity(anyInt())).thenReturn(new ArrayList<Invitation>());
	}
	
	@Test
	void whenValidatedEndpointCalledWithCustomJwtClaimsAndParam_thenOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/enrollment/{communityId}/invitations", 14)
				.header("x-data-jwt", VALID_DATA_JWT))
		.andExpect(MockMvcResultMatchers.status().isOk());
		//MockMvcResultMatchers		.
	}
}
