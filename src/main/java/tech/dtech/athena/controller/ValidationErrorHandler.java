package tech.dtech.athena.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tech.dtech.athena.config.validation.dto.ValidationErrorDTO;

@RestControllerAdvice
public class ValidationErrorHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ValidationErrorDTO> handle(MethodArgumentNotValidException exception) {
		List<ValidationErrorDTO> response = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		fieldErrors.stream().forEach(e-> {
			String errorMessage = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ValidationErrorDTO error = new ValidationErrorDTO(e.getField(), errorMessage);
			response.add(error);
		});
		
		return response;
	}

}
