package com.student.servlet;

import com.student.bean.Page;
import com.student.bean.Student;
import com.student.service.StudentService;
import com.student.service.StudentServiceImpl;
import com.student.util.redis.RedisUtil;
import com.student.util.redis.StudentToHash;
import com.student.util.servlet.RequestToBean;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(value = "/student",name = "StudentServlet")
public class UserServlet extends BaseServlet {

    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }
    @Autowired
    private StudentService studentService;

    private  final String STUDENT_REDIS="STUDENT_ID-";
    private  final String ID_SORTSET="ID_SORTSET";


    /**
     * 保存用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws ParseException
     */
    public void saveStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        Student student = studentService.getStudent(req);
        //添加一个UUID
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        student.setId(uuid);
        //设置状态
        student.setStatus(0);
        //更新
        studentService.updateStudent(student);
        //插入id表
        RedisUtil.setSortSet(ID_SORTSET,student.getAvgscore(),student.getId());
        req.setAttribute("view","/saveUser");
        req.getRequestDispatcher("/toBase").forward(req,resp);
    }

    /**
     * 删除用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws ParseException
     */
    public void removeStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        String id=req.getParameter("id");
        Student student = studentService.getStudent(id);
        student.setStatus(1);
        studentService.updateStudent(student);
        RedisUtil.removeSortSet(ID_SORTSET,id);
        req.getRequestDispatcher("/student?method=toUserForm").forward(req,resp);
    }

    /**
     * 更新用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws ParseException
     */
    public void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        Student student = studentService.getStudent(req);
        Map<String, String> map = StudentToHash.Student2Hash(student);
        RedisUtil.setAll(STUDENT_REDIS+student.getId(),map);
        //更新id表
        RedisUtil.setSortSet(ID_SORTSET,student.getAvgscore(),student.getId());
        req.getRequestDispatcher("/student?method=toUserForm").forward(req,resp);
    }

    /**
     * 获取根据传入的page，获取Page以及student，跳转到studentForm页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void toUserForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String pageNow = req.getParameter("page");
            if(pageNow==null){
                pageNow="1";
            }
            Page page = studentService.getPage(pageNow);
            List<String> idList=studentService.listIDList(Long.valueOf(page.getPageNow()));
            List<Student> list=studentService.listStudent(idList);
            req.setAttribute("list",list);
            req.setAttribute("page",page);
            req.setAttribute("view","/studentForm");
            req.getRequestDispatcher("/toBase").forward(req,resp);
    }

    /**
     * 跳转到添加用户页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void toSaveStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("view","/saveUser");
        req.getRequestDispatcher("/toBase").forward(req,resp);
    }

    /**
     * 根据ID查询Student,以及其生日格式化成字符串，跳转到更新用户页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws ParseException
     */
    public void toUpdateStudentView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        String id=req.getParameter("id");
        Student student = studentService.getStudent(id);
        DateFormat df=new SimpleDateFormat("yyyy-mm-dd");
        String format = df.format(student.getBirthday());
        req.setAttribute("dateStr",format);
        req.setAttribute("student", student);
        req.setAttribute("view","/updateUser");
        req.getRequestDispatcher("/toBase").forward(req,resp);
    }
}
