package co.edu.eam.ingesoft.products_ms.util;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Error manager.
 *
 * @author caferrerb
 *
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * EntityExistsException error manager.
   *
   * @param ex      exception
   * @param request httprequest
   * @return response
   */
  @ExceptionHandler(value = { EntityExistsException.class })
  protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = ex.getMessage() != null && ex.getMessage().length() > 0 ? ex.getMessage()
        : "Entity already exists";

    ErrorDTO error = new ErrorDTO(bodyOfResponse, HttpStatus.CONFLICT.value());
    return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  /**
   * EntityNotFoundException error manager.
   *
   * @param ex      exception
   * @param request httprequest
   * @return response
   */
  @ExceptionHandler(value = { EntityNotFoundException.class })
  protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = ex.getMessage() != null && ex.getMessage().length() > 0 ? ex.getMessage()
        : "Entity not exists";

    ErrorDTO error = new ErrorDTO(bodyOfResponse, HttpStatus.NOT_FOUND.value());
    return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  /**
   * General Exception error manager.
   *
   * @param ex      exception
   * @param request httprequest
   * @return response
   */
  @ExceptionHandler(value = { Exception.class })
  protected ResponseEntity<Object> serverError(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = ex.getMessage() != null && ex.getMessage().length() > 0 ? ex.getMessage()
        : "Internal server error";

    ErrorDTO error = new ErrorDTO(bodyOfResponse, HttpStatus.INTERNAL_SERVER_ERROR.value());
    return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }
}
