package com.example.demo.config;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    //获取授权信息 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("获取授权信息 授权");

        //授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //拿到当前登录对象
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();//当前登录对象

        simpleAuthorizationInfo.addStringPermission("user:add");//设置当前用户的权限，从数据库中读取

        simpleAuthorizationInfo.addStringPermission("user:add");

        return simpleAuthorizationInfo;
    }



    //获取身份验证信息 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("获取身份验证信息 认证");

        //用户名密码对比
        String username = "123456";
        String password = "123456";

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        System.out.println("username:"+usernamePasswordToken.getUsername());
        System.out.println("Password:"+usernamePasswordToken.getPassword());

        try {
            //数据库对比 验证帐号
            User byUsername = userService.findByUsername(usernamePasswordToken.getUsername());
            if (byUsername==null)return null;

            //shiro验证密码  密码MD5加密 MD5盐值加密
            return new SimpleAuthenticationInfo("",byUsername.getPassword(),"");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
