package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the social_user database table.
 * 
 */
@Entity
@Table(name="social_user")
@NamedQuery(name="SocialUser.findAll", query="SELECT s FROM SocialUser s")
public class SocialUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String email;

	@Column(name="email_verified")
	private Boolean emailVerified;

	@Column(name="image_url")
	private String imageUrl;

	private String name;

	private String provider;

	@Column(name="provider_id")
	private String providerId;

	@Column(name="user_id")
	private String userId;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;

	public SocialUser() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEmailVerified() {
		return this.emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getProviderId() {
		return this.providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}