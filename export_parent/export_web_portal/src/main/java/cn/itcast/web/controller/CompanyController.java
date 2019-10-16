package cn.itcast.web.controller;

import cn.itcast.domain.Company;
import cn.itcast.service.company.CompanyService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class CompanyController {

    @Reference
    private CompanyService companyService;


    @RequestMapping(value = "/apply")
    @ResponseBody
    public String apply(Company company){

        try{

            company.setId(UUID.randomUUID().toString().replace("-",""));
            company.setState(0); //未审核
            companyService.save(company);
            return "1";

        }catch (Exception e){
            return "2";
        }

    }
}
