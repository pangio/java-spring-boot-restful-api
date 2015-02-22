package com.pangio.magazine.api.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pangio.magazine.api.enums.ArticleType;
import com.pangio.magazine.api.factory.AbstractArticleFactory;

/**
 * Unit test for the {@link SportArticle} pojo.
 * See also {@link Article}
 * @author pangio
 */
public class SportArticleTest {

	@Test
	public void canCreateAPoliticArticleAndSetAllAttributes() {
		AbstractArticleFactory abstractFactory;
	    abstractFactory = AbstractArticleFactory.getFactory(ArticleType.SPORT);
	    Article sportArticle = abstractFactory.createArticle();
		
		sportArticle.setTitle("Sport article");
		sportArticle.setContent("some content");
		sportArticle.setMainPicturePath("wwww.pix.amazingpic.jpg");
		
		assertEquals("wwww.pix.amazingpic.jpg", sportArticle.getMainPicturePath());
		assertEquals("Sport article", sportArticle.getTitle());
		assertEquals("some content", sportArticle.getContent());
	}
	
}
