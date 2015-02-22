package com.pangio.magazine.api.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pangio.magazine.api.enums.ArticleType;
import com.pangio.magazine.api.factory.AbstractArticleFactory;

/**
 * Unit test for the {@link PoliticArticle} pojo.
 * See algo {@link Article}
 * @author pangio
 */
public class PoliticArticleTest {

	@Test
	public void canCreateAPoliticArticleAndSetAllAttributes() {
		AbstractArticleFactory abstractFactory;
	    abstractFactory = AbstractArticleFactory.getFactory(ArticleType.POLITIC);
	    Article politicArticle = abstractFactory.createArticle();
		
		
		politicArticle.setTitle("Politic article");
		politicArticle.setContent("some content");
		politicArticle.setMainPicturePath("wwww.pix.amazingpic.jpg");
		
		assertEquals("wwww.pix.amazingpic.jpg", politicArticle.getMainPicturePath());
		assertEquals("Politic article", politicArticle.getTitle());
		assertEquals("some content", politicArticle.getContent());
	}
	
}
