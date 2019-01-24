package com.chang.web.controller;

import com.chang.domain.User;
import com.chang.exception.UserExistException;
import com.chang.service.UserService;
import com.chang.utils.Page;
import com.chang.web.model.UserForm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/user")
@SessionAttributes("form")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/register")
    public String register(){

        return "user/register";
    }

    @PostMapping("/register")
    public String register(Model model, UserForm form){

        // 表单服务器校验
        boolean b = form.validate();

        User user = new User();
        BeanUtils.copyProperties(form, user);
        user.setId(UUID.randomUUID().toString().replace("-",""));
        user.setCreatedtime(new Date());

        try {
            userService.register(user);
            model.addAttribute("message","恭喜您，注册成功！浏览器将在3秒后跳转。<meta http-equiv='refresh' content='3;url=/user/index'>");
            return "message";
        } catch (UserExistException e) {
            form.getErrors().put("username","注册的用户名已存在！");
            return "user/register";
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
    public String login(Model model, UserForm form){

        User user = userService.login(form.getUsername(), form.getPassword());

        if(user != null){
            BeanUtils.copyProperties(user, form);
            model.addAttribute("form", form);
            return "redirect:/user/list";
        }

        model.addAttribute("message", "用户名或密码错误！浏览器将在3秒后跳转。<meta http-equiv='refresh' content='3;url=/user/user/login'>");
        return "message";
    }

    @GetMapping("/logout")
    public String logout(Model model, SessionStatus sessionStatus, HttpSession session){

        UserForm form = new UserForm();
        if(session.getAttribute("form") != null){
            form = (UserForm) session.getAttribute("form");
            // 清除HttpSession的session
            session.removeAttribute("form");

            // 只清除@SessionAttributes的session，不会清除HttpSession的数据
            sessionStatus.setComplete();
        }

        String nikeName = form.getNickname() == null ? "" : form.getNickname();
        model.addAttribute("message",nikeName + "注销成功！浏览器将在3秒后跳转。<meta http-equiv='refresh' content='3;url=/user/index'>");
        return "message";
    }

    @RequestMapping("/list")
    public String list(@RequestParam(value = "start", required = false, defaultValue = "1") Integer start, @RequestParam(value = "count", required = false, defaultValue = "10") Integer count) {

        return "redirect:/user/list/"+start+"/"+ count;
    }

    @RequestMapping("/list/{start}/{count}")
    public String list(Model model, @PathVariable(value = "start", required = false) Integer start, @PathVariable(value = "count", required = false) Integer count) {

        Page page = new Page(start == null ? 1 : start, count == null ? 10 : count);

        List<User> users = userService.list((page.getStart() - 1) * page.getCount(),page.getCount());
        int total = userService.getTotal();
        page.setTotal(total);

        List<UserForm> forms = new ArrayList<UserForm>();

        for (User user : users) {
            UserForm form = new UserForm();
            BeanUtils.copyProperties(user, form);
            forms.add(form);
        }

        model.addAttribute("users", forms);
        model.addAttribute("page", page);
        return "/user/listUser";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id){

        userService.deleteUser(id);
        return "redirect:/user/list";
    }

    @GetMapping("/deleteAll")
    public String delete(){

        userService.deleteAllUser();
        return "redirect:/user/list/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable String id){

        User user = userService.getUser(id);
        UserForm form = new UserForm();
        BeanUtils.copyProperties(user, form);
        model.addAttribute("user", form);
        return "user/editUser";
    }

    @PutMapping("/update")
    public String update(Model model, UserForm form){

        boolean b = form.validate();

        if(!b){
            model.addAttribute("form", form);
            return "redirect:/user/edit";
        }

        User user = new User();
        BeanUtils.copyProperties(form, user);

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
