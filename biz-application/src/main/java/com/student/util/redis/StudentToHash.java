package com.student.util.redis;

import com.student.bean.Student;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class StudentToHash {

    public static Map<String, String> Student2Hash(Student student){
        DateFormat df=new SimpleDateFormat("yyyy-mm-dd");
        Map<String,String> map=new HashMap();
        map.put("id",student.getId().toString());
        if(student.getName()!=null){
            map.put("name",student.getName());
        }
        if(student.getAvgscore()!=null){
            map.put("avgscore",student.getAvgscore().toString());
        }
        if(student.getBirthday()!=null){
            map.put("birthday",df.format(student.getBirthday()));
        }
        if(student.getBirthdayString()!=null){
            map.put("birthdayString",student.getBirthdayString());
        }
        if(student.getDescription()!=null){
            map.put("description",student.getDescription());
        }
        if(student.getStatus()!=null){
            map.put("status", String.valueOf(student.getStatus()));
        }
        return map;
    }

    public static Student Map2Student(Map<String,String> map) throws ParseException {
        DateFormat df=new SimpleDateFormat("yyyy-mm-dd");
        Student student=new Student();
        student.setId(map.get("id"));
        if(map.get("name")!=null){
            student.setName(map.get("name"));
        }
        if(map.get("birthday")!=null){
            student.setBirthday(df.parse(map.get("birthday")));
        }
        if(map.get("birthdayString")!=null){
            student.setBirthdayString(map.get("birthdayString"));
        }
        if(map.get("description")!=null){
            student.setDescription(map.get("description"));
        }
        if(map.get("avgscore")!=null){
            student.setAvgscore(Integer.valueOf(map.get("avgscore")));
        }
        if(map.get("status")!=null){
            student.setStatus(Integer.valueOf(map.get("status")));
        }
        return student;
    }
}
