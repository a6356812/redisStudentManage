package com.student.util.servlet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class RequestToBean {
    public static <T> T request2Bean(HttpServletRequest request,   //返回值为随意的类型   传入参数为request 和该随意类型
                                     Class<T> beanClass) {
        try {
            T bean = beanClass.newInstance();   //实例化随意类型
            Enumeration en = request.getParameterNames();   //获得参数的一个列举
            while (en.hasMoreElements()) {         //遍历列举来获取所有的参数
                String name = (String) en.nextElement();
                String value = request.getParameter(name);
                BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
                BeanUtils.setProperty(bean, name, value);   //注意这里导入的是  import org.apache.commons.beanutils.BeanUtils;
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);  //如果错误 则向上抛运行时异常
        }
    }

}
