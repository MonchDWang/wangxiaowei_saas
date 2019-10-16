package cn.itcast.web.controller.company;

import cn.itcast.domain.Company;
import cn.itcast.service.CompanyService;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping(value = "/company")
public class CompanyController extends BaseController{

    @Autowired
    private CompanyService companyService;

    /* 传统方式
    * @RequestParam(defaultValue="参数设置默认值")
    * */
//    @RequestMapping(value = "/list",name = "企业列表查询")
//    public String findAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4") int pageSize){
//
//        PageBean pageBean=companyService.findAll(pageNum,pageSize);
//        request.setAttribute("page",pageBean);
//        return "company/company-list";
//    }


    //pageHelper插件的分页方式
    @RequestMapping(value = "/list",name = "企业列表查询")
    public String findAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4") int pageSize){
        PageInfo page = companyService.findPage(pageNum, pageSize);
        request.setAttribute("page",page);
        return "company/company-list";
    }



    @RequestMapping(value = "/toAdd",name = "跳转到企业的添加页面")
    public String toAdd(){
        return "company/company-add";
    }


    /*
    *  1 将页面数据封装给对象
    *               手动设置id为uuid(为了让主键唯一)
    *  2 传递封装好的对象到数据库中
    *  3 重定向到查询方法list中带最新数据到页面展示
    *
    *
    * */
    @RequestMapping(value = "/edit",name = "添加/修改企业信息")
    public String edit(Company company){
        if(UtilFuns.isEmpty(company.getId())){
            // 添加
            company.setId(UUID.randomUUID().toString().replace("-",""));
            companyService.save(company);
        }else {
            //修改
            companyService.update(company);
        }

        return "redirect:/company/list.do";
    }


    /*
    *  1 接受点击信息的id,去数据库查询该数据的所有内容
    *  2 带着内容去修改页面回显
    * */
    @RequestMapping(value = "/toUpdate",name = "带着数据到修改页面回显")
    public String toUpdate(String id){
        Company company=companyService.findById(id);
        request.setAttribute("company",company);
        return "company/company-update";
    }


    @RequestMapping(value = "/delete",name = "企业信息删除")
    public String delete(String id){
        companyService.deleteById(id);
        return "redirect:/company/list.do";
    }
}
