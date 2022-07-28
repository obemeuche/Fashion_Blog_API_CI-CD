/*
 * Copyright (c) 2022 Payoneer Germany GmbH. All rights reserved.
 */
package com.example.fashionblog.controller;

import com.example.fashionblog.exception.*;
import com.example.fashionblog.model.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@RequiredArgsConstructor
@ControllerAdvice
public class GlobalErrorHandler {

	//private final ErrorResponse errorResponse;
	@ExceptionHandler(UserAlreadyExist.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(final UserAlreadyExist ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setDebugMessage("User already exists. Please check the email/id");
		errorResponse.setStatus(HttpStatus.IM_USED);
		return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
	}

	@ExceptionHandler(UserNotFound.class )
	public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(final UserNotFound ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setDebugMessage("User not found. Please check the email/password");
		errorResponse.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
	}

	@ExceptionHandler(CategoryAlreadyExist.class )
	public ResponseEntity<ErrorResponse> handleCategoryAlreadyExistException(final CategoryAlreadyExist ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setDebugMessage("You already have a category with this name");
		errorResponse.setStatus(HttpStatus.IM_USED);
		return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
	}

	@ExceptionHandler(PermissionDenied.class )
	public ResponseEntity<ErrorResponse> handlePermissionNotAllowedException(final PermissionDenied ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setDebugMessage("You do not have the rights for this");
		errorResponse.setStatus(HttpStatus.BAD_GATEWAY);
		return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
	}

	@ExceptionHandler(CategoryNotFound.class )
	public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(final CategoryNotFound ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setDebugMessage("This category does not exist!");
		errorResponse.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
	}

	@ExceptionHandler(PostAlreadyExist.class )
	public ResponseEntity<ErrorResponse> handlePostAlreadyExistException(final PostAlreadyExist ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setDebugMessage("This post already exist!");
		errorResponse.setStatus(HttpStatus.IM_USED);
		return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
	}

	@ExceptionHandler(PostNotFound.class )
	public ResponseEntity<ErrorResponse> handlePostNotFoundException(final PostNotFound ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setDebugMessage("This post does not exist!");
		errorResponse.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
	}

	@ExceptionHandler(CommentNotFound.class )
	public ResponseEntity<ErrorResponse> handleCommentNotFoundException(final CommentNotFound ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setDebugMessage("This comment does not exist!");
		errorResponse.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
	}

	@ExceptionHandler(UserAlreadyLikedPost.class )
	public ResponseEntity<ErrorResponse> handleCommentNotFoundException(final UserAlreadyLikedPost ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setDebugMessage("You have already liked this post!");
		errorResponse.setStatus(HttpStatus.IM_USED);
		return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
	}
}
