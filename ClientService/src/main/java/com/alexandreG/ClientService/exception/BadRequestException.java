/**
 * @author alexandre.gaia
 */

package com.alexandreG.ClientService.exception;

import java.io.Serial;

public class BadRequestException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7339546357706827674L;

    public BadRequestException(String message) {
        super(message);
    }
}
