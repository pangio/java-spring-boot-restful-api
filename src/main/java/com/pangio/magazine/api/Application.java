package com.pangio.magazine.api;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.pangio.magazine.api.domain.Article;
import com.pangio.magazine.api.domain.Magazine;
import com.pangio.magazine.api.enums.ArticleType;
import com.pangio.magazine.api.factory.AbstractArticleFactory;
import com.pangio.magazine.api.repository.ArticleRepository;
import com.pangio.magazine.api.repository.MagazineRepository;

/**
 * Main class. Performs the setup of the Spring Boot application. Also creates
 * sample data.
 * 
 * @author pangio
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EntityScan(basePackages = "com.pangio.magazine")
public class Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication
				.run(Application.class);
		MagazineRepository magazineRepository = context
				.getBean(com.pangio.magazine.api.repository.MagazineRepository.class);
		ArticleRepository articleRepository = context
				.getBean(com.pangio.magazine.api.repository.ArticleRepository.class);

		magazineRepository.save(new Magazine("Times"));
		magazineRepository.save(new Magazine("Western Times"));
		magazineRepository.save(new Magazine("ESPN Magazine"));
		
		
		AbstractArticleFactory abstractFactory;
		
	    abstractFactory = AbstractArticleFactory.getFactory(ArticleType.POLITIC);

	    Article politicArticle = abstractFactory.createArticle();
	    politicArticle.setTitle("Title 1");
	    politicArticle.setContent("Content 1");
		articleRepository.save(politicArticle);

	    politicArticle = abstractFactory.createArticle();
	    politicArticle.setTitle("Title 2");
	    politicArticle.setContent("Content 2");
		articleRepository.save(politicArticle);
	    
	    abstractFactory = AbstractArticleFactory.getFactory(ArticleType.SPORT);
	    
	    Article sportArticle = abstractFactory.createArticle();
	    sportArticle.setTitle("Title 3");
	    sportArticle.setContent("Content 3");
		articleRepository.save(sportArticle);

		Magazine magazineTimes = magazineRepository.findByName("Times");
		magazineTimes.setArticles(Arrays.asList(articleRepository.findByTitle("Title 1")));
		magazineRepository.save(magazineTimes);

		Magazine magazineWeekend = magazineRepository.findByName("Western Times");
		magazineWeekend.setArticles(Arrays.asList(articleRepository.findByTitle("Title 2")));
		magazineRepository.save(magazineWeekend);

		Magazine magazineFortune = magazineRepository.findByName("ESPN Magazine");
		magazineFortune.setArticles(Arrays.asList(articleRepository.findByTitle("Title 3")));
		magazineRepository.save(magazineFortune);

		Article article = articleRepository.findByTitle("Title 1");
		article.setMagazine(magazineRepository.findByName("Times"));
		articleRepository.save(article);
		
		article = articleRepository.findByTitle("Title 2");
		article.setMagazine(magazineRepository.findByName("Western Times"));
		articleRepository.save(article);
		
		article = articleRepository.findByTitle("Title 3");
		article.setMagazine(magazineRepository.findByName("ESPN Magazine"));
		articleRepository.save(article);

	}
}
