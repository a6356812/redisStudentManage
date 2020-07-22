package com.student.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(BaseServlet.class);
    /**
     * 跳转到serlvet的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName=request.getParameter("method");
        Method method=null;
        try{
            method=this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
        }catch (Exception e){
            throw new RuntimeException("方法不存在");
        }
        try {
            method.invoke(this,request,response);
        } catch (Exception e) {
            logger.info("BASE EXCEPTION--method="+method.getName());
            request.setAttribute("view","/500");
            request.getRequestDispatcher("/toBase").forward(request,response);
        }
    }

}
