package cn.itcast.web.controller.cargo;

import cn.itcast.domain.cargo.ContractProduct;
import cn.itcast.domain.cargo.ContractProductExample;
import cn.itcast.domain.cargo.Factory;
import cn.itcast.domain.cargo.FactoryExample;
import cn.itcast.service.cargo.ContractProductService;
import cn.itcast.service.cargo.ContractService;
import cn.itcast.service.cargo.FactoryService;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.controller.BaseController;
import cn.itcast.web.utils.FileUploadUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cargo/contractProduct")
public class ContractProductController extends BaseController{

    @Reference
    private ContractService contractService;

    @Reference
    private FactoryService factoryService;

    @Reference
    private ContractProductService contractProductService;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @RequestMapping(value = "/list",name = "货物查询")
    public String findPageAll(String contractId,@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){

        //1 查询所有的厂家
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria1 = factoryExample.createCriteria();
        criteria1.andCtypeEqualTo("货物");
        List<Factory> factoryList = factoryService.findAll(factoryExample);
        //2 根据合同id获取到属于该合同的所有货物
        ContractProductExample contractProductExample = new ContractProductExample();
        ContractProductExample.Criteria criteria = contractProductExample.createCriteria();
        criteria.andContractIdEqualTo(contractId);
        PageInfo pageInfo = contractProductService.findAll(contractProductExample, pageNum, pageSize);
        request.setAttribute("page",pageInfo);
        // 3 保存数据到页面
        request.setAttribute("factoryList",factoryList);

        request.setAttribute("contractId",contractId);
        //到展示页面
        return "cargo/product/product-list";
    }

    @RequestMapping(value = "/edit",name = "合同新增")
    public String edit(ContractProduct contractProduct,MultipartFile productPhoto) throws Exception {

        contractProduct.setCompanyId(companyId);
        contractProduct.setCompanyName(companyName);
        if(UtilFuns.isEmpty(contractProduct.getId())){
            // 保存
            // 将图片上传七牛云存储中
            if(productPhoto!=null){
                String imgurl = fileUploadUtil.upload(productPhoto);
                contractProduct.setProductImage("http://"+imgurl);
            }
            contractProductService.save(contractProduct);
        }else {
            // 修改
            contractProductService.update(contractProduct);
        }
        //到查询
        return "redirect:/cargo/contractProduct/list.do?contractId="+contractProduct.getContractId();
    }


    @RequestMapping(value = "/toUpdate",name = "货物修改页面")
    public String toUpdate(String id){
        //根据id查货物
        ContractProduct contractProduct = contractProductService.findById(id);
        request.setAttribute("contractProduct",contractProduct);
        // 查询货物的厂商
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria1 = factoryExample.createCriteria();
        criteria1.andCtypeEqualTo("货物");
        List<Factory> factoryList = factoryService.findAll(factoryExample);
        request.setAttribute("factoryList",factoryList);
        //到查询
        return "cargo/product/product-update";
    }


    //删除
    @RequestMapping(value = "/delete",name = "货物删除")
    public String delete(String id,String contractId){
        contractProductService.delete(id);
        //到查询
        return "redirect:/cargo/contractProduct/list.do?contractId="+contractId;
    }


    //到上传货物页面
    @RequestMapping(value = "/toImport",name = "上传货物页面")
    public String toImport(String contractId){
        request.setAttribute("contractId",contractId);
        //到查询
        return "cargo/product/product-import";
    }

    // 保存excel表中的货物数据到数据库
    @RequestMapping(value = "/import",name = "保存excel表中的货物数据到数据库")
    public String imports(String contractId,MultipartFile file) throws IOException {

        List<ContractProduct> list=new ArrayList<>();
        // 1 读取提交的excel文件
        // 加载读取文件
        Workbook wb=new XSSFWorkbook(file.getInputStream());
        // 获取页码
        Sheet st = wb.getSheetAt(0);
        // 获取行
        for(int i=1;i<st.getLastRowNum()+1;i++){
            Row row=st.getRow(i);
            Object[] obj=new Object[10];
            // 获取单元格
            for(int j=1;j<row.getLastCellNum();j++){
                // 获取单元格
                Cell cell = row.getCell(j);
                // 获取单元格内容
                if(cell!=null){
                    // 将每一行的单元格数据都存在数组中
                                // obj[1]=深化
                                // obj[2]=xx001
                                // ...  obj[1]---obj[9]
                    obj[j]=getCellValue(cell);
                }
            }
            // 将数据给对象
            ContractProduct contractProduct = new ContractProduct(obj,companyId,companyName);
            contractProduct.setContractId(contractId);
            // 放入集合中
            list.add(contractProduct);
        }
        // 2 保存文件中货物数据到数据库
        contractProductService.save(list);
        //到查询
        return "redirect:/cargo/contractProduct/list.do?contractId="+contractId;
    }


    //判断单元格类型且返回数据
    private Object getCellValue(Cell cell) {
        Object object=null;
        // 获取单元格的类型
        CellType cellType = cell.getCellType();
        // 根据不用的类型做不同的方法获取
        switch (cellType){
            case STRING:{
                // 获取数据
                object=cell.getStringCellValue();
                break;
            }
            case BOOLEAN:{
                object=cell.getBooleanCellValue();
                break;
            }
            case NUMERIC:{
                if(DateUtil.isCellDateFormatted(cell)){
                    object=cell.getDateCellValue();
                }else {
                    object=cell.getNumericCellValue();
                }
                break;
            }
            default:{
                break;
            }
        }
        return object;
    }

}
