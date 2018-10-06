package com.bestray.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="There is already a user registered with the email provided")  // 400
public class RegExceptionHandler extends Exception {
	
}
