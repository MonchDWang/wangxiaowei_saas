package cn.itcast.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyException implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        if(ex instanceof UnauthorizedException){
            return new ModelAndView("forward:/unauthorized.jsp");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMsg","不好意思，程序员出错了...");
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
