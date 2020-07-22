package com.student.service;

import com.student.bean.Page;
import com.student.bean.Student;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface StudentService {
    /**
     * 根据传入的页数，返回所有idlist
     * @param pageNow
     * @return
     */
    public List<String> listIDList(Long pageNow);

    /**
     * 根据传入的idlist，查询并返回student list
     * @param idList
     * @return
     */
    public List<Student>listStudent(List<String> idList);

    /**
     * 根据传入的当前页数，返回一个Page对象
     * @param page
     * @return
     */
    public Page getPage(String page);

    /**
     * 从HttpServletRequest获取Student并返回
     * @param req
     * @return
     * @throws ParseException
     */
    public Student getStudent(HttpServletRequest req) throws ParseException;

    /**
     * 根据传入的Student更新Student
     * @param student
     */
    public void updateStudent(Student student);

    /**
     * 根据传入的id，返回其Student对象
     * @param id
     * @return
     * @throws ParseException
     */
    public Student getStudent(String id) throws ParseException;
}
