package com.student.util;


import com.student.servlet.BaseServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
    @ExceptionHandler(value = Exception.class)
    public void exception(HttpServletRequest req, HttpServletResponse resp,Exception e){
        try {
            logger.error("ExceptionAdvice message:",e.getMessage(), e);
            req.setAttribute("view","/500");
            req.getRequestDispatcher("/toBase").forward(req,resp);
        } catch (ServletException ex) {
            logger.error("ExceptionAdvice EXCEPTION",e.getMessage(), e);
        } catch (IOException ex) {
            logger.error("ExceptionAdvice EXCEPTION ex",ex.getMessage(), ex);
        }
    }
}
