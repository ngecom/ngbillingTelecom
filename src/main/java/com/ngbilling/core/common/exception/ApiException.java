/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package com.ngbilling.core.common.exception;

public abstract class ApiException extends Exception {
    private static final long serialVersionUID = -2061318863847304479L;

    public ApiException(String message) {
        super(message);
    }
}
