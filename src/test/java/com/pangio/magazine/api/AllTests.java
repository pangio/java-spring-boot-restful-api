package com.pangio.magazine.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.pangio.magazine.api.controller.MagazineControllerTest;
import com.pangio.magazine.api.domain.ArticleTest;
import com.pangio.magazine.api.domain.MagazineTest;
import com.pangio.magazine.api.domain.PoliticArticleTest;
import com.pangio.magazine.api.domain.SportArticleTest;
import com.pangio.magazine.api.service.MagazineServiceTest;

/**
 * Test Suite. 
 * Includes unit tests & integration tests covering all layers of the application Domain Controllers and Services.
 * @author pangio
 */
@RunWith(Suite.class)
@SuiteClasses({ 
	MagazineTest.class, 
	ArticleTest.class, 
	PoliticArticleTest.class, 
	SportArticleTest.class,	
	MagazineControllerTest.class, 
	MagazineServiceTest.class })
public class AllTests {

}
