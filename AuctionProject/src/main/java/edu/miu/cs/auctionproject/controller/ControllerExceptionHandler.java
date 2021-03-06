package edu.miu.cs.auctionproject.controller;



import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler implements ErrorController {

    public static final String DEFAULT_ERROR_VIEW = "error";


    @ExceptionHandler(FileNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest req, FileNotFoundException exception) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("secured/seller/FileNotFound");
        return mav;
    }



    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "error/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/error-500";
            }
        }
        return "error/error";
    }



}
