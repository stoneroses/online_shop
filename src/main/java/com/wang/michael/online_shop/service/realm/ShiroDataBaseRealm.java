package com.wang.michael.online_shop.service.realm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.wang.michael.online_shop.exception.UserNotFound;
import com.wang.michael.online_shop.model.Permission;
import com.wang.michael.online_shop.model.Role;
import com.wang.michael.online_shop.model.User;
import com.wang.michael.online_shop.service.UserService;

/**
 * 自定义的指定Shiro验证用户登录的类
 * 
 * @author TCH
 *
 */
public class ShiroDataBaseRealm extends AuthorizingRealm {

    @Resource(name = "userService")
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 为当前登录的Subject授予角色和权限
     * 
     * @see 经测试:本例中该方法的调用时机为需授权资源被访问时
     * @see 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例未启用AuthorizationCache
     * @see web层可以有shiro的缓存，dao层可以配有hibernate的缓存（后面介绍）
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
        String email = (String) super.getAvailablePrincipal(principals);

        List<String> roles = new ArrayList<String>();
        List<String> permissions = new ArrayList<String>();

        // 从数据库中获取当前登录用户的详细信息
        User user = null;
        try {
            user = userService.getByEmail(email);
        } catch (UserNotFound e) {
            throw new AuthorizationException(e);
        }

        if (user != null) {
            // 实体类User中包含有用户角色的实体类信息
            if (user.getRoles() != null && user.getRoles().size() > 0) {
                // 获取当前登录用户的角色
                for (Role role : user.getRoles()) {
                    roles.add(role.getName());
                    // 实体类Role中包含有角色权限的实体类信息
                    if (role.getPermissions() != null && role.getPermissions().size() > 0) {
                        // 获取权限
                        for (Permission permission : role.getPermissions()) {
                            if (!StringUtils.isEmpty(permission.getName())) {
                                permissions.add(permission.getName());
                            }
                        }
                    }
                }
            }
        } else {
            throw new AuthorizationException();
        }

        // 为当前用户设置角色和权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);

        return info;

    }

    /**
     * 验证当前登录的Subject
     * 
     * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        // 获取基于用户名和密码的令牌
        // 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        // 从数据库中查询用户用信息
        User user = null;
        try {
            user = userService.getByEmail(token.getUsername());
            // 此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息
            return new SimpleAuthenticationInfo(user.getEmail(), user.getPassword(), getName());
        } catch (UserNotFound e) {
            // 没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
            return null;
        }
    }
}
