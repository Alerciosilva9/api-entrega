package com.spring.entrega.api.exceptionhandler;


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

import com.spring.entrega.api.exceptionhandler.Error.Campo;
import com.spring.entrega.domain.exception.EntidadeNaoEncontradaException;
import com.spring.entrega.domain.exception.NegocioException;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) { 
		List<Campo> campos = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			campos.add(new Campo(nome,mensagem));
		}
		Error erro = new Error();
		erro.setStatus(status.value());
		erro.setDataHora(OffsetDateTime.now());
		erro.setTitulo("1 ou mais campos estão incompletos, faça o preenchiento correto e tente novamente");
		erro.setCampos(campos);
		return handleExceptionInternal(ex,erro, headers, status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Error erro = new Error();
		erro.setStatus(status.value());
		erro.setDataHora(OffsetDateTime.now());
		erro.setTitulo(ex.getMessage());
		return handleExceptionInternal(ex,erro, new HttpHeaders(), status,request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	ResponseEntity<Object> handleEntidadeNaoEncontrada(NegocioException ex, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		Error erro = new Error();
		erro.setStatus(status.value());
		erro.setDataHora(OffsetDateTime.now());
		erro.setTitulo(ex.getMessage());
		return handleExceptionInternal(ex,erro, new HttpHeaders(), status,request);
	}
}
