package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the email_account database table.
 * 
 */
@Entity
@Table(name="email_account")
@NamedQuery(name="EmailAccount.findAll", query="SELECT e FROM EmailAccount e")
public class EmailAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="email_account")
	private String emailAccount;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;

	public EmailAccount() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmailAccount() {
		return this.emailAccount;
	}

	public void setEmailAccount(String emailAccount) {
		this.emailAccount = emailAccount;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}