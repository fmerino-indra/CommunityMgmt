package org.fmm.communitymgmt.common.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.fmm.communitymgmt.common.model.common.MStageOfCatechumenate;
import org.fmm.communitymgmt.common.model.common.MStepOfCatechumenateStage;
import org.fmm.communitymgmt.common.model.common.TCharge;
import org.fmm.communitymgmt.common.model.common.TEventType;
import org.fmm.communitymgmt.common.model.common.TTripod;
import org.fmm.communitymgmt.common.model.templates.CelebrationCycleTemplate;
import org.fmm.communitymgmt.common.model.templates.CelebrationEventTemplate;
import org.fmm.communitymgmt.common.repository.common.StageOfCatechumenateRepository;
import org.fmm.communitymgmt.common.repository.common.StepOfCatechumenateStageRepository;
import org.fmm.communitymgmt.common.repository.common.TChargeRepository;
import org.fmm.communitymgmt.common.repository.common.TEventTypeRepository;
import org.fmm.communitymgmt.common.repository.common.TMembershipRepository;
import org.fmm.communitymgmt.common.repository.common.TTripodRepository;
import org.fmm.communitymgmt.common.repository.templates.CelebrationCycleTemplateRepository;
import org.fmm.communitymgmt.common.repository.templates.CelebrationEventTemplateRepository;
import org.fmm.communitymgmt.common.testconfig.CommunityMgmtCommonTestConfiguration;
import org.fmm.communitymgmt.common.util.enums.ChargeEnum;
import org.fmm.communitymgmt.common.util.enums.MembershipTypeEnum;
import org.fmm.communitymgmt.common.util.enums.TripodEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

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
class CommunityMgmtMasterInsertionsTests {

    @Autowired
    private CelebrationCycleTemplateRepository cctRepository;
    
    @Autowired
    private CelebrationEventTemplateRepository cetRepository;
    
    @Autowired
    private TTripodRepository tTripodRepository;
    @Autowired
    private TChargeRepository tChargeRepository;
    @Autowired
    private TMembershipRepository tMembershipRepository;
    @Autowired
    private StageOfCatechumenateRepository stageOfCatechumenateRepository;
    @Autowired
    private StepOfCatechumenateStageRepository stepOfCatechumenateStageRepository;
    @Autowired
    private Environment environment;
    
    @Autowired
    private TEventTypeRepository tEventTypeRepository;
    
//	@Test
	void testConfig() {
		assertNotNull(environment.getProperty("server.port"), "Las propiedades no se han cargado");
	}
	
	@Test
	public void addMasterTypes() {
		addTripodTypes();
		addChargesTypes();
		addMembershipTypes();
		addStagesAndSteps();
		addCycles();
		addTEventTypes();
	}
	private void addTEventTypes() {
		TEventType tEventType = null;
		
		tEventType = new TEventType();
		tEventType.setId(1);
		tEventType.setServiceTypeName("Domestic");
		tEventTypeRepository.save(tEventType);

		tEventType = new TEventType();
		tEventType.setId(2);
		tEventType.setServiceTypeName("In Parish");
		tEventTypeRepository.save(tEventType);
	}

