/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package com.ngbilling.core.common.exception;

import org.springframework.http.HttpStatus;

public abstract class HttpException extends RuntimeException {
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    protected HttpException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    protected HttpException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    protected HttpException(ApiException exception) {
        super(exception);
    }

    protected HttpException(ApiException exception, HttpStatus httpStatus) {
        this(exception);
        this.httpStatus = httpStatus;
    }

    HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
