package cn.itcast.web.shiro;

import cn.itcast.utils.Encrypt;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher{

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
       // 1 获取用户书写的密码
        UsernamePasswordToken upToken=(UsernamePasswordToken)token;
        String password =new String(upToken.getPassword());
        String email = upToken.getUsername();
        String MD5Password = Encrypt.md5(password, email);
        //2 获取数据库的密码
        String dataPassword =(String)info.getCredentials();

        //3 比较
        return MD5Password.equals(dataPassword);
    }
}
