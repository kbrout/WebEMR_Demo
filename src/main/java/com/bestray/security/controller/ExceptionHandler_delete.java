package com.bestray.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No User availabe in this Username")  // 404
public class ExceptionHandler_delete extends Exception{

}
