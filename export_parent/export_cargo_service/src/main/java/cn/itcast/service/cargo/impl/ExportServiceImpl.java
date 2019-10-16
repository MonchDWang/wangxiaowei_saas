package cn.itcast.service.cargo.impl;

import cn.itcast.dao.cargo.*;
import cn.itcast.domain.cargo.*;
import cn.itcast.service.cargo.ExportService;
import cn.itcast.vo.ExportProductResult;
import cn.itcast.vo.ExportResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired //报运单
    private ExportDao exportDao;

    @Autowired //合同
    private ContractDao contractDao;

    @Autowired //合同下货物
    private ContractProductDao contractProductDao;

    @Autowired //合同下货物附件
    private ExtCproductDao extCproductDao;

    @Autowired //报运单下的货物
    private ExportProductDao exportProductDao;

    @Autowired //报运单下的货物附件
    private ExtEproductDao extEproductDao;

    @Override
    public Export findById(String id) {
        return exportDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Export export) {
        //1 修改合同的状态（state=2）
        String[] contractIds = export.getContractIds().split(",");
        StringBuffer buffer=new StringBuffer();
        for (String contractId : contractIds) {
            //根据id查询合同
            Contract contract = contractDao.selectByPrimaryKey(contractId);
            // 修改状态
            contract.setState(2);
            // 存储合同号 中间有空格
            buffer.append(contract.getContractNo()).append(" ");
            // 修改合同
            contractDao.updateByPrimaryKeySelective(contract);
        }

        //2 设置报运单的一些属性（id create_time 合同号 状态等）
        export.setId(UUID.randomUUID().toString().replace("-",""));
        export.setCreateTime(new Date());
        export.setInputDate(new Date());
        export.setState(0); //草稿
        export.setCustomerContract(buffer.toString());

        //3 保存报运单下的货物商品（来源于合同下的货物）
            // 获取合同下的货物
        ContractProductExample contractProductExample = new ContractProductExample();
        ContractProductExample.Criteria criteria = contractProductExample.createCriteria();
        criteria.andContractIdIn(Arrays.asList(contractIds));
        List<ContractProduct> products = contractProductDao.selectByExample(contractProductExample);
        //合同货物id(1)  === 报运单货物id(3)  map<1,3>
        Map<String,String> map=new HashMap<String,String>();
        for (ContractProduct product : products) {
            // 创建报运单货物商品对象
            ExportProduct exportProduct = new ExportProduct();
            //把合同货物对象的数据给报运单货物对象（属性名一致）
            BeanUtils.copyProperties(product,exportProduct);
            //设置主键属性
            exportProduct.setId(UUID.randomUUID().toString().replace("-",""));
            //设置报运单id
            exportProduct.setExportId(export.getId());

            // 组合合同货物和报运单货物id
            map.put(product.getId(),exportProduct.getId());
            // 保存报运单货物商品
            exportProductDao.insertSelective(exportProduct);
        }

        //4 保存报运单下的货物附件(来源于合同下的附件)
            //获取合同下的所有附件
        ExtCproductExample extCproductExample = new ExtCproductExample();
        ExtCproductExample.Criteria extCproductExampleCriteria = extCproductExample.createCriteria();
        extCproductExampleCriteria.andContractIdIn(Arrays.asList(contractIds));
        List<ExtCproduct> extCproducts = extCproductDao.selectByExample(extCproductExample);
          // 循环遍历
        for (ExtCproduct extCproduct : extCproducts) {
                // 创建报运单下的货物附件
            ExtEproduct extEproduct = new ExtEproduct();
            BeanUtils.copyProperties(extCproduct,extEproduct);
            extEproduct.setId(UUID.randomUUID().toString().replace("-",""));
            //保存属于哪个货物的
            extEproduct.setExportProductId(map.get(extCproduct.getContractProductId()));
            //保存附件
            extEproductDao.insertSelective(extEproduct);
        }

        //5 保存报运单
            // 货物数
        export.setProNum(products.size());
            // 附件数
        export.setExtNum(extCproducts.size());

        exportDao.insertSelective(export);



    }

    @Override
    public void update(Export export) {
        // 修改报运单
        exportDao.updateByPrimaryKeySelective(export);
        // 修改报运单下的货物商品
        List<ExportProduct> exportProducts = export.getExportProducts();
        for (ExportProduct exportProduct : exportProducts) {
            exportProductDao.updateByPrimaryKeySelective(exportProduct);
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public PageInfo findAll(ExportExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Export> list = exportDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public void update(ExportResult exportResult) {
        //exportResult：报运单的id 状态 说明
             //products: 货物的id 货物的税
        // 修改报运单内容（说明 状态）
        Export export = exportDao.selectByPrimaryKey(exportResult.getExportId());
        export.setState(exportResult.getState());
        export.setRemark(exportResult.getRemark());
        exportDao.updateByPrimaryKeySelective(export);

        // 修改报运单下的货物（税）
        Set<ExportProductResult> products = exportResult.getProducts();
        for (ExportProductResult product : products) {
                // 根据id获取货物
            ExportProduct exportProduct = exportProductDao.selectByPrimaryKey(product.getExportProductId());
            exportProduct.setTax(product.getTax());
            exportProductDao.updateByPrimaryKeySelective(exportProduct);
        }

    }

    @Override
    public List<Export> findExample(ExportExample exportExample) {
        return exportDao.selectByExample(exportExample);
    }
}
