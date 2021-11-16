package br.dev.rodrigocury.testewswork.controllers.errorhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandling {

  private final MessageSource messageSource;

  @Autowired
  public ErrorHandling(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Map<String, String>> entityNotFoundHandler(EntityNotFoundException ex){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(singleMessageBuilder(ex.getMessage()));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, String>> illegalArgumentHandler (IllegalArgumentException ex){
    return ResponseEntity.status(400).body(singleMessageBuilder(ex.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
    Map<String, String> fieldErrors = new HashMap<String, String>();

    ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
      fieldErrors.put(fieldError.getField(), messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
    });

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldErrors);
  }

  private Map<String, String> singleMessageBuilder(String s){
    Map<String, String> message = new HashMap<String, String>();
    message.put("message", s);
    return message;
  }

}
