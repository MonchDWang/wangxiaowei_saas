package cn.itcast.web.controller.cargo;

import cn.itcast.domain.cargo.*;
import cn.itcast.service.cargo.CoinvoiceService;
import cn.itcast.service.cargo.CoshippingorderService;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.zookeeper.data.Id;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/cargo/invoice")
public class CoinvoiceController extends BaseController {

    @Reference
    private CoinvoiceService coinvoiceService;

    @Reference
    private CoshippingorderService coshippingorderService;

    @RequestMapping(value = "/list",name = "查询所有的发票")
    public String findPageAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){

        //查询 所有的发票
        CoinvoiceExample coinvoiceExample = new CoinvoiceExample();
        CoinvoiceExample.Criteria criteria = coinvoiceExample.createCriteria();
        PageInfo page = coinvoiceService.findAll(coinvoiceExample, pageNum, pageSize);
        //传到页面展示
        request.setAttribute("page",page);

        return "cargo/invoice/invoice-list";
    }

    @RequestMapping(value = "/add",name = "到新增发票页面")
    public String add(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){

        CoshippingorderExample coshippingorderExample = new CoshippingorderExample();
        CoshippingorderExample.Criteria criteria = coshippingorderExample.createCriteria();
        criteria.andStateEqualTo(1);
        PageInfo page = coshippingorderService.findAll(coshippingorderExample,pageNum, pageSize);
        //将数据传到页面展示
        request.setAttribute("page",page);
        return "cargo/invoice/invoice-add";
    }



    @RequestMapping(value = "/edit",name = "添加或修改发票")//添加或修改
    public String edit(Coinvoice coinvoice,String shippingOrderId){

        if(UtilFuns.isEmpty(coinvoice.getInvoiceId())){
            coinvoice.setCreateDept(user.getDeptName());
            //添加
            coinvoice.setInvoiceId(shippingOrderId);
           coinvoiceService.insert(coinvoice);
        }else{
            //修改
            coinvoiceService.updateByPrimaryKeySelective(coinvoice);
        }

        //到查询
        return "redirect:/cargo/invoice/list.do";
    }



    @RequestMapping(value = "/toUpdate",name = "到修改发票页面")//修改
    public String toUpdate(String invoiceId,@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){
        System.out.println(invoiceId);
        //通过id查询发票
        Coinvoice invoice = coinvoiceService.selectByPrimaryKey(invoiceId);
        //将得到的数据到页面展示
        request.setAttribute("invoice",invoice);
        //通过id查询发票下的委托单
        CoshippingorderExample coshippingorderExample = new CoshippingorderExample();
        CoshippingorderExample.Criteria criteria = coshippingorderExample.createCriteria();
        criteria.andShippingOrderIdEqualTo(invoiceId);
        List<Coshippingorder> list = coshippingorderService.selectByExample(coshippingorderExample);

        //将数据传到页面展示
       request.setAttribute("page",list);

        return "cargo/invoice/invoice-update";
    }


    //根据id删除发票
    @RequestMapping(value = "/delete",name = "删除发票")
    public String delete(String id){

        coinvoiceService.deleteByPrimaryKey(id);
        //到查询
        return "redirect:/cargo/invoice/list.do";
    }




    //修改提交发票状态
    @RequestMapping(value = "/submit",name = "修改发票状态")
    public String submit(String id){
        //先查询发票
        Coinvoice coinvoice = coinvoiceService.selectByPrimaryKey(id);
        //设置发票状态
        coinvoice.setState(2);
        //保存
        coinvoiceService.updateByPrimaryKey(coinvoice);

        //到查询
        return "redirect:/cargo/invoice/list.do";
    }


    //修改取消发票状态
    @RequestMapping(value = "/cancel",name = "修改发票状态")
    public String cancel(String id){
        //先查询发票
        Coinvoice coinvoice = coinvoiceService.selectByPrimaryKey(id);
        //设置发票状态
        coinvoice.setState(1);
        //保存
        coinvoiceService.updateByPrimaryKey(coinvoice);

        //到查询
        return "redirect:/cargo/invoice/list.do";
    }
}
