package com.student.controller;


import com.student.bean.Student;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class BaseController {

    @RequestMapping("/toBase")
    public String toBase(HttpServletRequest request, HttpServletResponse response){
        return request.getAttribute("view").toString();
    }

    @RequestMapping("/toSave")
    public String toSave(HttpServletRequest request, HttpServletResponse response){
        return "/saveUser";
    }

}
