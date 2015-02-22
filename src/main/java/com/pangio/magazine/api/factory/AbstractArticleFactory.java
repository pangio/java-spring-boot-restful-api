package com.pangio.magazine.api.factory;

import com.pangio.magazine.api.domain.Article;
import com.pangio.magazine.api.enums.ArticleType;

/**
 * Abstract Factory used for the creation of any kind of {@link Article}s
 * @author pangio
 */
public abstract class AbstractArticleFactory {

	private static final PoliticArticleFactory politic = new PoliticArticleFactory();

	private static final SportArticleFactory sport = new SportArticleFactory();
		
	public static final AbstractArticleFactory getFactory(ArticleType article) {
		
		switch (article) {
		
		case SPORT:
			return sport;

		case POLITIC:
			return politic;
		} 

		throw new IllegalArgumentException(article.toString());

	} 

	/**
	 * Method used to create an {@link Article}
	 * @return
	 */
	public abstract Article createArticle();
	
} 

