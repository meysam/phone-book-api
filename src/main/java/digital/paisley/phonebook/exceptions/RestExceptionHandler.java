package digital.paisley.phonebook.exceptions;

import digital.paisley.commons.error.RestError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
class RestExceptionHandler {

  private static final String DEFAULT_MORE_INFO_URL = "mailto:support@paisley.digital";

  @ExceptionHandler(RestErrorException.class)
  @ResponseBody
  protected RestError restError(RestErrorException ex, WebRequest request) {
    return new RestError(
        ex.getCode(),
        ex.getCode(),
        ex.getMessage(),
        ex.getDeveloperMessage(),
        DEFAULT_MORE_INFO_URL,
        ex.getThrowable());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  protected RestError restError(MethodArgumentNotValidException ex, WebRequest request) {
    return new RestError(
           400,
            400,
            ex.getMessage(),
            ex.getMessage(),
            DEFAULT_MORE_INFO_URL,
            ex.getCause());
  }

}
