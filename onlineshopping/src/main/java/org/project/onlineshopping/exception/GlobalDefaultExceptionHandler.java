package org.project.onlineshopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	/*
	 * For Handle Global Exception
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException(){
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "This page not constructed!!!!!");
		mv.addObject("errorDescription", "The page you are looking for not available now!!!!!");
		mv.addObject("title", "404 Error Page");
		
		return mv;
	}
	
	/*
	 * For Handle Custom Exception
	 */
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException(){
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Product not avaiable!!!!!");
		mv.addObject("errorDescription", "The Product you are looking for not available right now!!!!!");
		mv.addObject("title", " product unavailable");
		
		return mv;
	}
	
	/*
	 * For Handle Generalize Exception
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex){
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Contact your Administrator!!!!!");
		
		//Only for debugging my application printStrce() message show on the web page
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
									//here ex.toString()
		mv.addObject("errorDescription", sw.toString());
		mv.addObject("title", " error");
		
		return mv;
	}
}
