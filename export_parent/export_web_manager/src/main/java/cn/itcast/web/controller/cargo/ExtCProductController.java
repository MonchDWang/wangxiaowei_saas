package cn.itcast.web.controller.cargo;

import cn.itcast.domain.cargo.*;
import cn.itcast.service.cargo.ContractProductService;
import cn.itcast.service.cargo.ContractService;
import cn.itcast.service.cargo.ExtCproductService;
import cn.itcast.service.cargo.FactoryService;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/cargo/extCproduct")
public class ExtCProductController extends BaseController{

    @Reference
    private ContractService contractService;

    @Reference
    private FactoryService factoryService;

    @Reference
    private ContractProductService contractProductService;

    @Reference
    private ExtCproductService extCproductService;


    @RequestMapping(value = "/list",name = "附件查询")
    public String findPageAll(String contractId,String contractProductId,@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){
        // 查询附件的厂商
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria1 = factoryExample.createCriteria();
        criteria1.andCtypeEqualTo("附件");
        List<Factory> factoryList = factoryService.findAll(factoryExample);
        request.setAttribute("factoryList",factoryList);
        // 根据货物的id获取所有的附件
        ExtCproductExample extCproductExample = new ExtCproductExample();
        ExtCproductExample.Criteria criteria = extCproductExample.createCriteria();
        criteria.andContractProductIdEqualTo(contractProductId);
        PageInfo page = extCproductService.findAll(extCproductExample, pageNum, pageSize);
        request.setAttribute("page",page);
        request.setAttribute("contractId",contractId);
        request.setAttribute("contractProductId",contractProductId);
        //到展示页面
        return "cargo/extc/extc-list";
    }

    @RequestMapping(value = "/edit",name = "附件新增")
    public String edit(ExtCproduct extCproduct){

        extCproduct.setCompanyId(companyId);
        extCproduct.setCompanyName(companyName);
        if(UtilFuns.isEmpty(extCproduct.getId())){
            // 保存
            extCproductService.save(extCproduct);
        }else {
            // 修改
            extCproductService.update(extCproduct);
        }
        //到查询
        return "redirect:/cargo/extCproduct/list.do?contractId="+extCproduct.getContractId()+"&contractProductId="+extCproduct.getContractProductId();
    }


    @RequestMapping(value = "/toUpdate",name = "附件修改")
    public String toUpdate(String id,String contractId,String contractProductId){
        //1 根据附件id查询附件数据展示
        ExtCproduct extCproduct = extCproductService.findById(id);
        request.setAttribute("extCproduct",extCproduct);
        //2 查询附件的厂商
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria1 = factoryExample.createCriteria();
        criteria1.andCtypeEqualTo("附件");
        List<Factory> factoryList = factoryService.findAll(factoryExample);
        request.setAttribute("factoryList",factoryList);

        request.setAttribute("contractId",contractId);
        request.setAttribute("contractProductId",contractProductId);
        //到修改页面
        return "cargo/extc/extc-update";
    }


    @RequestMapping(value = "/delete",name = "附件删除")
    public String delete(String id,String contractId,String contractProductId){
        extCproductService.delete(id);
        //到修改页面
        return "redirect:/cargo/extCproduct/list.do?contractId="+contractId+"&contractProductId="+contractProductId;
    }



}
