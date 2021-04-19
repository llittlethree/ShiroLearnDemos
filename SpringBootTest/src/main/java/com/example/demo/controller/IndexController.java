package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("index","hello index");
        return "Index/index";
    }

    @RequestMapping("/add")
    public String add(Model model){
        model.addAttribute("add","hello add");
        return "Index/add";
    }

    @RequestMapping("/update")
    public String update(Model model){
        model.addAttribute("update","hello update");
        return "Index/update";
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("login","hello login");
        return "login";
    }

    @RequestMapping("/noAuth")
    public String noAuth(Model model){
        model.addAttribute("noAuth","hello noAuth");
        return "noAuth";
    }

    @RequestMapping("/doLogin")
    public String doLogin(Model model,String username ,String password){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);

        try {
            //执行登录方法，无异常则登录成功，
            subject.login(usernamePasswordToken);
            model.addAttribute("e","登录成功");
            return "index/index";
        } catch (UnknownAccountException e ){
            //未知帐号，帐号未注册，找不到对应的帐号
            //e.printStackTrace();
            model.addAttribute("e","帐号密码不正确");
            return "login";
        }catch (IncorrectCredentialsException e ){
            //密码不正确
            //e.printStackTrace();
            model.addAttribute("e","密码不正确");
            return "login";
        }catch (LockedAccountException e ) {
            //帐号被锁定，无法登录
            //e.printStackTrace();
            model.addAttribute("e","帐号被锁定，无法登录");
            return "login";
        }

    }


//----------------------
}
