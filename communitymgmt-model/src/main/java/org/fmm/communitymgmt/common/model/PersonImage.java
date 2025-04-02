package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the person_image database table.
 * 
 */
@Entity
@Table(name="person_image")
@NamedQuery(name="PersonImage.findAll", query="SELECT p FROM PersonImage p")
public class PersonImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private byte[] photo;

	@Column(name="photo_type")
	private String photoType;

	public PersonImage() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getPhotoType() {
		return this.photoType;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

}