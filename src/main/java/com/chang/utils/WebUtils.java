package com.chang.utils;

import com.chang.domain.User;
import com.chang.web.model.UserModel;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

public class WebUtils {

    public static <T> T requestToModel(HttpServletRequest request, Class<T> beanClass){
        try {
            T bean = beanClass.newInstance();

            Enumeration e = request.getParameterNames();
            while (e.hasMoreElements()){
                String name = (String) e.nextElement();
                String value = request.getParameter(name);
                BeanUtils.setProperty(bean, name,value);
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void entityToModel(User entity, UserModel model) {
        try {
            BeanUtils.copyProperties(model, entity);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static void modelToEntity(UserModel model, User entity) {
        try {
            BeanUtils.copyProperties(entity, model);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static String generateID() {
        return UUID.randomUUID().toString();
    }
}