	private void addTripodTypes() {
		TTripod tripod = null;
		
		tripod = TTripod.from(TripodEnum.COMMUNITY);
		tripod.setFrequency(30);
		tTripodRepository.save(tripod);
		
		tripod = TTripod.from(TripodEnum.LITURGY);
		tripod.setFrequency(7);
		tTripodRepository.save(tripod);
		
		tripod = TTripod.from(TripodEnum.WORD);
		tripod.setFrequency(7);
		tTripodRepository.save(tripod);
		
		CelebrationCycleTemplate cct = new CelebrationCycleTemplate();
		cct.setName("");
	}
	private void addChargesTypes() {
		TCharge charge = null;
		charge = TCharge.from(ChargeEnum.RESPONSIBLE);
		tChargeRepository.save(charge);
		
		charge = TCharge.from(ChargeEnum.RESPONSIBLE_SPOUSE);
		tChargeRepository.save(charge);

		charge = TCharge.from(ChargeEnum.CORESPONSIBLE);
		tChargeRepository.save(charge);
		
		charge = TCharge.from(ChargeEnum.CORESPONSIBLE_SPOUSE);
		tChargeRepository.save(charge);

		charge = TCharge.from(ChargeEnum.PSALM_SINGER);
		tChargeRepository.save(charge);
		
		charge = TCharge.from(ChargeEnum.PSALM_SINGER_RESPONSIBLE);
		tChargeRepository.save(charge);
		
		charge = TCharge.from(ChargeEnum.READER);
		tChargeRepository.save(charge);
		
		charge = TCharge.from(ChargeEnum.READER_RESPONSIBLE);
		tChargeRepository.save(charge);
		
		charge = TCharge.from(ChargeEnum.PRIEST);
		tChargeRepository.save(charge);

		charge = TCharge.from(ChargeEnum.OSTIARY);
		tChargeRepository.save(charge);
	}
	
	private void addMembershipTypes() {
		tMembershipRepository.save(MembershipTypeEnum.CURRENT.toModel());
		tMembershipRepository.save(MembershipTypeEnum.MISSION.toModel());
		tMembershipRepository.save(MembershipTypeEnum.OTHER.toModel());
		tMembershipRepository.save(MembershipTypeEnum.ORIGIN.toModel());
	}
	private void addStagesAndSteps() {
		MStageOfCatechumenate stage = null;

		stage = new MStageOfCatechumenate();
		stage.setId(1);
		stage.setStage("Pre-catechumenate");
		stage.setFeature("Humility");
		stageOfCatechumenateRepository.save(stage);
		addPrechatechumenate(stage);
		
		stage = new MStageOfCatechumenate();
		stage.setId(2);
		stage.setStage("Catechumenate");
		stage.setFeature("Simplicity");
		stageOfCatechumenateRepository.save(stage);
		addChatechumenate(stage);
		
		stage = new MStageOfCatechumenate();
		stage.setId(3);
		stage.setStage("Election");
		stage.setFeature("Praise");
		stageOfCatechumenateRepository.save(stage);
		addElection(stage);

		stage = new MStageOfCatechumenate();
		stage.setId(4);
		stage.setStage("Post-Election"); // Don't know the name
		stageOfCatechumenateRepository.save(stage);
		addPostElection(stage);
	}
	
	private void addPrechatechumenate(MStageOfCatechumenate stage) {
		MStepOfCatechumenateStage step = null;
		
		step = new MStepOfCatechumenateStage();
		step.setId(0);
		step.setStep("Community insititution - End Catechesis Convivence"); // First Baptismal Scrutiny
		step.setStage(stage);
		stepOfCatechumenateStageRepository.save(step);
		
		step = new MStepOfCatechumenateStage();
		step.setId(1);
		step.setStep("First Baptismal Scrutiny"); // First Baptismal Scrutiny
		step.setStage(stage);
		stepOfCatechumenateStageRepository.save(step);
		
		step = new MStepOfCatechumenateStage();
		step.setId(2);
		step.setStep("Shemá Israel"); // Shema
		step.setStage(stage);
		stepOfCatechumenateStageRepository.save(step);
		
		step = new MStepOfCatechumenateStage();
		step.setId(3);
		step.setStep("Second Baptismal Scrutiny"); // Second Baptismal Scrutiny
		step.setStage(stage);
		stepOfCatechumenateStageRepository.save(step);
	}
	private void addChatechumenate(MStageOfCatechumenate stage) {
		MStepOfCatechumenateStage step = null;
		
		step = new MStepOfCatechumenateStage();
		step.setId(4);
		step.setStep("1st Initiation to Prayer"); // Giving or delivery of the Psalter
		step.setStage(stage);
		stepOfCatechumenateStageRepository.save(step);
		
		step = new MStepOfCatechumenateStage();
		step.setId(5);
		step.setStep("Traditio Symboli"); // First Baptismal Scrutiny
		step.setStage(stage);
		stepOfCatechumenateStageRepository.save(step);
		
		step = new MStepOfCatechumenateStage();
		step.setId(6);
		step.setStep("Redditio Symboli");
		step.setStage(stage);
		stepOfCatechumenateStageRepository.save(step);
		
		step = new MStepOfCatechumenateStage();
		step.setId(7);
		step.setStep("2nd Initiation to Prayer: Our Father"); // Giving or Delivery of the Our Father, Rite of the Delivery... 
		step.setStage(stage);
		stepOfCatechumenateStageRepository.save(step);
		
	}

