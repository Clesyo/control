package com.app.control.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.control.api.exception.ApiException;
import com.app.control.api.exception.EntityNotExist;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(EntityNotExist.class)
	public ResponseEntity<Object> handleEntityNotExist(ApiException ex, WebRequest req) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		return handleExceptionInternal(ex, getProblem(ex, status), new HttpHeaders(), status, req);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<Object> handleApi(ApiException ex, WebRequest req) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return handleExceptionInternal(ex, getProblem(ex, status), new HttpHeaders(), status, req);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		List<Field> fields =  new ArrayList<Field>();
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String field = ((FieldError)error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			fields.add(new Field(field, message));
		}
		ProblemException problem =  new ProblemException();
		problem.setStatus(status.value());
		problem.setTitle("Um ou mais campos estão inválidos."+"Preencha corretamente.");
		problem.setDateTime(OffsetDateTime.now());
		problem.setFields(fields);
		
		return super.handleExceptionInternal(ex,problem, headers, status, request);
	}

	private ProblemException getProblem(ApiException ex, HttpStatus status) {
		// TODO Auto-generated method stub
		ProblemException problem = new ProblemException();
		
		problem.setStatus(status.value());
		problem.setTitle(ex.getMessage());
		problem.setDateTime(OffsetDateTime.now());
		return problem;
	}
}
 