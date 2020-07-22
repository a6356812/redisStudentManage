package com.student.service;

import com.student.bean.Page;
import com.student.bean.Student;
import com.student.servlet.BaseServlet;
import com.student.util.redis.RedisUtil;
import com.student.util.redis.StudentToHash;
import com.student.util.servlet.RequestToBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StudentServiceImpl implements StudentService{
    private  final String ID_SORTSET="ID_SORTSET";
    private  final String STUDENT_REDIS="STUDENT_ID-";
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    /**
     * 1.获取Set类型的idList
     * 2.转成List并返回
     * @param pageNow
     * @return
     */
    @Override
    public List<String> listIDList(Long pageNow) {
            //1
            Long size=10L;
            Set<String> sortSet = RedisUtil.getSortSet(ID_SORTSET, (pageNow - 1) * size, (pageNow * size) - 1);
            //2
            List<String> idList=new LinkedList(sortSet);
            logger.info("开始查询idList--入参pagenow:"+pageNow+"出参idList.size---"+idList.size());
        return idList;
    }

    /**1.根据idList中的值查询获得Student
     * 2.判断状态是否为已删除，若为未删除状态则加入list中
     * 获取所有学生
     * @param idList
     * @return
     */
    @Override
    public List<Student> listStudent(List<String> idList) {

        List<Student> list=new LinkedList();
        idList.forEach(item->{
            try {
                //1
                Student student=getStudent(item);
                //2
                if(student!=null&&student.getStatus()==0)
                list.add(student);
            } catch (ParseException e) {
                logger.info("listStudentidListforEach 错误 ----id="+item+"----"+e.getMessage(),e);
            }
        });
        logger.info("入参"+idList+"开始查询listStudent--出参idListsize:"+idList.size());
        return list;
    }

    /**
     * 1.获取学生总数
     * 2.计算总页数
     * 3.构造page
     * @param page
     * @return
     */
    @Override
    public Page getPage(String page) {
        //1
        Long total = RedisUtil.getSortSetSize(ID_SORTSET);
        Long size = 10L;
        Long totalPage=0L;
        //非空判断
        if(total==null){
            total=0L;
        }
        //2
        if (total/size==0){
            totalPage = total/size;
        }else {
            totalPage = total/size + 1;
        }
        //非空判断
        if(page==null){
            page="1";
        }
        //3
        Integer currentPage=Integer.valueOf(page);
        logger.info("入参为---"+page,"出参total/totalpage为"+total+"/"+totalPage);
        Page page1=new Page();
        page1.setTotal(Math.toIntExact(total));
        page1.setPageNow(currentPage);
        page1.setPageNum(Math.toIntExact(totalPage));
        return page1;
    }

    /**
     * 1.在HttpServletRequest中获得Student
     * 2.构造出正确格式的birthday并赋值给student
     * @param req
     * @return
     * @throws ParseException
     */
    @Override
    public Student getStudent(HttpServletRequest req) throws ParseException {
        //1
        Student student= RequestToBean.request2Bean(req,Student.class);
        //2
        DateFormat df=new SimpleDateFormat("yyyy-mm-dd");
        String birthday = req.getParameter("birthday");
        student.setBirthday(df.parse(birthday));
        logger.info("getStudent--入参"+student.getName()+"-----出参student:"+student.getName());
        return student;
    }

    /**
     * 1.将Student转换为Map
     * 2.更新Student
     * @param student
     */
    @Override
    public void updateStudent(Student student) {
        //1
        Map<String, String> map = StudentToHash.Student2Hash(student);
        //2
        RedisUtil.setAll(STUDENT_REDIS+student.getId(),map);
    }

    /**
     * 根据id查询并返回Student
     * @param id
     * @return
     * @throws ParseException
     */
    @Override
    public Student getStudent(String id) throws ParseException {
        Student student=StudentToHash.Map2Student(RedisUtil.getAll(STUDENT_REDIS+id));
        return student;
    }

}
