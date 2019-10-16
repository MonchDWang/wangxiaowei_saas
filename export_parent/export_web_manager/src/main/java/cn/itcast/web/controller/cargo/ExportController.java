package cn.itcast.web.controller.cargo;

import cn.itcast.domain.cargo.*;
import cn.itcast.service.cargo.ContractService;
import cn.itcast.service.cargo.ExportProductService;
import cn.itcast.service.cargo.ExportService;
import cn.itcast.utils.UtilFuns;
import cn.itcast.vo.ExportProductVo;
import cn.itcast.vo.ExportResult;
import cn.itcast.vo.ExportVo;
import cn.itcast.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cargo/export")
public class ExportController extends BaseController{

    @Reference
    private ContractService contractService;

    @Reference
    private ExportService exportService;

    @Reference
    private ExportProductService exportProductService;

    @RequestMapping(value = "/contractList",name = "查询状态为1的合同列表")
    public String contractList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){

        ContractExample example = new ContractExample();
        ContractExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        // 状态为1的合同
        criteria.andStateEqualTo(1);
        PageInfo page = contractService.findAll(example, pageNum, pageSize);
        request.setAttribute("page",page);
        return "cargo/export/export-contractList";
    }



    @RequestMapping(value = "/list",name = "查询报运单列表")
    public String list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){

        ExportExample exportExample = new ExportExample();
        ExportExample.Criteria criteria = exportExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        PageInfo page = exportService.findAll(exportExample, pageNum, pageSize);
        request.setAttribute("page",page);
        return "cargo/export/export-list";
    }


    @RequestMapping(value = "/toExport",name = "到生成报运单页面")
    // mvc：String: 123,234   String[" 123","234"]
    public String toExport(String id){
        request.setAttribute("id",id);
        return "cargo/export/export-toExport";
    }



    @RequestMapping(value = "/edit",name = "新增报运单")
    public String edit(Export export){

        export.setCompanyId(companyId);
        export.setCompanyName(companyName);
        if(UtilFuns.isEmpty(export.getId())){
            //保存
            exportService.save(export);
        }else {
            //修改
            exportService.update(export);

        }
        return "redirect:/cargo/export/list.do";
    }



    @RequestMapping(value = "/toView",name = "报运单查看")
    public String toView(String id){
        //根据报运单的id查询报运单
        Export export = exportService.findById(id);
        // 放入request中带页面
        request.setAttribute("export",export);
        return "cargo/export/export-view";
    }



    @RequestMapping(value = "/toUpdate",name = "到报运单编辑页面")
    public String toUpdate(String id){
        //根据报运单的id查询报运单
        Export export = exportService.findById(id);
        // 放入request中带页面
        request.setAttribute("export",export);

        // 获取报运单(id)下的商品展示
        ExportProductExample exportProductExample = new ExportProductExample();
        ExportProductExample.Criteria criteria = exportProductExample.createCriteria();
        criteria.andExportIdEqualTo(id);
        List<ExportProduct> exps=exportProductService.findAll(exportProductExample);
        request.setAttribute("eps",exps);

        return "cargo/export/export-update";
    }

    // 海关报运

    @RequestMapping(value = "/exportE",name = "海关报运")
    public String exportE(String id){
        // 实现海关报运
        // 1 封装数据到ExportVo（来源于报运单）
        ExportVo exportVo = new ExportVo();
        Export export = exportService.findById(id);
        BeanUtils.copyProperties(export,exportVo);
        exportVo.setExportId(export.getId());
        //2 封装商品数据到ExportProductVo（来源于报运单下货物）
        List<ExportProductVo> products = new ArrayList<>();

        ExportProductExample exportProductExample = new ExportProductExample();
        ExportProductExample.Criteria criteria = exportProductExample.createCriteria();
        criteria.andExportIdEqualTo(id);
        List<ExportProduct> exportProducts = exportProductService.findAll(exportProductExample);
        for (ExportProduct exportProduct : exportProducts) {
                //创建海关需要的商品对象
            ExportProductVo exportProductVo = new ExportProductVo();
            BeanUtils.copyProperties(exportProduct,exportProductVo);
            exportProductVo.setExportProductId(exportProduct.getId());
            products.add(exportProductVo);
        }
        //3 ExportProductVo封装到ExportVo
        exportVo.setProducts(products);
        //4 带着海关需要的数据post方式访问
        WebClient webClient = WebClient.create("http://localhost:8083/ws/export/user");
            //指定提交方式和传递的数据
        webClient.post(exportVo);

        //5 调用海关的查看方法获取数据
                            // 返回的对象：ExportResult（报运说明 状态  商品税）
        WebClient webClient2 = WebClient.create("http://localhost:8083/ws/export/user/"+id);
        ExportResult exportResult = webClient2.get(ExportResult.class);

        //6 根据从海关拿来的数据修改自己的内容
        exportService.update(exportResult);
        return "redirect:/cargo/export/list.do";
    }

}
