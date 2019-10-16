package cn.itcast.web.controller.cargo;


import cn.itcast.domain.cargo.Factory;
import cn.itcast.domain.cargo.Product;
import cn.itcast.service.cargo.FactoryService;
import cn.itcast.service.cargo.ProductService;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @创建人 liujie
 * @创建时间 2019/8/11 21:29
 * @描述
 */
@Controller
@RequestMapping("/baseinfo/product")
public class ProductController extends BaseController {

    @Reference
    private ProductService productService;

    @Reference
    private FactoryService factoryService;

    @RequestMapping("/list")
    public String findPageAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4") int pageSize) {

        PageInfo page = productService.findAll(pageNum, pageSize);

        request.setAttribute("page",page);

        return "baseinfo/product/product-list";
    }

    @RequestMapping("/toAdd")
    public String findPageAll(){

        List<Factory> list = factoryService.findAll(null);
        request.setAttribute("factoryList",list);

        return "baseinfo/product/product-add";
    }

    @RequestMapping("/edit")
    public String edit(Product product) {

        if (UtilFuns.isEmpty(product.getId())) {
            //添加
            product.setId(UUID.randomUUID().toString().replace("-", ""));
            product.setQty(Integer.valueOf(product.getQty()));
            productService.save(product);
        } else {
            //修改

            product.setCreateTime(new Date());

            productService.update(product);
        }
        //return "redirect:/cargo/contractProduct/list.do?contractId="+contractProduct.getContractId();
        return "redirect:/baseinfo/product/list.do";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){

        List<Factory> list = factoryService.findAll(null);
        request.setAttribute("factoryList",list);

        Product product = productService.findById(id);

        request.setAttribute("product",product);
        return "baseinfo/product/product-update";
    }

    @RequestMapping("/delete")
    public String delete(String id){
        productService.delete(id);
        return "redirect:/baseinfo/product/list.do";
    }
}
