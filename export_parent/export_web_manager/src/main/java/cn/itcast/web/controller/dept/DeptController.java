package cn.itcast.web.controller.dept;

import cn.itcast.domain.Dept;
import cn.itcast.service.DeptService;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/system/dept")
public class DeptController extends BaseController{

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/list",name = "部门查询")
    public String findPageAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){
        PageInfo page = deptService.findPageAll(pageNum, pageSize, companyId);
        request.setAttribute("page",page);
        //到展示页面
        return "system/dept/dept-list";
    }


    @RequestMapping(value = "/toAdd",name = "部门新建页面")
    public String toAdd(){
        // 根据企业id获取所有部门
        List<Dept> list=deptService.findAllDept(companyId);
        request.setAttribute("deptList",list);
        //到页面
        return "system/dept/dept-add";
    }

    @RequestMapping(value = "/edit",name = "部门新增")
    public String edit(Dept dept){

        if(UtilFuns.isEmpty(dept.getId())){
            dept.setId(UUID.randomUUID().toString().replace("-",""));
            dept.setCompanyId(companyId);
            dept.setCompanyName(companyName);
            deptService.save(dept);
        }else{
            deptService.update(dept);
        }

        //到查询
        return "redirect:/system/dept/list.do";
    }


    @RequestMapping(value = "/toUpdate",name = "部门修改页面")
    public String toUpdate(String id){
        //根据id查部门
        Dept dept=deptService.findById(id);
        //查询所有部门
        List<Dept> list=deptService.findAllDept(companyId);
        request.setAttribute("dept",dept);
        request.setAttribute("deptList",list);
        //到查询
        return "system/dept/dept-update";
    }

    //根据id删除部门
    @RequestMapping(value = "/delete",name = "删除部门")
    public String delete(String id){

        deptService.delete(id);
        //到查询
        return "redirect:/system/dept/list.do";
    }


}
