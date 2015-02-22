package com.pangio.magazine.api.exception;

import com.pangio.magazine.api.domain.Magazine;

/**
 * This exception is thrown when an {@link Magazine}is not found.
 * @author pangio
 */
public class MagazineNotFoundException extends RuntimeException{

	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = 3213530216681486765L;

	/**
	 * Constructor of the exception
	 * @param magazineId
	 */
	public MagazineNotFoundException(Long magazineId) {
		super("The Magazine with id " + magazineId + " doesn't exist");
	}
}
