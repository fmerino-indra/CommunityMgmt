package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fmm.communitymgmt.common.util.Gender;
import org.fmm.oauth2.common.model.model.AbstractPerson;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@DiscriminatorValue("PERSON")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Person extends AbstractPerson implements Serializable {
	private static final long serialVersionUID = 1L;

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
	@JsonManagedReference
	private List<EmailAccount> emailAccounts;

	//bi-directional many-to-one association to MobileNumber
	@OneToMany(mappedBy="person", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
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
	@JsonIgnore
	private List<AppRole> appRoles;

//	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "image_id", referencedColumnName="id")
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private Image image;
	
	public Person() {
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}