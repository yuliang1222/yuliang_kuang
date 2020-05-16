package com.example.demo.exceptions;


import com.example.demo.utils.CodeMsg;
import com.example.demo.utils.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	@ExceptionHandler(value=GlobalException.class)
	public Result<String> exceptionHandler(HttpServletRequest request, GlobalException e){
		e.printStackTrace();
		String exceptionName = e.getClass().getSimpleName();
//		if(e instanceof GlobalException) {
			GlobalException ex = (GlobalException)e;
			return Result.error(ex.getCm());
//		}else if(e instanceof BindException) {
//			BindException ex = (BindException)e;
//			List<ObjectError> errors = ex.getAllErrors();
//			ObjectError error = errors.get(0);
//			String msg = error.getDefaultMessage();
//			return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
//		}else if(e instanceof MethodArgumentNotValidException) {
//			MethodArgumentNotValidException ex = (MethodArgumentNotValidException)e;
//			List<ObjectError> errors = ex.getBindingResult().getAllErrors();
//			ObjectError error = errors.get(0);
//			String msg = error.getDefaultMessage();
//			return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
//		}
//		else {
//			return Result.error(CodeMsg.SERVER_ERROR);
//		}
	}
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public Result<String> exceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e){
		MethodArgumentNotValidException ex = (MethodArgumentNotValidException)e;
			List<ObjectError> errors = ex.getBindingResult().getAllErrors();
			ObjectError error = errors.get(0);
			String msg = error.getDefaultMessage();
			return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
	}

}
