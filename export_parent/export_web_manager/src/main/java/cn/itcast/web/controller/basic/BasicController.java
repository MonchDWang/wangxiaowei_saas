package cn.itcast.web.controller.basic;

import cn.itcast.domain.cargo.Factory;
import cn.itcast.service.cargo.FactoryService;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping(value = "/baseinfo/factory")
public class BasicController extends BaseController{

    @Reference
    private FactoryService factoryService;

//pagehelper插件方式实现
    @RequestMapping(value = "/list",name = "厂家列表查询")
    public String findAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "3") int pageSize){
        PageInfo page = factoryService.findPageAll(pageNum, pageSize);
        request.setAttribute("page",page);
        return "baseinfo/factory/factory-list";
    }
    @RequestMapping(value = "/toAdd",name = "跳转到厂家的添加页面")
    public String toAdd(){
        return "baseinfo/factory/factory-add";
    }
    @RequestMapping(value = "/toUpdate",name = "带着数据到修改页面回显")
    public String toUpdate(String id){
        Factory factory=factoryService.findById(id);
        request.setAttribute("factory",factory);
        return "/baseinfo/factory/factory-update";
    }

    @RequestMapping(value = "/edit",name = "厂家保存/修改")
    public String edit(Factory factory){
        if(UtilFuns.isEmpty(factory.getId())){
            // 添加
            factory.setId(UUID.randomUUID().toString().replace("-",""));
            factory.setCreateTime(new Date());
            factory.setUpdateTime(new Date());
            factoryService.save(factory);
        }else {
            //修改
            factoryService.update(factory);
        }
        return "redirect:/baseinfo/factory/list.do";
    }
    @RequestMapping(value = "/delete",name = "厂家信息删除")
    public String delete(String id){
        factoryService.delete(id);
        return "redirect:/baseinfo/factory/list.do";
    }
}
