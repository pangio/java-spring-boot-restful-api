package com.pangio.magazine.api.domain;

import javax.persistence.Entity;

import com.pangio.magazine.api.factory.PoliticArticleFactory;

/**
 * Domain Layer. Represents the POJO of a {@link PoliticArticle}.
 * For instantiation of this class use {@link PoliticArticleFactory}
 * @author pangio
 */
@Entity
public class PoliticArticle  extends Article{

	private String introduction;
	private String ending;

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getEnding() {
		return ending;
	}

	public void setEnding(String ending) {
		this.ending = ending;
	}

}
