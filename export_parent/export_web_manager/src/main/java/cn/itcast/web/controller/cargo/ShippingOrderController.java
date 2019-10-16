package cn.itcast.web.controller.cargo;

import cn.itcast.domain.cargo.*;
import cn.itcast.service.cargo.CoPackingListService;
import cn.itcast.service.cargo.CoshippingorderService;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/cargo/shipping")
public class ShippingOrderController extends BaseController {

    @Reference
    private CoshippingorderService coshippingorderService;

    @Reference
    private CoPackingListService copackinglistService;




    @RequestMapping(value = "/list", name = "委托管理")
    public String findPageAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4") int pageSize) {
        //根据公司id查询属于当前公司的装箱单 集合
        CoPackingListExample copackinglistExample = new CoPackingListExample();
        CoPackingListExample.Criteria criteria = copackinglistExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        criteria.andStateEqualTo(2L);
        List<CoPackingList> copackinglists = copackinglistService.findByExample(copackinglistExample);
        List<String> values = new ArrayList<>();
        for (CoPackingList copackinglist : copackinglists) {
            values.add(copackinglist.getPackingListId());
        }
        if (values.size() > 0) {
            CoshippingorderExample coshippingorderExample = new CoshippingorderExample();
            CoshippingorderExample.Criteria criteria1 = coshippingorderExample.createCriteria();
            criteria1.andShippingOrderIdIn(values);
            PageInfo pageInfo = coshippingorderService.findAll(coshippingorderExample, pageNum, pageSize);
            request.setAttribute("page",pageInfo);
        }
        return "cargo/shipping/shipping-list";
    }


    @RequestMapping(value = "/toAdd", name = "跳转到添加委托管理页面")
    public String toAdd(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4") int pageSize) {
        //查询装箱单状态为2的
        //根据公司id查询属于当前公司的装箱单 集合
        CoPackingListExample copackinglistExample = new CoPackingListExample();
        CoPackingListExample.Criteria criteria = copackinglistExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        criteria.andStateEqualTo(1L);
        PageInfo pageInfo = copackinglistService.findAll(copackinglistExample, pageNum, pageSize);
        request.setAttribute("page",pageInfo);
        return "cargo/shipping/shipping-add";
    }

    @RequestMapping(value = "/edit", name = "新增/修改委托单")
    public String edit(Coshippingorder coshippingorder,String packingListId) {
        if (UtilFuns.isEmpty(packingListId)) {
            request.setAttribute("errorMessage","请选择一个装箱单提交");
            request.setAttribute("coshippingorder",coshippingorder);
            return "cargo/shipping/shipping-add";
        }
        //设置一些基本属性
        coshippingorder.setCreateBy(user.getId());
        coshippingorder.setCreateDept(user.getDeptId());
        coshippingorder.setCreateTime(new Date());
        if (UtilFuns.isEmpty(coshippingorder.getShippingOrderId())) {
            coshippingorderService.save(coshippingorder,packingListId);
        } else {
            coshippingorderService.update(coshippingorder,packingListId);
        }

        return "redirect:/cargo/shipping/list.do";
    }


    @RequestMapping(value = "/toUpdate", name = "跳转到修改委托单页面")
    public String toUpdate(String id, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4") int pageSize) {
        //根据委托单id查询委托单数据
        Coshippingorder coshippingorder = coshippingorderService.findById(id);
        request.setAttribute("coshippingorder",coshippingorder);
        //查询当前委托单对应的装箱单
        CoPackingList copackinglist = copackinglistService.findById(id);
        System.out.println(copackinglist);
        //查询所有状态为1装箱单
        //根据公司id查询属于当前公司的装箱单 集合
        CoPackingListExample copackinglistExample = new CoPackingListExample();
        CoPackingListExample.Criteria criteria = copackinglistExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        criteria.andStateEqualTo(1L);
        PageInfo pageInfo = copackinglistService.findAll(copackinglistExample, pageNum, pageSize);
        List<CoPackingList> list = pageInfo.getList();
        list.add(copackinglist);
        pageInfo.setList(list);
        pageInfo.setPageSize(pageInfo.getPageSize()+1);
        pageInfo.setTotal(pageInfo.getTotal()+1);
        if (pageInfo.getTotal() % pageInfo.getPageSize() == 0) {
            pageInfo.setPages(pageInfo.getPages() + 1);
        }
        request.setAttribute("page",pageInfo);

        return "cargo/shipping/shipping-update";
    }

    @RequestMapping(value = "/toView", name = "跳转到查看委托单页面")
    public String toView(String id) {
        Coshippingorder coshippingorder = coshippingorderService.findById(id);
        request.setAttribute("coshippingorder",coshippingorder);
        return "cargo/shipping/shipping-view";
    }


    @RequestMapping(value = "/delete", name = "跳转到查看委托单页面")
    public String delete(String id) {
        coshippingorderService.delete(id);
        return "redirect:/cargo/shipping/list.do";
    }

    //提交 submit
    @RequestMapping(value = "/submit",name = "委托单状态的提交")
    public String submit(String id){
        Coshippingorder coshippingorder = new Coshippingorder();
        coshippingorder.setShippingOrderId(id);
        coshippingorder.setState(1);
        coshippingorderService.update(coshippingorder);
        return "redirect:/cargo/shipping/list.do";
    }

    //取消
    @RequestMapping(value = "/cancel",name = "委托单状态的取消")
    public String cancel(String id){
        Coshippingorder coshippingorder = new Coshippingorder();
        coshippingorder.setShippingOrderId(id);
        coshippingorder.setState(0);
        coshippingorderService.update(coshippingorder);
        return "redirect:/cargo/shipping/list.do";
    }

}
