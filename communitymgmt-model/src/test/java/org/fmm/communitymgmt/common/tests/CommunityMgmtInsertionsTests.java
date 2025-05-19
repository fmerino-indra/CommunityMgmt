package org.fmm.communitymgmt.common.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.EmailAccount;
import org.fmm.communitymgmt.common.model.Image;
import org.fmm.communitymgmt.common.model.Membership;
import org.fmm.communitymgmt.common.model.MobileNumber;
import org.fmm.communitymgmt.common.model.Person;
import org.fmm.communitymgmt.common.model.RMarriage;
import org.fmm.communitymgmt.common.model.ROther;
import org.fmm.communitymgmt.common.model.RSingle;
import org.fmm.communitymgmt.common.model.Relationship;
import org.fmm.communitymgmt.common.model.TMembership;
import org.fmm.communitymgmt.common.repository.CommunityRepository;
import org.fmm.communitymgmt.common.repository.EmailAccountRepository;
import org.fmm.communitymgmt.common.repository.MarriageRepository;
import org.fmm.communitymgmt.common.repository.MembershipRepository;
import org.fmm.communitymgmt.common.repository.MembershipTypeRepository;
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
//	@Test
	void addPersonsJPQL() {
		Person husband;
		Person wife;
		Person single;

		husband = addPersonJPQL(Gender.M,"Miguel María","Soler","Areta", "Miguel", "653286044", "miguelyalmudena@hotmail.com", 1970,2,1);
		wife = addPersonJPQL(Gender.F,"Almudena","Molero","", null, "660477633", "miguelyalmudena@hotmail.com", 1965,1,1);
		addPhoto(wife, "almudena", "png");
		addMarriage(husband, wife, DateUtil.from(1995, 1, 1));
		
		husband = addPersonJPQL(Gender.M,"Félix","Merino","Martínez de Pinillos", null, "660959325", "felix.merino@gmail.com", 1970,3,21);
		wife = addPersonJPQL(Gender.F,"María Teresa","Cabanes","Miró", "Mayte", "650676743", "mayte.cabanes@gmail.com", 1976,5,4);
		addPhoto(wife, "mayte", "jpg");
		addMarriage(husband, wife, DateUtil.from(1999, 10, 31));
/*		
		husband = addPersonJPQL(Gender.M,"","","", "", null, null, ,,);
		wife = addPersonJPQL(Gender.F,"","","", "", null, null, ,,);
		addMarriage(husband, wife, DateUtil.from(, , ));
*/
		husband = addPersonJPQL(Gender.M,"José Antonio","Zarzuela","", "Zarzu", "610596076", null,1960, 1,1);
		addPhoto(husband, "zarzu", "png");
		wife = addPersonJPQL(Gender.F,"Pino Rosa","","", "Pino", null, null,1960, 1 ,1);
		addMarriage(husband, wife, DateUtil.from(1970,1,1 ));
		addPhoto(wife,"pino","jpg");
		
		husband = addPersonJPQL(Gender.M,"Steven","Rooney","", "Steve", null, null, 1960,3,18);
		wife = addPersonJPQL(Gender.F,"Magdalena","Medina","Balenciaga", null, null, null, 1950 ,1,1);
		addMarriage(husband, wife, DateUtil.from(1980,1,1 ));
		
		husband = addPersonJPQL(Gender.M,"Manuel","Manzano","", "Manolo", null, "", 1965,1,1);
		wife = addPersonJPQL(Gender.F,"Consuelo","","", null, null, null, 1965,1,1);
		addMarriage(husband, wife, DateUtil.from(1965, 1,1));

		husband = addPersonJPQL(Gender.M,"Ángel Luis","","", "Ángel", "676474959", null, 1965,1,1);
		addPhoto(husband, "angelluis", "jpg");
		wife = addPersonJPQL(Gender.F,"Yolanda","","", null, "619553228", null,1972 ,1,1);
		addPhoto(wife,"yolanda","jpg");
		addMarriage(husband, wife, DateUtil.from(2000,1 ,1 ));
		
		single = addPersonJPQL(Gender.M, "Juan Antonio", null, null, null, null, null, 1950, 1, 1);
		addSingle(single);

		single = addPersonJPQL(Gender.M, "Guillermo", "Melgares", null, null, "617806438", "guiller1313@hotmail.com", 1960, 1, 1);
		addPhoto(single, "guillermo", "jpg");
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
		aux.setRelationshipName(String.format("%s and %s", husband.getNickname(), wife.getNickname()));
		aux.setOrderList(10);
		marriageRepository.save(aux);
		return aux;
	}

	private RSingle addSingle(Person person) {
		RSingle single = new RSingle();
		single.setPerson(person);
		single.setRelationshipName(String.format("%s", person.getNickname()));
		single.setOrderList(50);
		singleRepository.save(single);
		return single;
	}

	private ROther addOther(List<Person> persons) {
		StringBuilder name = new StringBuilder("Hermanas: ");

		ROther other = new ROther();
		other.setCount(persons.size());
		other.setRelationshipName(name.toString());
		other.setDescription(name.toString());
		other.setOrderList(30);
		persons.stream().forEach(other::addRelatedPerson);
		
		othersRepository.save(other);
		return other;
	}
	
	private Person addPersonJPQL(Gender gender, String name, String surname1, String surname2, String nickname, String phone, String email, int year, int month, int day) {
		Person aux = null;
		MobileNumber mn = null;
		EmailAccount ea = null;
		
		aux = new Person();
		aux.setBirthday(DateUtil.toEpochDays(year, month, day));
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
	
	private void addPhoto (Person person, String photoName, String extension) {
		Path basePath = Paths.get("/home/felix/workspaces/java-2025-01/CommunityMgmt/resources/imgs");
		Path tinyPhotoPath = basePath.resolve(photoName+"-tiny."+extension);
		Path smallPhotoPath = basePath.resolve(photoName+"-small."+extension);
//		if (Files.notExists(basePath)) {
//			Files.createDirectories(basePath);
//			System.out.println("Directorio creado: " + basePath);
//		} else {
//			System.out.println("El directorio ya existe");
//		}
		byte[] tinyPhotoBytes = null;
		byte[] smallPhotoBytes = null;
		try {
			tinyPhotoBytes = Files.readAllBytes(tinyPhotoPath);
			smallPhotoBytes = Files.readAllBytes(smallPhotoPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.notNull(smallPhotoBytes, String.format("La imagen %s no puede ser nula", smallPhotoPath.toString()));
		Assert.notNull(tinyPhotoBytes, String.format("La imagen %s no puede ser nula", tinyPhotoPath.toString()));
		
		Image image = new Image();
		image.setTinyPhoto(tinyPhotoBytes);
		image.setSmallPhoto(smallPhotoBytes);
		image.setMimeType(MimeTypeUtil.getMimeType(tinyPhotoPath.toString()));
		person.setImage(image);
		personRepository.save(person);
		
	}
	
//	@Test
	public void imagenes() throws Exception {
		Path basePath = Paths.get("/home/felix/workspaces/java-2025-01/CommunityMgmt/imgs");
		Path tinyImagePath = basePath.resolve("mayte-tiny.jpg");
		Path smallImagePath = basePath.resolve("mayte_small.jpg");
		
		if (Files.notExists(basePath)) {
			Files.createDirectories(basePath);
			System.out.println("Directorio creado: " + basePath);
		} else {
			System.out.println("El directorio ya existe");
		}
		
		byte[] tinyImageBytes = Files.readAllBytes(tinyImagePath);
		byte[] smallImageBytes = Files.readAllBytes(smallImagePath);
		System.out.println("Archivo leído large: " + tinyImageBytes.length);
		System.out.println("Archivo leído small: " + smallImageBytes.length);
		
		Optional<Person> mayteOpt = personRepository.findById(2);
		if (mayteOpt.isPresent()) {
			Person mayte = mayteOpt.get();
			Image image = new Image();
			image.setMimeType(MimeTypeUtil.getMimeType(tinyImagePath.toString()));

			image.setTinyPhoto(tinyImageBytes);
			image.setSmallPhoto(smallImageBytes);

			mayte.setImage(image);
			personRepository.save(mayte);
		}
	}

	@Test
	public void addCommunities() {
		Community com = new Community();
		com = addCommunity("2", "Santa Catalina de Siena", "Juan de Urbieta", "51", "28007", "Madrid", "España", false);
		addStandardMember(76, com);
		addStandardMember(77, com);
		
		addStandardMember(80, com);
		addStandardMember(81, com);

		com = addCommunity("1", "San Pedro Regalado y San José de Calasanz", null, null, null, "Madrid", "España", false);
		addStandardMember(80, com);
		addStandardMember(81, com);
	}
	
	private Membership addStandardMember(Integer pId, Community com) {
		Optional<Person> personOpt = personRepository.findById(pId);
		Person person = null;
		Membership m = null;
		TMembership tm = null;
		if (personOpt.isPresent()) {
			person = personOpt.get();
			m = new Membership();
			tm = membershipTypeRepository.findById(1).get();
			m.setPerson(person);
			m.setCommunity(com);
			m.setMembershipType(tm);
			m = membershipRepository.save(m);
			assertNotNull(m, "Error al grabar Membership");
		}
		return m;
	}
	private Community addCommunity(String number, String parish, String address, String addressNumber, String postalCode, String city, String country, Boolean activated) {
		Community com = new Community();
		com.setCommunityNumber(number);
		com.setParish(parish);
		com.setParishAddress(address);
		com.setParishAddressNumber(addressNumber);
		com.setParishAddressPostalCode(postalCode);
		com.setParishAddressCity(city);
		com.setCountry(country);
		com.setIsActivated(activated);
		com = communityRepository.save(com);
		assertNotNull(com);
		assertNotNull(com.getId(), "Id NO generado correctamente");
		return com;
	}
}