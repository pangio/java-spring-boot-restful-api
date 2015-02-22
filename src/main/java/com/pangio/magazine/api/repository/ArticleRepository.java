package com.pangio.magazine.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pangio.magazine.api.domain.Article;

/**
 * {@link ArticleRepository} is where all the {@link Article}s will be stored.
 * Also see {@link JpaRepository}
 * 
 * @author pangio
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	/**
	 * Finds a {@link Article} by id.
	 * @param id of the requested article.
	 * @return the article.
	 */
	Optional<Article> findById(Long id);

	/**
	 * Finds a {@link Article} by title.
	 * @param title of the requested article.
	 * @return the article.
	 */
	Article findByTitle(String title);

}
