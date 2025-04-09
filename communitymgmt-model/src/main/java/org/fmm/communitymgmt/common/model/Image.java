package org.fmm.communitymgmt.common.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the person_image database table.
 * 
 */
@Entity
@Table(name="image")
@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i")
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//	@Lob
	@Column(nullable = false, name = "photo", columnDefinition="bytea")
	private byte[] photo;

	@Column(name="mime_type")
	private String mimeType;

//	@Transient
//	private String imageBase64;
	
	public Image() {
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
//		this.imageBase64 = convertToBase64();
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

//	public String getImageBase64() {
//		if (imageBase64 == null)
//			imageBase64 = convertToBase64();
//		return imageBase64;
//	}
//
//	public void setImageBase64(String imageBase64) {
//		this.imageBase64 = imageBase64;
//		this.photo = convertToBinary();
//	}
	
//	private String convertToBase64() {
//		if (photo != null) 
//			return Base64.getEncoder().encodeToString(photo);
//		else
//			return null;
//	}
//	
//	private byte[] convertToBinary() {
//		if (imageBase64 != null)
//			return Base64.getDecoder().decode(imageBase64);
//		else
//			return null;
//		
//	}
}