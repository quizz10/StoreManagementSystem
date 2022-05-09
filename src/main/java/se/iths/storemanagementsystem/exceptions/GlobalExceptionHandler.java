package se.iths.storemanagementsystem.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import se.iths.storemanagementsystem.exceptions.customexceptions.DuplicateEmailException;
import se.iths.storemanagementsystem.exceptions.customexceptions.ShortPasswordException;
import se.iths.storemanagementsystem.exceptions.customexceptions.WrongEmailFormatException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    // CUSTOM EXCEPTION
    @ExceptionHandler({ShortPasswordException.class})
    public ResponseEntity<Object> shortPasswordException(ShortPasswordException e) {
        logger.info(e.getClass().getName());
        String errorMessage = "User entered short password";

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMessage, e));
    }
    @ExceptionHandler({DuplicateEmailException.class})
    public ResponseEntity<Object> duplicateEmailException(DuplicateEmailException d) {
        logger.info(d.getClass().getName());
        String errorMessage = "User entered duplicate email";

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMessage, d));
    }
    @ExceptionHandler({WrongEmailFormatException.class})
    public ResponseEntity<Object> wrongEmailFormatException(WrongEmailFormatException w) {
        logger.info(w.getClass().getName());
        String errorMessage = "User entered email incorrectly";

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMessage, w));
    }

    // BASE EXCEPTION
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info(e.getClass().getName());
        String errorMessage = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMessage, e));
    }

    // ALL EXCEPTIONS
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception e) {
        logger.info(e.getClass().getName());
        logger.error("Error: ", e);
        String errorMessage = "An unexpected error occurred.";
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, e));
    }

}