	private void addElection(MStageOfCatechumenate stage) {
		MStepOfCatechumenateStage step = null;
		
		step = new MStepOfCatechumenateStage();
		step.setId(8);
		step.setStep("Election (I)"); 
		step.setStage(stage);
		stepOfCatechumenateStageRepository.save(step);
		
		step = new MStepOfCatechumenateStage();
		step.setId(9);
		step.setStep("Election (II)"); 
		step.setStage(stage);
		stepOfCatechumenateStageRepository.save(step);
		
		step = new MStepOfCatechumenateStage();
		step.setId(10);
		step.setStep("Third Scrutiny"); // Renewal of Baptismal Promises 
		step.setStage(stage);
		stepOfCatechumenateStageRepository.save(step);
		
	}

	private void addPostElection(MStageOfCatechumenate stage) {
		MStepOfCatechumenateStage step = null;
		
		step = new MStepOfCatechumenateStage();
		step.setId(11);
		step.setStep("Renewal of the Baptismal Promises"); // of the Promises of the Baptism 
		step.setStage(stage);
		stepOfCatechumenateStageRepository.save(step);
		
	}

	private void addCycles() {
		CelebrationCycleTemplate cct = null;
		MStepOfCatechumenateStage step = new MStepOfCatechumenateStage();
		step.setId(0);
		
		
		cct = new CelebrationCycleTemplate();
		cct.setStep(step);
		cct.setId(0);
		cct.setName("Word service");
		cct.setNumber(1);
		cct.setStep(step);
		cct.setTeamNeaded(true);
		cctRepository.save(cct);
		addEventsToCycleWord(cct);
		
		step = new MStepOfCatechumenateStage();
		step.setId(11);

		cct = new CelebrationCycleTemplate();
		cct.setStep(step);
		cct.setId(1);
		cct.setName("Aliance service");
		cct.setNumber(4);
		cct.setTeamNeaded(true);
		cctRepository.save(cct);
		addEventsToCycleAliance(cct);
		
	}
	
	private void addEventsToCycleWord(CelebrationCycleTemplate cct ) {
		CelebrationEventTemplate cet = null;
		cet = new CelebrationEventTemplate();
		cet.setCct(cct);
		cet.setId(1);
		cet.setName("Word service");
		cet.setTeamNeaded(false);
		cet.setOrder(1);
		cetRepository.save(cet);
	}
	private void addEventsToCycleAliance(CelebrationCycleTemplate cct ) {
		CelebrationEventTemplate cet = null;
		
		cet = new CelebrationEventTemplate();
		cet.setCct(cct);
		cet.setId(2);
		cet.setName("Presentation Word");
		cet.setTeamNeaded(false);
		cet.setOrder(1);
		cetRepository.save(cet);

		cet = new CelebrationEventTemplate();
		cet.setCct(cct);
		cet.setId(3);
		cet.setName("Domestical scrutiny");
		cet.setTeamNeaded(true);
		cet.setOrder(2);
		cetRepository.save(cet);

		cet = new CelebrationEventTemplate();
		cet.setCct(cct);
		cet.setId(4);
		cet.setName("Conclusion Word");
		cet.setTeamNeaded(false);
		cet.setOrder(3);
		cetRepository.save(cet);

		cet = new CelebrationEventTemplate();
		cet.setCct(cct);
		cet.setId(5);
		cet.setName("Guarantors");
		cet.setTeamNeaded(true);
		cet.setOrder(4);
		cetRepository.save(cet);
	}
}