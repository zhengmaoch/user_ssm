package com.chang.web.controller;

import com.chang.domain.User;
import com.chang.exception.UserExistException;
import com.chang.service.UserService;
import com.chang.utils.Page;
import com.chang.utils.WebUtils;
import com.chang.web.model.UserModel;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.traversal.NodeFilter;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response){

        UserModel form = WebUtils.requestToModel(request, UserModel.class);
        boolean b = form.validate();

        User user = new User();
        WebUtils.copyModel(form, user);
        user.setId(WebUtils.generateID());
        user.setCreatedtime(new Date());

        try {
            userService.register(user);
            request.setAttribute("message","恭喜您，注册成功！浏览器将在3秒后跳转。<meta http-equiv='refresh' content='3;url="+request.getContextPath()+"/index.jsp'>");
            return "redirect:message";
        } catch (UserExistException e) {
            form.getErrors().put("username","注册的用户名已存在！");
            return "redirect:register";
        } catch (Exception e){
            e.printStackTrace();
            request.setAttribute("message","服务器出现未知错误！");
            return "redirect:message";
        }

    }

    @RequestMapping("/login")
    public String Login(){
        return "login";
    }

    @RequestMapping("/doLogin")
    public String DoLogin(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.login(username, password);

        if(user != null){
            request.getSession().setAttribute("user", user);

            return "redirect:listUser";
        }

        request.setAttribute("message", "用户名或密码错误！浏览器将在3秒后跳转。<meta http-equiv='refresh' content='3;url="+request.getContextPath()+"/servlet/LoginUIServlet'>");
        return "redirect:message";
    }

    @RequestMapping("/logout")
    public String Logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.removeAttribute("user");
        }

        request.setAttribute("message","注销成功！浏览器将在3秒后跳转。<meta http-equiv='refresh' content='3;url="+request.getContextPath()+"/index.jsp'>");
        return "redirect:message";
    }

    @RequestMapping("/listUser")
    public String List(HttpServletRequest request, HttpServletResponse response){

        int start = 0;
        int count = 10;

        try {
            start = Integer.parseInt(request.getParameter("page.start"));
            count = Integer.parseInt(request.getParameter("page.count"));
        } catch (Exception e){
            e.printStackTrace();
        }

        Page page = new Page(start, count);

        List<User> users = userService.list(page.getStart(),page.getCount());
        int total = userService.getTotal();
        page.setTotal(total);
        request.setAttribute("users", users);
        request.setAttribute("page", page);
        return "listUser";
    }

    @RequestMapping("/deleteUser")
    public String Delete(String id){

        userService.deleteUser(id);
        return "redirect:listUser";
    }

    @RequestMapping("/deleteAllUser")
    public String Delete(){

        userService.deleteAllUser();
        return "redirect:listUser";
    }

    @RequestMapping("/editUser")
    public ModelAndView Edit(String id){

        ModelAndView mav = new ModelAndView("editUser");
        User user = userService.getUser(id);
        mav.addObject("user",user);
        return mav;
    }

    @RequestMapping("/updateUser")
    public String Update(HttpServletRequest request, HttpServletResponse response){

        UserModel form = WebUtils.requestToModel(request, UserModel.class);
        boolean b = form.validate();

        if(!b){
            request.setAttribute("form", form);
            return "redirect:editUser";
        }

        User user = new User();
        WebUtils.copyModel(form, user);

        try {
            userService.updateUser(user);
            request.setAttribute("message","恭喜您，编辑成功！");
            return "redirect:listUser";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "服务器出现未知错误！");
            return "message";
        }
    }

}