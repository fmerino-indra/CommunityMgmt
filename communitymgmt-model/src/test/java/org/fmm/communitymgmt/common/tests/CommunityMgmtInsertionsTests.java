package org.fmm.communitymgmt.common.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.fmm.communitymgmt.common.model.EmailAccount;
import org.fmm.communitymgmt.common.model.Image;
import org.fmm.communitymgmt.common.model.MobileNumber;
import org.fmm.communitymgmt.common.model.Person;
import org.fmm.communitymgmt.common.model.RMarriage;
import org.fmm.communitymgmt.common.model.ROther;
import org.fmm.communitymgmt.common.model.ROthersPerson;
import org.fmm.communitymgmt.common.model.RSingle;
import org.fmm.communitymgmt.common.model.Relationship;
import org.fmm.communitymgmt.common.repository.EmailAccountRepository;
import org.fmm.communitymgmt.common.repository.MarriageRepository;
import org.fmm.communitymgmt.common.repository.MobileNumberRepository;
import org.fmm.communitymgmt.common.repository.OthersRepository;
import org.fmm.communitymgmt.common.repository.PersonRepository;
import org.fmm.communitymgmt.common.repository.RelationshipRepository;
import org.fmm.communitymgmt.common.repository.SingleRepository;
import org.fmm.communitymgmt.common.testconfig.CommunityMgmtCommonTestConfiguration;
import org.fmm.communitymgmt.common.util.DateUtil;
import org.fmm.communitymgmt.common.util.Gender;
import org.fmm.communitymgmt.common.util.MimeTypeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

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
class CommunityMgmtInsertionsTests {

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
	
//	@Test
	void testListRelationship() {
		List<? extends Relationship> matrimonios = null;
		List<? extends Relationship> singles = null;
		
//		relationships = relationshipRepository.findBy();
		matrimonios = relationshipRepository.findAllRelationshipsWithMarriages();
		Assert.notEmpty(matrimonios, "La lista es vacía");

		singles = relationshipRepository.findAllRelationshipsWithSingles();
		Assert.notEmpty(matrimonios, "La lista es vacía");
		
		List<Relationship> lista = new ArrayList<Relationship>();
		lista.addAll(matrimonios);
		lista.addAll(singles);
	}
	
//	@Test
	void testOthersInsertion() {
		Person other1;
		Person other2;

		other1 = addPersonJPQL(Gender.F,"María Rosa","Perea","", "Rosa", null, null, 1960,1,1);
		other2 = addPersonJPQL(Gender.F,"María del Carmen","",null, "Carmen", null, null, 1950 ,1,1);
		addOther(List.of(other1, other2));
	}
//	@Transactional
	@Test
	void addPersonsJPQL() {
		Person husband;
		Person wife;
		Person single;
		
		husband = addPersonJPQL(Gender.M,"Félix","Merino","Martínez de Pinillos", null, "660959325", "felix.merino@gmail.com", 1970,3,21);
		wife = addPersonJPQL(Gender.F,"María Teresa","Cabanes","Miró", "Mayte", "650959325", "mayte.cabanes@gmail.com", 1976,5,4);
		addPhoto(wife, "mayte.png", "mayte-small.png");
		addMarriage(husband, wife, DateUtil.from(1999, 10, 31));
/*		
		husband = addPersonJPQL(Gender.M,"","","", "", null, null, ,,);
		wife = addPersonJPQL(Gender.F,"","","", "", null, null, ,,);
		addMarriage(husband, wife, DateUtil.from(, , ));
*/
		husband = addPersonJPQL(Gender.M,"José Antonio","Zarzuela","", "Zarzu", null, null,1960, 1,1);
		wife = addPersonJPQL(Gender.F,"Pino Rosa","","", "Pino", null, null,1960, 1 ,1);
		addMarriage(husband, wife, DateUtil.from(1970,1,1 ));
		
		husband = addPersonJPQL(Gender.M,"Steven","Rooney","", "Steve", null, null, 1960,3,18);
		wife = addPersonJPQL(Gender.F,"Magdalena","Medina","Balenciaga", null, null, null, 1950 ,1,1);
		addMarriage(husband, wife, DateUtil.from(1980,1,1 ));
		
		husband = addPersonJPQL(Gender.M,"Manuel","Manzano","", "Manolo", null, "", 1965,1,1);
		wife = addPersonJPQL(Gender.F,"Consuelo","","", null, null, null, 1965,1,1);
		addMarriage(husband, wife, DateUtil.from(1965, 1,1));

		husband = addPersonJPQL(Gender.M,"Ángel Luis","","", "Ángel", null, null, 1965,1,1);
		wife = addPersonJPQL(Gender.F,"Yolanda","","", null, null, null,1972 ,1,1);
		addMarriage(husband, wife, DateUtil.from(2000,1 ,1 ));
		
		single = addPersonJPQL(Gender.M, "Juan Antonio", null, null, null, null, null, 1950, 1, 1);
		addSingle(single);

		single = addPersonJPQL(Gender.M, "Guillermo", "Melgares", null, null, null, null, 1960, 1, 1);
		addPhoto(single, "guillermo.png", "guillermo-small.png");
		addSingle(single);

		testOthersInsertion();
}
	
//	@Transactional
	private RMarriage addMarriage(Person husband, Person wife, Date date) {
		RMarriage aux = new RMarriage();
		aux.setDate(date);
		aux.setHusband(husband);
		aux.setWife(wife);
		aux.setDescription(String.format("Marriage of %s and %s - %tD", husband.getNickname(), wife.getNickname(), date));
		aux.setRelationshipName(String.format("Marriage of %s and %s", husband.getNickname(), wife.getNickname()));
		marriageRepository.save(aux);
		return aux;
	}

