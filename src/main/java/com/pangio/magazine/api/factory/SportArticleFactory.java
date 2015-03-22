package com.pangio.magazine.api.factory;

import com.pangio.magazine.api.domain.SportArticle;

/**
 * Concrete Factory used for the creation of {@link SportArticle}s
 * Extends {@link AbstractArticleFactory}
 * @author pangio
 */
public class SportArticleFactory extends AbstractArticleFactory{

	/**
	 * Method used to create an {@link SportArticle}
	 */
	@Override
	public SportArticle createArticle() {
		return new SportArticle();
	}
	
}

