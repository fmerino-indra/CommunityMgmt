package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fmm.communitymgmt.common.util.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Enumerated(EnumType.STRING) // Guarda "M" o "F" como texto
	@Column(nullable = false, length = 1)
	private Gender gender;

	private String name;

	private String nickname;

	@Temporal(TemporalType.DATE)
	@Column(name="passing_date")
	private Date passingDate;

	private String surname1;

	private String surname2;

	//bi-directional many-to-one association to EmailAccount
	@OneToMany(mappedBy="person", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmailAccount> emailAccounts;

	//bi-directional many-to-one association to MobileNumber
	@OneToMany(mappedBy="person", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MobileNumber> mobileNumbers;

	//bi-directional many-to-many association to AppRole
	@ManyToMany
	@JoinTable(
		name="person_app_role"
		, joinColumns={
			@JoinColumn(name="person_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="app_role_id")
			}
		)
	private List<AppRole> appRoles;

/* Creo que no queremos la relación bidireccional
	//bi-directional many-to-one association to RMarriage
	@OneToMany(mappedBy="person1")
	private List<RMarriage> RMarriages1;

	//bi-directional many-to-one association to RMarriage
	@OneToMany(mappedBy="person2")
	private List<RMarriage> RMarriages2;

	//bi-directional many-to-one association to ROthersPerson
	@OneToMany(mappedBy="person")
	private List<ROthersPerson> ROthersPersons;

	//bi-directional many-to-one association to RSingle
	@OneToMany(mappedBy="person")
	private List<RSingle> RSingles;
*/
	//bi-directional many-to-one association to SocialUser
	@OneToMany(mappedBy="person")
	private List<SocialUser> socialUsers;

	public Person() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getPassingDate() {
		return this.passingDate;
	}

	public void setPassingDate(Date passingDate) {
		this.passingDate = passingDate;
	}

	public String getSurname1() {
		return this.surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return this.surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public List<EmailAccount> getEmailAccounts() {
		return this.emailAccounts;
	}

	public void setEmailAccounts(List<EmailAccount> emailAccounts) {
		this.emailAccounts = emailAccounts;
	}

	public EmailAccount addEmailAccount(EmailAccount emailAccount) {
		if (getEmailAccounts() == null) {
			setEmailAccounts(new ArrayList<EmailAccount>());
		}
		getEmailAccounts().add(emailAccount);
		emailAccount.setPerson(this);

		return emailAccount;
	}

	public EmailAccount removeEmailAccount(EmailAccount emailAccount) {
		getEmailAccounts().remove(emailAccount);
		emailAccount.setPerson(null);

		return emailAccount;
	}

	public List<MobileNumber> getMobileNumbers() {
		return this.mobileNumbers;
	}

	public void setMobileNumbers(List<MobileNumber> mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}

	public MobileNumber addMobileNumber(MobileNumber mobileNumber) {
		if (getMobileNumbers() == null) {
			setMobileNumbers(new ArrayList<MobileNumber>());
		}
		getMobileNumbers().add(mobileNumber);
		mobileNumber.setPerson(this);

		return mobileNumber;
	}

	public MobileNumber removeMobileNumber(MobileNumber mobileNumber) {
		getMobileNumbers().remove(mobileNumber);
		mobileNumber.setPerson(null);

		return mobileNumber;
	}

	public List<AppRole> getAppRoles() {
		return this.appRoles;
	}

	public void setAppRoles(List<AppRole> appRoles) {
		this.appRoles = appRoles;
	}
/*
	public List<RMarriage> getRMarriages1() {
		return this.RMarriages1;
	}

	public void setRMarriages1(List<RMarriage> RMarriages1) {
		this.RMarriages1 = RMarriages1;
	}

	public RMarriage addRMarriages1(RMarriage RMarriages1) {
		getRMarriages1().add(RMarriages1);
		RMarriages1.setPerson1(this);

		return RMarriages1;
	}

	public RMarriage removeRMarriages1(RMarriage RMarriages1) {
		getRMarriages1().remove(RMarriages1);
		RMarriages1.setPerson1(null);

		return RMarriages1;
	}

	public List<RMarriage> getRMarriages2() {
		return this.RMarriages2;
	}

	public void setRMarriages2(List<RMarriage> RMarriages2) {
		this.RMarriages2 = RMarriages2;
	}

	public RMarriage addRMarriages2(RMarriage RMarriages2) {
		getRMarriages2().add(RMarriages2);
		RMarriages2.setPerson2(this);

		return RMarriages2;
	}

	public RMarriage removeRMarriages2(RMarriage RMarriages2) {
		getRMarriages2().remove(RMarriages2);
		RMarriages2.setPerson2(null);

		return RMarriages2;
	}

	public List<ROthersPerson> getROthersPersons() {
		return this.ROthersPersons;
	}

	public void setROthersPersons(List<ROthersPerson> ROthersPersons) {
		this.ROthersPersons = ROthersPersons;
	}

	public ROthersPerson addROthersPerson(ROthersPerson ROthersPerson) {
		getROthersPersons().add(ROthersPerson);
		ROthersPerson.setPerson(this);

		return ROthersPerson;
	}

	public ROthersPerson removeROthersPerson(ROthersPerson ROthersPerson) {
		getROthersPersons().remove(ROthersPerson);
		ROthersPerson.setPerson(null);

		return ROthersPerson;
	}

	public List<RSingle> getRSingles() {
		return this.RSingles;
	}

	public void setRSingles(List<RSingle> RSingles) {
		this.RSingles = RSingles;
	}

	public RSingle addRSingle(RSingle RSingle) {
		getRSingles().add(RSingle);
		RSingle.setPerson(this);

		return RSingle;
	}

	public RSingle removeRSingle(RSingle RSingle) {
		getRSingles().remove(RSingle);
		RSingle.setPerson(null);

		return RSingle;
	}

	public List<SocialUser> getSocialUsers() {
		return this.socialUsers;
	}

	public void setSocialUsers(List<SocialUser> socialUsers) {
		this.socialUsers = socialUsers;
	}

	public SocialUser addSocialUser(SocialUser socialUser) {
		getSocialUsers().add(socialUser);
		socialUser.setPerson(this);

		return socialUser;
	}

	public SocialUser removeSocialUser(SocialUser socialUser) {
		getSocialUsers().remove(socialUser);
		socialUser.setPerson(null);

		return socialUser;
	}
*/
}