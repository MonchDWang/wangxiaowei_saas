package cn.itcast.service.cargo.impl;

import cn.itcast.dao.cargo.ContractDao;
import cn.itcast.dao.cargo.ContractProductDao;
import cn.itcast.dao.cargo.ExtCproductDao;
import cn.itcast.domain.cargo.*;
import cn.itcast.service.cargo.ContractProductService;
import cn.itcast.vo.ContractProductVo;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class ContractProductServiceImpl implements ContractProductService {

    @Autowired
    private ContractProductDao contractProductDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private ExtCproductDao extCproductDao;

    @Override
    public void save(ContractProduct contractProduct) {
        //设置基本数据(id 总金额)
        contractProduct.setId(UUID.randomUUID().toString().replace("-",""));
                //总金额=单价*数量
        double total=contractProduct.getPrice()*contractProduct.getCnumber();
        contractProduct.setAmount(total);
        // 查询该货物的合同
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        // 修改合同的总金额
                    //总金额=以前的+现在的
        contract.setTotalAmount(contract.getTotalAmount()+total);
        // 修改合同的货件数
        contract.setProNum(contract.getProNum()+1);
        // 保存货物
        contractProductDao.insertSelective(contractProduct);
        // 修改合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void update(ContractProduct contractProduct) {
        // 货物以前的货物总金额
        ContractProduct old_contractProduct= contractProductDao.selectByPrimaryKey(contractProduct.getId());
        // 计算最新的货物总金额
        double total=contractProduct.getPrice()*contractProduct.getCnumber();
        // 设置给货物
        contractProduct.setAmount(total);
        // 根据货物获取合同
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        // 修改合同的总金额  总金额=总金额-货物以前的+货物现在的
        contract.setTotalAmount(contract.getTotalAmount()-old_contractProduct.getAmount()+total);
        // 修改货物
        contractProductDao.updateByPrimaryKeySelective(contractProduct);
        // 修改合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void delete(String id) {

        // 获取货物
        ContractProduct contractProduct = contractProductDao.selectByPrimaryKey(id);
        // 获取货物总金额
        double total=contractProduct.getAmount();
        // 根据货物id获取附件 (list)
        ExtCproductExample extCproductExample = new ExtCproductExample();
        ExtCproductExample.Criteria criteria = extCproductExample.createCriteria();
        criteria.andContractProductIdEqualTo(id);
        List<ExtCproduct> list = extCproductDao.selectByExample(extCproductExample);
        // 循环遍历附件累加货物和附件的总金额
        for (ExtCproduct extCproduct : list) {
            total+=extCproduct.getAmount();
            // 删除附件
            extCproductDao.deleteByPrimaryKey(extCproduct.getId());
        }
        // 删除货物
        contractProductDao.deleteByPrimaryKey(id);
        // 查询合同
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        // 修改合同总金额 总金额-货物和附件累加的
        contract.setTotalAmount(contract.getTotalAmount()-total);
        // 修改合同的货件数  -1
        contract.setProNum(contract.getProNum()-1);
        // 修改合同的附件数  -集合.size()
        contract.setExtNum(contract.getExtNum()-list.size());
        // 修改合同
        contractDao.updateByPrimaryKeySelective(contract);

    }

    @Override
    public ContractProduct findById(String id) {

        return contractProductDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ContractProductExample example, int page, int size) {

        PageHelper.startPage(page,size);
        List<ContractProduct> list = contractProductDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public void save(List<ContractProduct> list) {
        for (ContractProduct contractProduct : list) {
            // 调用以前的保存方法
            save(contractProduct);
        }
    }

    @Override
    public List<ContractProductVo> findByInputDate(String companyId, String inputDate) {
        return contractProductDao.findByInputDate(companyId,inputDate);
    }
}
