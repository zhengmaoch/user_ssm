package com.chang.web.controller;

import com.chang.domain.User;
import com.chang.exception.UserExistException;
import com.chang.service.UserService;
import com.chang.utils.Page;
import com.chang.utils.WebUtils;
import com.chang.web.model.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/Register")
    public String Register(){

        return "user/register";
    }

    @PostMapping("/register")
    public String register(Model model, UserForm form){

        // 表单服务器校验
        boolean b = form.validate();

        User user = new User();
        WebUtils.modelToEntity(form, user);
        user.setId(WebUtils.generateID());
        user.setCreatedtime(new Date());

        try {
            userService.register(user);
            model.addAttribute("message","恭喜您，注册成功！浏览器将在3秒后跳转。<meta http-equiv='refresh' content='3';url='${pageContext.request.contextPath}/index.jsp'>");
            return "message";
        } catch (UserExistException e) {
            form.getErrors().put("username","注册的用户名已存在！");
            return "register";
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("message","服务器出现未知错误！");
            return "message";
        }

    }

    @GetMapping("/login")
    public String login(){

        return "user/login";
    }

    @PostMapping("/login")
    public String login(Model model, @SessionAttribute("user") UserForm form){

        User user = userService.login(form.getUsername(), form.getPassword());

        if(user != null){
            model.addAttribute("user", user);
            return "redirect:/user/list";
        }

        model.addAttribute("message", "用户名或密码错误！浏览器将在3秒后跳转。<meta http-equiv='refresh' content='3';url='${pageContext.request.contextPath}/user/login'>");
        return "message";
    }

    @GetMapping("/logout")
    public String logout(Model model, SessionStatus sessionStatus, HttpSession session){

        UserForm form = (UserForm) session.getAttribute("user");
        // 清除HttpSession的session
        session.removeAttribute("user");
        // 只清除@SessionAttributes的session，不会清除HttpSession的数据
        sessionStatus.setComplete();

        model.addAttribute("message",form.getNickname() + "注销成功！浏览器将在3秒后跳转。<meta http-equiv='refresh' content='3';url='${pageContext.request.contextPath}/index.jsp'>");
        return "message";
    }

    @GetMapping("/list")
    public String list(Model model, Page page){

        int start = 0;
        int count = 10;

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

        List<UserForm> forms = new ArrayList<UserForm>();

        for (User user : users) {
            UserForm form = new UserForm();
            WebUtils.entityToModel(user, form);
            forms.add(form);
        }

        model.addAttribute("forms", forms);
        model.addAttribute("forms", forms);
//        request.setAttribute("page", page);
        return "/user/listUser";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id){

        userService.deleteUser(id);
        return "redirect:/user/list";
    }

    @DeleteMapping("/deleteAll")
    public String delete(){

        userService.deleteAllUser();
        return "redirect:/user/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable String id){

        User user = userService.getUser(id);
        UserForm form = new UserForm();
        WebUtils.entityToModel(user, form);
        model.addAttribute("user", form);
        return "redirect:/user/editUser";
    }

    @PutMapping("/update")
    public String update(Model model, UserForm form){

        boolean b = form.validate();

        if(!b){
            model.addAttribute("form", form);
            return "redirect:/user/edit";
        }

        User user = new User();
        WebUtils.modelToEntity(form, user);

        try {
            userService.updateUser(user);
            model.addAttribute("message","恭喜您，编辑成功！");
            return "redirect:/user/list";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "服务器出现未知错误！");
            return "message";
        }
    }

}
