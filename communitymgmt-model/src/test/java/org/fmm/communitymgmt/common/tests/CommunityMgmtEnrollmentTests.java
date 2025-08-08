package org.fmm.communitymgmt.common.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.Invitation;
import org.fmm.communitymgmt.common.model.InvitationJsonViews;
import org.fmm.communitymgmt.common.model.InvitationRMarriage;
import org.fmm.communitymgmt.common.model.InvitationRSingle;
import org.fmm.communitymgmt.common.model.Person;
import org.fmm.communitymgmt.common.model.TMembership;
import org.fmm.communitymgmt.common.repository.CommunityRepository;
import org.fmm.communitymgmt.common.repository.InvitationMarriageRepository;
import org.fmm.communitymgmt.common.repository.InvitationRelationshipRepository;
import org.fmm.communitymgmt.common.repository.InvitationRepository;
import org.fmm.communitymgmt.common.repository.InvitationSingleRepository;
import org.fmm.communitymgmt.common.repository.MembershipRepository;
import org.fmm.communitymgmt.common.repository.MembershipTypeRepository;
import org.fmm.communitymgmt.common.repository.PersonRepository;
import org.fmm.communitymgmt.common.testconfig.CommunityMgmtCommonTestConfiguration;
import org.fmm.communitymgmt.common.util.InvitationState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.net.ObjectWriter;

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
class CommunityMgmtEnrollmentTests {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    InvitationRepository invitationRepository;
    
    @Autowired
    InvitationMarriageRepository invitationMarriageRepository;

    @Autowired
    InvitationSingleRepository invitationSingleRepository;
    
    @Autowired
    InvitationRelationshipRepository relationshipRepository;

    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    MembershipRepository membershipRepository;
    
    @Autowired
    MembershipTypeRepository membershipTypeRepository;
    
    @Autowired
    private Environment environment;
    
//	@Test
	void testConfig() {
		assertNotNull(environment.getProperty("server.port"), "Las propiedades no se han cargado");
	}
	@Test
	void testInvitationByPerson() {
		List<Invitation> invitationList = null;
		invitationList = invitationRepository.listAllInvitationsByPersonId(100, InvitationState.P);
		assertFalse(invitationList.isEmpty());
				
	}
//	@Test
	void testJsonComplexView() throws StreamWriteException, DatabindException, IOException {
		Invitation invitation = null;
		Optional<Invitation> oInvitation = null;
		oInvitation = invitationRepository.findFullInvitationById(20);
		assertTrue(oInvitation.isPresent());
		invitation = oInvitation.get();
		writeLargeJson(invitation);
				
	}
//	@Test
	void testaddInvitations() throws StreamWriteException, DatabindException, IOException {
		Invitation invitation = null;
		Optional<Community> oCommunity = null;
		Community community = null;
		Optional<Person> oPerson = null;
		Person person = null;
		Person husband = null;
		Person wife = null;

		Optional<TMembership> oMembershipType = null;
		TMembership membershipType = null;
		
		oMembershipType = membershipTypeRepository.findById(1);
		assertTrue(oMembershipType.isPresent());
		membershipType = oMembershipType.get();
		
		InvitationRSingle invitationRSingle = null;
		InvitationRMarriage invitationRMarriage = null;
		
		oCommunity = communityRepository.findById(14);
		assertTrue(oCommunity.isPresent());
		community = oCommunity.get();
		
		oPerson = personRepository.findById(87);
		assertTrue(oPerson.isPresent());
		person = oPerson.get();
		
		invitation = addInvitation("Test: Guillermo solo", 7, community, false, membershipType); 
		invitationRSingle = addSingle(person);
		invitation.setInvitationRelationship(invitationRSingle);
		invitation = invitationRepository.save(invitation);
		
		oPerson = personRepository.findById(76);
		assertTrue(oPerson.isPresent());
		husband = oPerson.get();
		
		oPerson = personRepository.findById(77);
		assertTrue(oPerson.isPresent());
		wife = oPerson.get();
		
		invitation = addInvitation("Test: Félix y Mayte", 7, community, true, membershipType); 
		invitationRMarriage = addMarriage(husband, wife);
		invitation.setInvitationRelationship(invitationRMarriage);
		invitation = invitationRepository.save(invitation);
		writeLargeJson(invitation);
	}
	
	private Invitation addInvitation(String name, Integer diasVigencia, Community community, Boolean forMarriage, TMembership membershipType) {
		Long iat = System.currentTimeMillis();
		Long exp = iat + diasVigencia*24*60*60*1000;
		Invitation invitation = new Invitation();
		invitation.setCommunity(community);
		invitation.setExp(exp);
		invitation.setIat(iat);
		invitation.setNbf(iat);
		invitation.setName(name);
		invitation.setState(InvitationState.P);
		invitation.setForMarriage(forMarriage);
		invitation.setInvitationType(membershipType);
		return invitationRepository.save(invitation);
		
	}
//	@Transactional
//	@Test
//	@Transactional
	private InvitationRMarriage addMarriage(Person husband, Person wife) {
		InvitationRMarriage aux = new InvitationRMarriage();
		aux.setHusband(husband);
		aux.setWife(wife);
		aux.setDescription(String.format("Marriage of %s and %s", formatName(husband), formatName(wife)));
		aux.setRelationshipName(String.format("%s and %s", formatName(husband), formatName(wife)));
		aux.setOrderList(100);
		invitationMarriageRepository.save(aux);
		return aux;
	}

	private InvitationRSingle addSingle(Person person) {
		InvitationRSingle single = new InvitationRSingle();
		single.setPerson(person);
		single.setRelationshipName(String.format("%s", formatName(person)));
		single.setOrderList(200);
		invitationSingleRepository.save(single);
		return single;
	}

	private String formatName (Person person) {
		return person.getNickname()!=null?person.getNickname():person.getName();
	}
	
	@SuppressWarnings("unused")
	private void writeLargeJson(Invitation invitation) throws StreamWriteException, DatabindException, IOException {
		Path path = Paths.get("queries", "invitation-v2.json");
		File file = path.toFile();
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonFactory jFactory = objectMapper.getFactory(); 
		JsonGenerator jGenerator = null;
//		objectMapper.writeValue(file, invitation);
		
		ObjectWriter writter = null;
		String json = objectMapper.writerWithView(InvitationJsonViews.ComplexInvitation.class).writeValueAsString(invitation);
		System.out.println(json);
		/*
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
		*/
	}

}