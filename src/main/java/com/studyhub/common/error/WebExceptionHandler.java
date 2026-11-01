package com.studyhub.common.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class WebExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler({ DataAccessResourceFailureException.class, CannotCreateTransactionException.class })
    public String handleDatabaseConnectionFailure(Exception exception, HttpServletRequest request, HttpServletResponse response, Model model) {
        log.error("Database connection failed. path={}", request.getRequestURI(), exception);
        return errorView(HttpStatus.SERVICE_UNAVAILABLE, "The database is unavailable", "Check the PostgreSQL status and connection settings.", request, response, model);
    }

    @ExceptionHandler(DataAccessException.class)
    public String handleDatabaseFailure(DataAccessException exception, HttpServletRequest request, HttpServletResponse response, Model model) {
        log.error("Database request failed. path={}", request.getRequestURI(), exception);
        return errorView(HttpStatus.INTERNAL_SERVER_ERROR, "The data could not be read", "Confirmed that the database scripts have been applied.", request, response, model);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNotFound(NoResourceFoundException exception, HttpServletRequest request, HttpServletResponse response, Model model) {
        return errorView(HttpStatus.NOT_FOUND, "Page not found", "Check that the URL is correct.", request, response, model);
    }

    @ExceptionHandler(Exception.class)
    public String handleUnexpectedFailure(Exception exception, HttpServletRequest request, HttpServletResponse response, Model model) {
        log.error("Unhandled request error. path={}", request.getRequestURI(), exception);
        return errorView(HttpStatus.INTERNAL_SERVER_ERROR, "The request could not be completed", "Please try again later.", request, response, model);
    }

    private String errorView(HttpStatus status, String title, String message, HttpServletRequest request, HttpServletResponse response, Model model) {
        response.setStatus(status.value());
        model.addAttribute("errorInfo", new ErrorViewModel(status.value(), title, message, request.getRequestURI()));
        return "error/common";
    }
}