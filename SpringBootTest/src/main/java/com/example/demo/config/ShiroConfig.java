package com.example.demo.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean 过滤器工厂
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager getDefaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager);

        /*
        * 添加shiro 的过滤器
        * anon：无需认证可才能访问
        * authc:必须认证才能访问
        * user:必须拥有记住我功能才能访问
        * perms：必须拥有某个资源的权限才能访问
        * roles：拥有某个角色权限才能访问
        * */
        //设置拦截过滤链
        Map<String,String> map = new LinkedHashMap<>();
        map.put("/index/index","anon");
        map.put("/index/add","authc");

        //授权 未授权跳转到401界面。
        map.put("/index/update","perms[user:add]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        //拦截成功时跳转到的登录界面
        shiroFilterFactoryBean.setLoginUrl("/index/login");

        //未授权跳转到指定界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/index/noAuth");
        return shiroFilterFactoryBean;
    }

    //DefaultWebSecurityManager 安全管理器
    @Bean(name = "getDefaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shiroRealm);
        return defaultWebSecurityManager;
    }

    //创建自定义 Realm 对象
    @Bean(name = "shiroRealm")
    public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }
}
