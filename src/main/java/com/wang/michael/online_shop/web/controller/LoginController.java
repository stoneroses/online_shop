package com.wang.michael.online_shop.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wang.michael.online_shop.model.User;

@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    public String login(@RequestParam(value = "username", required = true) String email,
            @RequestParam(value = "password", required = true) String password) {
        // 获取当前的Subject
        Subject curUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
        token.setRememberMe(true);
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到ShiroDbRealm.doGetAuthenticationInfo()方法中
            curUser.login(token);
            return "redirect:/";
        } catch (AuthenticationException e) {
            // 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            token.clear();
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(User user, HttpSession session, HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        return "redirect:/";
    }

}
