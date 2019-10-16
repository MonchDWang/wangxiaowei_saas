package cn.itcast.web.controller.cargo;

import cn.itcast.domain.cargo.Contract;
import cn.itcast.domain.cargo.ContractExample;
import cn.itcast.service.cargo.ContractService;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/cargo/contract")
public class ContractController extends BaseController{

    @Reference
    private ContractService contractService;

    @RequestMapping(value = "/list",name = "合同查询")
    public String findPageAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){

        // 查询所有合同数据
        ContractExample contractExample = new ContractExample();
        contractExample.setOrderByClause("create_time desc");
        ContractExample.Criteria criteria = contractExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);

        //根据的不同级别做不同数据的查询（细粒度的数据权限控制）
        if(user.getDegree()==4){
            criteria.andCreateByEqualTo(user.getId());
        }else if(user.getDegree()==3){
            criteria.andCreateDeptEqualTo(user.getDeptId());
        }else if(user.getDegree()==2){
            criteria.andCreateDeptLike(user.getDeptId()+"%");
        }
        PageInfo page = contractService.findAll(contractExample, pageNum, pageSize);
        request.setAttribute("page",page);
        //到展示页面
        return "cargo/contract/contract-list";
    }


    @RequestMapping(value = "/toAdd",name = "合同新建页面")
    public String toAdd(){
        //到页面
        return "cargo/contract/contract-add";
    }

    @RequestMapping(value = "/edit",name = "合同新增")
    public String edit(Contract contract){
        contract.setCompanyId(companyId);
        contract.setCompanyName(companyName);
        if(UtilFuns.isEmpty(contract.getId())){
            //增加创建人和创建部门
            contract.setCreateBy(user.getId()); //创建人
            contract.setCreateDept(user.getDeptId()); //创建人部门
            contractService.save(contract);
        }else{
            contractService.update(contract);
        }

        //到查询
        return "redirect:/cargo/contract/list.do";
    }


    @RequestMapping(value = "/toUpdate",name = "合同修改页面")
    public String toUpdate(String id){
        //根据id查合同
        Contract contract=contractService.findById(id);
        request.setAttribute("contract",contract);
        //到查询
        return "cargo/contract/contract-update";
    }

    //根据id删除合同
    @RequestMapping(value = "/delete",name = "删除合同")
    public String delete(String id){

        contractService.delete(id);
        //到查询
        return "redirect:/cargo/contract/list.do";
    }

    //查看
    @RequestMapping(value = "/toView",name = "合同查看页面")
    public String toView(String id){
        //根据id查合同
        Contract contract=contractService.findById(id);
        request.setAttribute("contract",contract);
        //到查询
        return "cargo/contract/contract-view";
    }

    //提交 submit
    @RequestMapping(value = "/submit",name = "合同状态的提交")
    public String submit(String id){
        Contract contract = new Contract();
        contract.setId(id);
        contract.setState(1);
        contractService.update(contract);
        //到查询
        return "redirect:/cargo/contract/list.do";
    }


    //取消
    @RequestMapping(value = "/cancel",name = "合同状态的取消")
    public String cancel(String id){
        // 判断 必须是状态为1才能取消回0

        Contract contract = new Contract();
        contract.setId(id);
        contract.setState(0);
        contractService.update(contract);
        //到查询
        return "redirect:/cargo/contract/list.do";
    }

}
