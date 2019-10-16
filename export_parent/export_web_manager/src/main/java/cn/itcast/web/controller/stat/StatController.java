package cn.itcast.web.controller.stat;

import cn.itcast.service.stat.StatService;
import cn.itcast.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @创建人 liujie
 * @创建时间 2019/8/7 8:49
 * @描述
 */
@Controller
@RequestMapping(value = "/stat")
public class StatController extends BaseController {


    @Reference
    private StatService statService;

    @RequestMapping(value = "/toCharts")
    public String toCharts(String chartsType){

        return "stat/stat-"+chartsType;
    }


    // 厂家商品的销售情况
    @RequestMapping(value = "/getFactoryData")
    @ResponseBody
    public List getFactoryData(){

        List list = statService.findFactoryData(companyId);
        return list;
    }

    @RequestMapping(value = "/getSellData")
    @ResponseBody
    public List getSellData(){

        List list = statService.findSellData(companyId);
        return list;
    }

    @RequestMapping(value = "/getOnlineData")
    @ResponseBody
    public List getOnlineData(){

        List list = statService.findOnlineData(companyId);
        return list;
    }

    @RequestMapping(value = "/getCountIpData")
    @ResponseBody
    public List getCountIpData(){

        List list = statService.findCountIpData(companyId);
        return list;
    }
    @RequestMapping(value = "/getPriceData")
    @ResponseBody
    public List getPriceData(){
        //调用service
        List list=statService.findPriceData();
        System.out.println(list);
        return list;
    }

}
