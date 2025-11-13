package org.fmm.communitymgmt.dto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.Invitation;
import org.fmm.communitymgmt.common.model.Membership;
import org.fmm.communitymgmt.common.model.RMarriage;
import org.fmm.communitymgmt.common.model.RSingle;
import org.fmm.communitymgmt.common.model.Relationship;
import org.fmm.communitymgmt.common.repository.CommunityRepository;
import org.fmm.communitymgmt.common.repository.EmailAccountRepository;
import org.fmm.communitymgmt.common.repository.InvitationRepository;
import org.fmm.communitymgmt.common.repository.MarriageRepository;
import org.fmm.communitymgmt.common.repository.MembershipRepository;
import org.fmm.communitymgmt.common.repository.MobileNumberRepository;
import org.fmm.communitymgmt.common.repository.OthersRepository;
import org.fmm.communitymgmt.common.repository.PersonRepository;
import org.fmm.communitymgmt.common.repository.RelationshipRepository;
import org.fmm.communitymgmt.common.repository.SingleRepository;
import org.fmm.communitymgmt.common.testconfig.CommunityMgmtJpaTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
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
@ContextConfiguration(classes = CommunityMgmtJpaTestConfiguration.class)
class CommunityMgmtDtosTests {

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

    @Autowired
    CommunityRepository communityRepository;
    
    @Autowired
    MembershipRepository membershipRepository;

//    @Autowired
//    MembershipRelationshipRepository membershipRelationshipRepository;
    
    @Autowired
    InvitationRepository invitationRepository;
    
//    @Test
    void testCommunityMembership() {
    	List<Membership> members = null;
		members = membershipRepository.findMembershipWithChargesByPersonId(76);
    	assertNotNull(members, "Sin comunidad");
		assertFalse(members.isEmpty(), "Sin comunidad");
		members = membershipRepository.findMembershipWithChargesByPersonId(81);
    	assertNotNull(members, "Sin comunidad");
		assertFalse(members.isEmpty(), "Sin comunidad");
		members = membershipRepository.findMembershipWithChargesByPersonId(78);
    	assertNotNull(members, "Sin comunidad");
		assertFalse(members.isEmpty(), "Sin comunidad");
    }
//    @Test
    void testInvitations() {
    	List<Invitation> invitations = null;
    	invitations = invitationRepository.listAllInvitationsByCommunityId(14);
    	assertNotNull(invitations);
    	assertFalse(invitations.isEmpty());
    }
//	@Test
	void testConfig() {
		assertNotNull(environment.getProperty("server.port"), "Las propiedades no se han cargado");
	}
	
	
//	@Test
	void testListMembersOfACommunity() {
		List<Membership> communityList = null;
		List<Relationship> relationshipList = null;
		communityList = membershipRepository.findMembershipByCommunityId(14);
		assertFalse(communityList.isEmpty());
		
		relationshipList = communityList.stream()
				.map(e -> e.getRelationship())
				.collect(Collectors.toList());
		assertTrue(relationshipList != null && !relationshipList.isEmpty());
		writeLargeJson(relationshipList);
	}
	
//	@Test
	void testCommunityOfAPerson() {
		List<Membership> membershipPerson = null;
		List<Community> communityPerson = null;
		List<Integer> communityIds = null;
		membershipPerson = membershipRepository.findMembershipWithChargesByPersonId(76);
		
		communityIds = membershipPerson.stream()
				.map(e -> 
					e.getCommunity().getId())
				.collect(Collectors.toList());
		communityPerson = communityRepository.listCommunitiesByIds(communityIds);
		assertFalse(communityPerson.isEmpty());
	}
	
//	@Test
	void testListRelationship() {
		List<? extends Relationship> comunidad = null;
		comunidad = relationshipRepository.listCommunityTiny(14);
		Assert.notEmpty(comunidad, "La lista no puede estar vacía");
		writeLargeJson(comunidad);
	}

	@Test
	void testFindRMarriageRSingle() {
		Optional<RSingle> oRSingle = null;
		oRSingle = singleRepository.findRSingleByPersonId(100);
		Optional<RMarriage> oRMarriage = null;
		oRMarriage = marriageRepository.findRMarriageByPersonId(100);
		assertNotNull(oRSingle);
		assertNotNull(oRMarriage);
	}

	@SuppressWarnings("unused")
	private void writeLargeJson(List<? extends Relationship> largeList) {
		Path path = Paths.get("queries", "comunidad-v2.json");
		File file = path.toFile();
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonFactory jFactory = objectMapper.getFactory(); 
		JsonGenerator jGenerator = null;

		try {
			jGenerator = jFactory.createGenerator(new FileOutputStream(file));
			jGenerator.writeStartObject();
			jGenerator.writeFieldName("brothers");
			jGenerator.writeStartArray();
			for (Relationship r:largeList) {
				objectMapper.writeValue(jGenerator, r);
			}
			jGenerator.writeEndArray();
			jGenerator.writeEndObject();
			jGenerator.close();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}