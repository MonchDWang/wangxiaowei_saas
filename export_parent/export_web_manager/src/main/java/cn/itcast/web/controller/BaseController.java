package cn.itcast.web.controller;

import cn.itcast.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {

    public HttpServletRequest request;
    public HttpServletResponse response;
    public HttpSession session;

    //模拟登陆用户的企业id和企业名称
    public String companyId;
    public String companyName;
    public User user;

    @ModelAttribute
    public void init(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        this.request=request;
        this.response=response;
        this.session=session;
        // 获取当前登录用户的企业信息
        User user=(User)session.getAttribute("loginUser");
        if(user!=null){
            this.companyId=user.getCompanyId();
            this.companyName=user.getCompanyName();
        }
        this.user=user;

    }
}
