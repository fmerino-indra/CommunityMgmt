package org.fmm.communitymgmt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BusinessNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1242720532052608661L;

	public BusinessNotFoundException(String message, Integer id) {
		// Montar mensaje con $1...
		super(message);
	}
	
	public BusinessNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
