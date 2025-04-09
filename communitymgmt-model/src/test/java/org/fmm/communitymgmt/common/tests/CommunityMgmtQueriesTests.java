package org.fmm.communitymgmt.common.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.fmm.communitymgmt.common.model.Relationship;
import org.fmm.communitymgmt.common.repository.EmailAccountRepository;
import org.fmm.communitymgmt.common.repository.MarriageRepository;
import org.fmm.communitymgmt.common.repository.MobileNumberRepository;
import org.fmm.communitymgmt.common.repository.OthersRepository;
import org.fmm.communitymgmt.common.repository.PersonRepository;
import org.fmm.communitymgmt.common.repository.RelationshipRepository;
import org.fmm.communitymgmt.common.repository.SingleRepository;
import org.fmm.communitymgmt.common.testconfig.CommunityMgmtCommonTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Esto configura springboot completo para tests integrados.
// Para JPA es mejor @DataJPATest
//@SpringBootTest(classes = {CommunityMgmtCommonTestApplication.class})
//@EntityScan(basePackages = {"org.fmm.communitymgmt.common.model","org.fmm.oauth2.common.model.model"})
//@EnableJpaRepositories(basePackages = {"org.fmm.communitymgmt.common.repository","org.fmm.oauth2.common.model.repository"})

@Rollback(false)
@DataJpaTest
// No reemplaces la base de datos real por una embebida.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Import(CommunityMgmtCommonTestConfiguration.class)
@ContextConfiguration(classes = CommunityMgmtCommonTestConfiguration.class)
class CommunityMgmtQueriesTests {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MobileNumberRepository mobileNumberRepository;

    @Autowired
    EmailAccountRepository emailAccountRepository;
    
    @Autowired
    MarriageRepository marriageRepository;

    @Autowired
    SingleRepository singleRepository;
    
    @Autowired
    OthersRepository othersRepository;
    
    @Autowired
    RelationshipRepository relationshipRepository;
    
    @Autowired
    private Environment environment;
    
//	@Test
	void testConfig() {
		assertNotNull(environment.getProperty("server.port"), "Las propiedades no se han cargado");
	}
	
	@Test
	void testListRelationship() {
		List<? extends Relationship> comunidad = null;
		comunidad = relationshipRepository.listCommunityOrderByDefault();
		Assert.notEmpty(comunidad, "La lista no puede estar vacía");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String listJson = null;
		try {
			listJson = objectMapper.writeValueAsString(comunidad);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.notNull(listJson, "Lista vacía");
		System.out.println();
		System.out.println(listJson);
		System.out.println();
	}
}