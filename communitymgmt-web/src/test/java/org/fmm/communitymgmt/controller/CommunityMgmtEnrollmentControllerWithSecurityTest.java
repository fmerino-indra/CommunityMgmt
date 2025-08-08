package org.fmm.communitymgmt.controller;

import static org.mockito.ArgumentMatchers.anyInt;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.fmm.communitymgmt.common.model.Invitation;
import org.fmm.communitymgmt.controller.CommunityMgmtEnrollmentController;
import org.fmm.communitymgmt.service.CommunityService;
import org.fmm.communitymgmt.service.EnrollmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = CommunityMgmtEnrollmentController.class)
//@Import({
//	CommunityMgmtDataTokenInterceptorConfig.class,
//	ValidateCommunityBelongingInterceptor.class,
////	OidcHttpSecurityCustomizer.class,
//	CommunityMgmtSecurityConfig.class,
//	OAuth2OidcLoginSecurityConfig.class
//})
//@EnableWebSecurity
@AutoConfigureMockMvc
public class CommunityMgmtEnrollmentControllerWithSecurityTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockitoBean
	private EnrollmentService enrollmentService;
	
	@MockitoBean
	private CommunityService communityService;
	
	@MockitoBean
	private JwtDecoder jwtDecoder;
	
	private static final String VALID_DATA_JWT = "eyJraWQiOiJjbGF2ZURlUHJ1ZWJhcyIsImFsZyI6IlJTNTEyIn0.eyJzdWIiOiIxMTQ3NDk2MTI1MTk5NzY1MTg3NTgiLCJhdWQiOiJmZWxpeC5tZXJpbm8uZm90b2dyYWZvQGdtYWlsLmNvbSIsIm5iZiI6MTc0ODUzMDE0MCwiaXNzIjoiQ29tbXVuaXR5TWdtdEJhY2siLCJleHAiOjE3NDg1MzM3NDAsImlhdCI6MTc0ODUzMDE0MCwic2VjQ29udGV4dCI6IntcIm15Q29tbXVuaXRpZXNJZHNcIjpbMTQsMTVdLFwibXlDaGFyZ2VzXCI6e1wiMTRcIjpbMV0sXCIxNVwiOlsyXX0sXCJwZXJzb25JZFwiOjc2LFwibmFtZVwiOlwiRsOpbGl4XCJ9In0.e1XFls12DDXfVixP_10ZtUlPaEJSYQBp1x7-uh8MuJoj7YLXaQisU50YNR29axj6Mm002b3ni5Yb7XnqIz8J1p2lNBalSa72zfRNd84eIe9ZOLg72z8g9GoywMBoQb1X82OdPewD34uGRvQq1gHVlMVEfTgeUrHl42u26m0d3vpCN45btp0fTGEyBMuvif_3PDD3Z2E-OvSadl5K-voEc24_UhqhcNOZM4aVLXUUAEvHOdV_jWqGxmKCpI832s2HfIOR9z67YqBuMm2ax7_ocFSlJFcsXK2Tfm8YmfqmQ1eI56EoBMVclCz8ZmpeelP64t2CQADlK8TP7NeqMDTBaw";
	private static final String AUTH_JWT_TOKEN = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImUxNGMzN2Q2ZTVjNzU2ZThiNzJmZGI1MDA0YzBjYzM1NjMzNzkyNGUiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI4Mjg2MTQ0ODMyMTYtcWw0N2RkbnE3NzNtdDZnZTkyYWswZjFpZGttbHNiNmQuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI4Mjg2MTQ0ODMyMTYtNHF2aHZqZjQwdG9rb2tiN3Zqamd2MW0xNTg5aTlkczcuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTQ3NDk2MTI1MTk5NzY1MTg3NTgiLCJlbWFpbCI6ImZlbGl4Lm1lcmluby5mb3RvZ3JhZm9AZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5vbmNlIjoia290bGluLlVuaXQiLCJuYW1lIjoiRmVsaXggTWVyaW5vIE1hcnTDrW5leiBkZSBQaW5pbGxvcyIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NJY1NBN0pYSm1VWW9qR1YtMTh5NEhCNVhRVXVCMVgxdzQ5MVlXT1dfWVlGTnhMbXc9czk2LWMiLCJnaXZlbl9uYW1lIjoiRmVsaXgiLCJmYW1pbHlfbmFtZSI6Ik1lcmlubyBNYXJ0w61uZXogZGUgUGluaWxsb3MiLCJpYXQiOjE3NDY4NTQ2NjUsImV4cCI6MTc0Njg1ODI2NX0.TcWUf8dFL2nSRotQ_gCvAsJCHqpsrdSYQauFJBjVWBxFLesm_lIcOfGQ6uVQGfn99KV6VhmfKoq31wvWvTYsJnXhPhtFjZkDc6oMRBrrQsFdmRxDM8P3Z9aOxsv8oQnbDq72HzcPfudWAuziZ68sZDRU_hCbF-Y9rS0OqVn6uOPSGpJE7hGChuxqhdmBlORHCdI8TbknOiqLduihCuEKqr4FIZxIx948n3csLzXoZ1H_lrOSh0jjh6CyMl1M2aI3VdpE-qoiGn7aIFyQrg9g5tgHijat_G6pEs-aXNwntqNU128Xfi1ozXxoW1CZgQpRqKoD5iayvM2huGCGv13URA";
	
	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		
		Mockito.when(enrollmentService.getInvitationsByCommunity(anyInt())).thenReturn(new ArrayList<Invitation>());
		
		Map<String, Object> authClaims = new HashMap<>();
		authClaims.put("sub", "114749612519976518758");
		authClaims.put("email", "felix.merino.fotografo@gmail.com");
		authClaims.put("email_verified", true);
		authClaims.put("name", "Felix Merino Martínez de Pinillos");
		authClaims.put("iat", 1748526296);
		authClaims.put("nbf", 1748526296);
		authClaims.put("exp", 1748550962);
		
		Jwt mockJwt = new Jwt(AUTH_JWT_TOKEN, Instant.now(), Instant.now().plusSeconds(3600),
				Map.of("alg", "HS256", "kid", "e14c37d6e5c756e8b72fdb5004c0cc356337924e"), authClaims
		);
		
		Mockito.when(jwtDecoder.decode(AUTH_JWT_TOKEN)).thenReturn(mockJwt);
	}
	
	@Test
	void whenValidatedEndpointCalledWithCustomJwtClaimsAndParam_thenOk() throws Exception {
		Integer communityId = 14;
		Mockito.when(enrollmentService.getInvitationsByCommunity(communityId))
		.thenReturn(Collections.emptyList());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/enrollment/{communityId}/invitations", communityId)
				.with(SecurityMockMvcRequestPostProcessors.jwt().jwt(jwtBuilder -> jwtBuilder
						.claim("sub", "114749612519976518758")
						.claim("email", "felix.merino.fotografo@gmail.com")
				))
//				.header("Authorization", "Bearer " +AUTH_JWT_TOKEN)
				.header("x-data-jwt", VALID_DATA_JWT))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().json("[]"));
		//MockMvcResultMatchers		.
	}
	
//	@Test
	void whenListInvitationsWithoutAuthToken_thenUnauthorized() throws Exception {
		Integer communityId = 14;
		mockMvc.perform(MockMvcRequestBuilders.get("/api/enrollment/{communityId}/invitations", communityId)
				.header("x-data-jwt", VALID_DATA_JWT))
		.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
}
