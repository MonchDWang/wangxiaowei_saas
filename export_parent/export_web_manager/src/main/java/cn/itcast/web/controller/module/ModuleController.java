package cn.itcast.web.controller.module;

import cn.itcast.domain.Module;
import cn.itcast.service.ModuleService;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/system/module")
public class ModuleController extends BaseController{

    @Autowired
    private ModuleService moduleService;


    @RequestMapping(value = "/list",name = "菜单查询")
    @RequiresPermissions("模块管理")
    public String findPageAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){
        PageInfo page = moduleService.findAllPage(pageNum, pageSize);
        request.setAttribute("page",page);
        //到展示页面
        return "system/module/module-list";
    }


    @RequestMapping(value = "/toAdd",name = "菜单新建页面")
    public String toAdd(){
        List<Module> list=moduleService.findAllModule();
        request.setAttribute("menus",list);
        //带着所有菜单功能名称到页面
        return "system/module/module-add";
    }

    @RequestMapping(value = "/edit",name = "菜单新增")
    public String edit(Module module){

        if(UtilFuns.isEmpty(module.getId())){
            module.setId(UUID.randomUUID().toString().replace("-",""));
            moduleService.save(module);
        }else{
            moduleService.update(module);
        }

        //到查询
        return "redirect:/system/module/list.do";
    }


    @RequestMapping(value = "/toUpdate",name = "菜单修改页面")
    public String toUpdate(String id){
        //根据id查菜单
        Module module=moduleService.findById(id);
        //查询所有菜单
        request.setAttribute("module",module);
        List<Module> list=moduleService.findAllModule();
        request.setAttribute("menus",list);
        //到查询
        return "system/module/module-update";
    }

    //根据id删除菜单
    @RequestMapping(value = "/delete",name = "删除菜单")
    public String delete(String id){

        moduleService.delete(id);
        //到查询
        return "redirect:/system/module/list.do";
    }

    // 获取所有的菜单数据--返回json数据
    // @responseBody：将对象转成json返回
    /*[
        { id:1, pId:0, name:"SaaS管理"}, map
        { id:111, pId:11, name:"随意勾选 1-1-1",checked:true} map
      ]

      根据角色的菜单去做回显勾选 checked:true
      #根据点击的角色id去查询该角色具有的权限
     #去和全部的菜单做对比
     #如果一致，在后面添加"ckecked":true
    */
    @RequestMapping(value = "/initModuleData",name = "获取所有菜单数据")
    public @ResponseBody List initModuleData(String id){

        // 0 查询id角色的所有权限 List<Module>（中间表）
        List<Module> list=moduleService.findRoleModule(id);  //4
        // 1 查询所有的菜单数据list
        List<Module> moduleList = moduleService.findAllModule(); //28
        // 2 组装指定的格式 {id：value,pid:value,name:value}
        List jsonList=new ArrayList();
        for (Module module : moduleList) {
            // 创建map组装指定的数据
            HashMap map = new HashMap();
            map.put("id",module.getId());
            map.put("pId",module.getParentId());
            map.put("name",module.getName());
            //判断
            if(list.contains(module)){
                map.put("checked",true);
            }
            // 放入jsonList中
            jsonList.add(map);
        }
        // 3 返回
        return jsonList;
    }

}
