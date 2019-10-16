package cn.itcast.web.controller.syslog;

import cn.itcast.service.SysLogService;
import cn.itcast.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/system/log")
public class SysLogController extends BaseController{

    @Autowired
    private SysLogService sysLogService;
    
    @RequestMapping(value = "/list",name = "日志查询")
    public String findPageAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){
        PageInfo page = sysLogService.findAll(pageNum, pageSize, companyId);
        request.setAttribute("page",page);
        //到展示页面

        return "system/log/log-list";
    }


}
