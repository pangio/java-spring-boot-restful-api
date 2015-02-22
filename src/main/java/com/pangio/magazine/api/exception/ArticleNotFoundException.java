package com.pangio.magazine.api.exception;

import com.pangio.magazine.api.domain.Article;

/**
 * This exception is thrown when an {@link Article}is not found.
 * @author pangio
 */
public class ArticleNotFoundException extends RuntimeException{

	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = 4268181103917531067L;

	/**
	 * Constructor of the exception
	 * @param articleId
	 */
	public ArticleNotFoundException(Long articleId) {
		super("The Article with id " + articleId + " doesn't exist");
	}
}
