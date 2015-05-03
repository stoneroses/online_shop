package com.wang.michael.online_shop.web.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleMissingResource(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error/404");
        modelAndView.addObject("pageTitle", "Page not found.");
        return modelAndView;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView exception(Exception exception, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error/500");
        modelAndView.addObject("pageTitle", "Errors!");
        modelAndView.addObject("errorMessage", ExceptionUtils.getRootCause(exception) + " - " + ExceptionUtils.getRootCauseMessage(exception));
        modelAndView.addObject("detailErrorMessage", ExceptionUtils.getStackTrace(exception));
        return modelAndView;
    }
}
