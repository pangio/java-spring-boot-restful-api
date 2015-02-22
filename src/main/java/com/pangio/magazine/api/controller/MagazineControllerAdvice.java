package com.pangio.magazine.api.controller;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pangio.magazine.api.exception.ArticleNotFoundException;
import com.pangio.magazine.api.exception.MagazineNotFoundException;

/**
 * Exception handler for the MagazineController.
 * This Controller Advice handles both exceptions {@link MagazineNotFoundException} and {@link ArticleNotFoundException}
 * @author pangio
 */
@ControllerAdvice
public class MagazineControllerAdvice {

	/**
	 * Exception handler of {@link MagazineNotFoundException}
	 */
	@ResponseBody
	@ExceptionHandler(MagazineNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors magazineNotFoundExceptionHandler(MagazineNotFoundException ex) {
		return new VndErrors("error", ex.getMessage());
	}

	/**
	 * Exception handler of {@link ArticleNotFoundException}
	 */
	@ResponseBody
	@ExceptionHandler(ArticleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors articleNotFoundExceptionHandler(ArticleNotFoundException ex) {
		return new VndErrors("error", ex.getMessage());
	}
}
