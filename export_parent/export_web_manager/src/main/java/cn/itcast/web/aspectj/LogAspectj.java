package cn.itcast.web.aspectj;

import cn.itcast.domain.SysLog;
import cn.itcast.domain.User;
import cn.itcast.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Component
@Aspect
public class LogAspectj {

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Around(value = "execution(* cn.itcast.web.controller.*.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature ms =(MethodSignature)pjp.getSignature();
        Method method = ms.getMethod();
      if(method.isAnnotationPresent(RequestMapping.class)){
          RequestMapping annotation = method.getAnnotation(RequestMapping.class);

          // 对日志表插入数据
          SysLog sysLog = new SysLog();
          // 添加信息
          sysLog.setAction(annotation.name());
          sysLog.setMethod(method.getName());
          sysLog.setId(UUID.randomUUID().toString().replace("-",""));
          sysLog.setTime(new Date());
          sysLog.setIp(request.getRemoteAddr());
          User user =(User)session.getAttribute("loginUser");
          if(user!=null){
              sysLog.setCompanyId(user.getCompanyId());
              sysLog.setCompanyName(user.getCompanyName());
              sysLog.setUserName(user.getUserName());

          }

          sysLogService.save(sysLog);
        }

        return pjp.proceed(); //让被代理对象的方法执行
    }
}
