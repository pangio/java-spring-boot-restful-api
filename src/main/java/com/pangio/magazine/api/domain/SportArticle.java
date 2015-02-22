package com.pangio.magazine.api.domain;

import javax.persistence.Entity;

import com.pangio.magazine.api.factory.SportArticleFactory;

/**
 * Domain Layer. Represents the POJO of an {@link SportArticle}.
 * For instantiation of this class use {@link SportArticleFactory}
 * 
 * @author pangio
 */
@Entity
public class SportArticle  extends Article{

	private String subTitle;
	private String thumbnailsPath;

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getThumbnailsPath() {
		return thumbnailsPath;
	}

	public void setThumbnailsPath(String thumbnailsPath) {
		this.thumbnailsPath = thumbnailsPath;
	}

}
