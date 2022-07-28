/*
 * Copyright (c) 2022 Payoneer Germany GmbH. All rights reserved.
 */
package com.example.fashionblog.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
	private String message;
	private HttpStatus status;
	private String debugMessage;
	private LocalDateTime time = LocalDateTime.now();
}
