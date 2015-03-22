package com.pangio.magazine.api.factory;

import com.pangio.magazine.api.domain.PoliticArticle;

/**
 * Concrete Factory used for the creation of {@link PoliticArticle}s
 * Extends {@link AbstractArticleFactory}
 * @author pangio
 */
public class PoliticArticleFactory extends AbstractArticleFactory{

	/**
	 * Method used to create an {@link PoliticArticle}
	 */
	@Override
	public PoliticArticle createArticle() {
		return new PoliticArticle();
	}
	
}

