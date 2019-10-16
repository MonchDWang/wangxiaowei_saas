package cn.itcast.web.controller;

import cn.itcast.domain.Module;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController extends BaseController{

    @Autowired
    private UserService userService;
    /*
    * 登录校验
    *      成功 home/main显示该用户信息
    *      失败 login.jsp显示错误信息
    *
    * */
	/*@RequestMapping("/login")
	public String login(String email,String password) {
        //1 判断用户名(Email)和密码是否为空
                        // 没写：继续登录页面
        if(UtilFuns.isEmpty(email) || UtilFuns.isEmpty(password)){
            // 到登录页面
            return "forward:login.jsp";
        }
        //2 带着用户名去查询该用户
        User user=userService.findByEmail(email);
        //3 将输入的密码和该用户的密码匹配
        if(user==null || !user.getPassword().equals(password)){
            // 失败：到登录页面显示错误信息
            request.setAttribute("error","用户名或者密码错误");
            return "forward:login.jsp";
        }

        // 成功："home/main"
        List<Module> moduleList=userService.FindByUserModule(user);
        session.setAttribute("modules",moduleList);

        session.setAttribute("loginUser",user);
	    return "home/main";
	}*/


    @RequestMapping("/login")
    public String login(String email,String password) {
        try {

            // 1 获取Subject对象
            Subject subject = SecurityUtils.getSubject();
            // 2 存储用户邮箱和密码
            UsernamePasswordToken upToken = new UsernamePasswordToken(email, password);
            // 3 调用realm进行用户认证
            subject.login(upToken);
            // 4 从shiro中获取用户
            User user =(User)subject.getPrincipal();
            session.setAttribute("loginUser",user);
            // 5 获取用户权限
            List<Module> moduleList=userService.FindByUserModule(user);
            session.setAttribute("modules",moduleList);

            return "home/main";

        }catch (Exception e){

            // 失败：到登录页面显示错误信息
            request.setAttribute("error","用户名或者密码错误");
            return "forward:login.jsp";
        }

    }

    //退出
    @RequestMapping(value = "/logout",name="用户登出")
    public String logout(){
        SecurityUtils.getSubject().logout();   //登出
        return "forward:login.jsp";
    }

    @RequestMapping("/home")
    public String home(){

	    return "home/home";
    }
}
