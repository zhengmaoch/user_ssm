package com.chang.web.controller;

import com.chang.domain.User;
import com.chang.exception.UserExistException;
import com.chang.service.UserService;
import com.chang.utils.MD5Utils;
import com.chang.utils.WebUtils;
import com.chang.web.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/toRegister")
    public String toRegister(){

        return "user/register";
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response){

        UserModel form = WebUtils.requestToModel(request, UserModel.class);
        boolean b = form.validate();

        User user = new User();
        WebUtils.modelToEntity(form, user);
        user.setId(WebUtils.generateID());
        user.setCreatedtime(new Date());

        try {
            userService.register(user);
            request.setAttribute("message","恭喜您，注册成功！浏览器将在3秒后跳转。<meta http-equiv='refresh' content='3;url="+request.getContextPath()+"/index.jsp'>");
            return "message";
        } catch (UserExistException e) {
            form.getErrors().put("username","注册的用户名已存在！");
            return "register";
        } catch (Exception e){
            e.printStackTrace();
            request.setAttribute("message","服务器出现未知错误！");
            return "message";
        }

    }

    @RequestMapping("/toLogin")
    public String toLogin(){

        return "user/login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.login(username, password);

        if(user != null){
            request.getSession().setAttribute("user", user);
            return "redirect:/user/list";
        }

        request.setAttribute("message", "用户名或密码错误！浏览器将在3秒后跳转。<meta http-equiv='refresh' content='3;url="+request.getContextPath()+"/user/toLogin'>");
        return "message";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.removeAttribute("user");
        }

        request.setAttribute("message","注销成功！浏览器将在3秒后跳转。<meta http-equiv='refresh' content='3;url="+request.getContextPath()+"/index.jsp'>");
        return "message";
    }

    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response){

//        int start = 0;
//        int count = 10;
//
//        try {
//            start = Integer.parseInt(request.getParameter("page.start"));
//            count = Integer.parseInt(request.getParameter("page.count"));
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Page page = new Page(start, count);

        List<User> users = userService.getAllUser();
//        List<User> users = userService.list(page.getStart(),page.getCount());
//        int total = userService.getTotal();
//        page.setTotal(total);
        List<UserModel> userModels = new ArrayList<UserModel>();

        for (User user : users) {
            UserModel userModel = new UserModel();
            WebUtils.entityToModel(user, userModel);
            userModels.add(userModel);
        }

        request.setAttribute("userModels", userModels);
//        request.setAttribute("page", page);
        return "/user/listUser";
    }

    @RequestMapping("/delete")
    public String delete(String id){

        userService.deleteUser(id);
        return "redirect:/user/list";
    }

    @RequestMapping("/deleteAll")
    public String delete(){

        userService.deleteAllUser();
        return "redirect:/user/list";
    }

    @RequestMapping("/edit")
    public ModelAndView edit(String id){

        ModelAndView mav = new ModelAndView("user/editUser");
        User user = userService.getUser(id);
        UserModel userModel = new UserModel();
        WebUtils.entityToModel(user, userModel);
        mav.addObject("user",userModel);
        return mav;
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response){

        UserModel form = WebUtils.requestToModel(request, UserModel.class);
        boolean b = form.validate();

        if(!b){
            request.setAttribute("form", form);
            return "redirect:/user/edit";
        }

        User user = new User();
        WebUtils.modelToEntity(form, user);


        try {
            userService.updateUser(user);
            request.setAttribute("message","恭喜您，编辑成功！");
            return "redirect:/user/list";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "服务器出现未知错误！");
            return "message";
        }
    }

}
