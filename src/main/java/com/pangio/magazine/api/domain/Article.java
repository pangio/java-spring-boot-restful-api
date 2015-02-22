package com.pangio.magazine.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Domain Layer. Represents the POJO of an {@link Article}.
 * @author pangio
 */
@Entity
public abstract class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String content;
	private String mainPicturePath;
    @ManyToOne
    @JsonBackReference
    private Magazine magazine;

	/**
	 * {@link Article } default constructor required by JPA
	 */
	Article() {
	}

	public Magazine getMagazine() {
		return magazine;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	public String getMainPicturePath() {
		return mainPicturePath;
	}

	public void setMainPicturePath(String mainPicturePath) {
		this.mainPicturePath = mainPicturePath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
