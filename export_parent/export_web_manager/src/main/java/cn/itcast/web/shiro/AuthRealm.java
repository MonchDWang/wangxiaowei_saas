package cn.itcast.web.shiro;

import cn.itcast.domain.Module;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

public class AuthRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    /*
    * principalCollection：所有的安全数据
    *
    * AuthorizationInfo：用户的所有权限
    * */
    @Override  // 授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 1 获取到user对象
        User user =(User)principalCollection.getPrimaryPrincipal();
        //2 查询该对象的所有权限
        List<Module> modules = userService.FindByUserModule(user);
        HashSet<String> set = new HashSet<>();
        for (Module module : modules) {
            set.add(module.getName());
        }
        System.out.println(set);
        //3 将权限封装给AuthorizationInfo返回
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(set);
        return info;
    }

    @Override // 认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1 获取用户的邮箱和密码
        UsernamePasswordToken upToken=(UsernamePasswordToken)authenticationToken;
        String email = upToken.getUsername();
        String password = new String(upToken.getPassword());
        // 2 带着邮箱去数据库查询用户
        User user = userService.findByEmail(email);
        // 3 输入的密码和数据库的密码对比
                //对象
                //数据库的密码
                // 当前realm的名称
        if(user!=null){
            return new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        }
        return null; //抛异常
    }
}
