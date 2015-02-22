package com.pangio.magazine.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pangio.magazine.api.domain.Article;
import com.pangio.magazine.api.domain.Magazine;
import com.pangio.magazine.api.domain.PoliticArticle;
import com.pangio.magazine.api.domain.SportArticle;
import com.pangio.magazine.api.repository.ArticleRepository;
import com.pangio.magazine.api.repository.MagazineRepository;

/**
 * Service Layer. {@link MagazineService} provides an interface to do CRUD
 * operations of {@link Magazine}s
 * 
 * @author pangio
 */
@Component
public class MagazineService {

	public MagazineService() {
	}

	public MagazineService(MagazineRepository magazineRepository,
			ArticleRepository articleRepository) {
		this.magazineRepository = magazineRepository;
		this.articleRepository = articleRepository;
	}

	@Autowired
	MagazineRepository magazineRepository;

	@Autowired
	ArticleRepository articleRepository;

	/**
	 * Creates a new {@link Magazine}.
	 * @param magazine - a JSON representation of a magazine
	 */
	public void create(Magazine magazine) {
		this.magazineRepository.save(magazine);
		return;
	}

	/**
	 * Finds a {@link Magazine} by id
	 * @param id of the requested Magazine
	 * @return the magazine
	 */
	public Magazine findById(Long id) {
		Magazine magazine = null;
		magazine = this.magazineRepository.findById(id);
		return magazine;
	}

	/**
	 * Updates a {@link Magazine}
	 * @param magazineId to be updated
	 * @param newMagazine - JSON representation of the new Magazine
	 * @return the updated magazine
	 */
	public Magazine update(Long magazineId, Magazine newMagazine) {
        
		Magazine updatedMagazine = null;
        
		if (magazineId != null && magazineRepository.findById(magazineId) != null) {
			Magazine storedMagazine = magazineRepository.findById(magazineId);
			storedMagazine.setName(newMagazine.getName());
			updatedMagazine = magazineRepository.save(storedMagazine);
		}
		return updatedMagazine;
	}

	/**
	 * Deletes a {@link Magazine}
	 * @param id of the magazine to be deleted.
	 */
	public void delete(Long id) {
		this.magazineRepository.delete(id);
		return;
	}

	/**
	 * Deletes an {@link Article} from the list of the {@link Magazine}
	 * @param magazineId of the magazine which contains the article
	 * @param articleId of the article to be deleted
	 */
	public void deleteArticleFromMagazine(Long articleId) {
		this.articleRepository.delete(articleId);
		return;
	}
	
	/**
	 * Provides a list of all the {@link Magazine}s
	 * @return the list of magazines
	 */
	public List<Magazine> list() {
		return this.magazineRepository.findAll();
	}

	/**
	 * Adds the {@link PoliticArticle} to the articleList of the {@link Magazine}
	 * @param article to be added.
	 * @param magazineId where the article will be added to.
	 */
	public void addPoliticArticle(PoliticArticle article, Long magazineId) {
		Magazine magazine =  this.magazineRepository.findById(magazineId);
		article.setMagazine(magazine);
		this.articleRepository.save(article);
		magazine.addArticle(article);
		this.magazineRepository.save(magazine);
		return;
	}

	/**
	 * Adds the {@link SportArticle} to the articleList of the {@link Magazine}
	 * @param article to be added.
	 * @param magazineId where the article will be added.
	 */
	public void addSportArticle(SportArticle article, Long magazineId) {
		Magazine magazine =  this.magazineRepository.findById(magazineId);
		article.setMagazine(magazine);
		this.articleRepository.save(article);
		magazine.addArticle(article);
		this.magazineRepository.save(magazine);
		return;
	}
	
	/**
	 * Finds the {@link Article} by the id
	 * @param articleId
	 * @return the article
	 */
	public Optional<Article> findArticleById(Long articleId){
		return this.articleRepository.findById(articleId);
	}
}
