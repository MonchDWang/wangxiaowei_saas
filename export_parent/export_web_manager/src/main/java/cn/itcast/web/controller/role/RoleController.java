package cn.itcast.web.controller.role;

import cn.itcast.domain.Role;
import cn.itcast.service.RoleService;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping(value = "/system/role")
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "/list",name = "角色查询")
    public String findPageAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){
        PageInfo page = roleService.findAllPage(pageNum, pageSize, companyId);
        request.setAttribute("page",page);
        //到展示页面
        return "system/role/role-list";
    }


    @RequestMapping(value = "/toAdd",name = "角色新建页面")
    public String toAdd(){
        //带着所有部门到页面
        return "system/role/role-add";
    }

    @RequestMapping(value = "/edit",name = "角色新增")
    public String edit(Role role){

        role.setCompanyId(companyId);
        role.setCompanyName(companyName);
        if(UtilFuns.isEmpty(role.getId())){
            role.setId(UUID.randomUUID().toString().replace("-",""));
            roleService.save(role);
        }else{
            roleService.update(role);
        }

        //到查询
        return "redirect:/system/role/list.do";
    }


    @RequestMapping(value = "/toUpdate",name = "角色修改页面")
    public String toUpdate(String id){
        //根据id查角色
        Role role=roleService.findById(id);
        //查询所有角色
        request.setAttribute("role",role);
        //到查询
        return "system/role/role-update";
    }

    //根据id删除角色
    @RequestMapping(value = "/delete",name = "删除角色")
    public String delete(String id){

        roleService.delete(id);
        //到查询
        return "redirect:/system/role/list.do";
    }


    @RequestMapping(value = "/roleModule",name = "到分配菜单权限的页面")
    public String roleModule(String roleid){

        Role role = roleService.findById(roleid);
        request.setAttribute("role",role);
        //到页面
        return "system/role/role-module";
    }

    //更新角色权限
    @RequestMapping(value = "/updateRoleModule",name = "更新角色权限")
    public String updateRoleModule(String roleid,String moduleIds){
        //调用service完成先删后增角色的菜单权限
        roleService.updateRoleModule(roleid,moduleIds);
        //到页面
        return "redirect:/system/role/list.do";
    }


}