	private RSingle addSingle(Person person) {
		RSingle single = new RSingle();
		single.setPerson(person);
		single.setRelationshipName(String.format("Single brother of %s", person.getNickname()));
		singleRepository.save(single);
		return single;
	}

	private ROther addOther(List<Person> persons) {
		StringBuilder name = new StringBuilder("Hermanas: ");

		ROther other = new ROther();
		other.setCount(persons.size());
		other.setRelationshipName(name.toString());
		other.setDescription(name.toString());
		persons.stream().forEach(other::addRelatedPerson);
		
		othersRepository.save(other);
		return other;
	}
	
	private Person addPersonJPQL(Gender gender, String name, String surname1, String surname2, String nickname, String phone, String email, int year, int month, int day) {
		Person aux = null;
		MobileNumber mn = null;
		EmailAccount ea = null;
		
		aux = new Person();
		aux.setBirthday(DateUtil.from(year, month, day));
		if (gender == Gender.M)
			aux.setGender(Gender.M);
		else
			aux.setGender(Gender.F);
		System.out.println(org.hibernate.Version.getVersionString());
		System.out.println("Gender before save: " + aux.getGender());

		aux.setGender(gender);
		aux.setName(name);
		aux.setSurname1(surname1);
		aux.setSurname2(surname2);
		if (nickname == null)
			aux.setNickname(name);
		else
			aux.setNickname(nickname);
		
		if (phone != null) {
			mn = new MobileNumber();
			mn.setMobileNumber(phone);
			aux.addMobileNumber(mn);
		}
		if (email != null) {
			ea = new EmailAccount();
			ea.setEmailAccount(email);
			aux.addEmailAccount(ea);
		}
		personRepository.save(aux);
		Assert.notNull(aux.getId(), "El id no puede ser nulo");
		if (email != null)
			Assert.notNull(ea.getId(), "El id no puede ser nulo");
		if (phone != null)
			Assert.notNull(mn.getId(), "El id Phone no puede ser nulo");
		return aux;
	}
	
	private void addPhoto (Person person, String photoName, String smallPhotoName) {
		Path basePath = Paths.get("/home/felix/workspaces/java-2025-01/CommunityMgmt/resources/imgs");
		Path imagePath = basePath.resolve(photoName);
		Path smallImagePath = basePath.resolve(smallPhotoName);
//		if (Files.notExists(basePath)) {
//			Files.createDirectories(basePath);
//			System.out.println("Directorio creado: " + basePath);
//		} else {
//			System.out.println("El directorio ya existe");
//		}
		byte[] imageBytes = null;
		byte[] smallImageBytes = null;
		try {
			imageBytes = Files.readAllBytes(imagePath);
			smallImageBytes = Files.readAllBytes(smallImagePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.notNull(imageBytes, String.format("La imagen %s no puede ser nula", photoName));
		Assert.notNull(smallImageBytes, String.format("La imagen %s no puede ser nula", smallPhotoName));
		
		Image photo = new Image();
		photo.setPhoto(imageBytes);
		photo.setSmallPhoto(smallImageBytes);
		photo.setMimeType(MimeTypeUtil.getMimeType(imagePath.toString()));
		person.setImage(photo);
		personRepository.save(person);
		
	}
	
//	@Test
	public void imagenes() throws Exception {
		Path basePath = Paths.get("/home/felix/workspaces/java-2025-01/CommunityMgmt/imgs");
		Path imagePath = basePath.resolve("mayte.png");
		Path smallImagePath = basePath.resolve("mayte_small.png");
		
		if (Files.notExists(basePath)) {
			Files.createDirectories(basePath);
			System.out.println("Directorio creado: " + basePath);
		} else {
			System.out.println("El directorio ya existe");
		}
		
		byte[] imageBytes = Files.readAllBytes(imagePath);
		byte[] smallImageBytes = Files.readAllBytes(smallImagePath);
		System.out.println("Archivo leído large: " + imageBytes.length);
		System.out.println("Archivo leído small: " + imageBytes.length);
		
		Optional<Person> mayteOpt = personRepository.findById(2);
		if (mayteOpt.isPresent()) {
			Person mayte = mayteOpt.get();
			Image photo = new Image();
			photo.setMimeType(MimeTypeUtil.getMimeType(imagePath.toString()));

			photo.setPhoto(imageBytes);
			photo.setSmallPhoto(smallImageBytes);

			mayte.setImage(photo);
			personRepository.save(mayte);
		}
	}
}