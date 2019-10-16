package cn.itcast.service.cargo.impl;

import cn.itcast.dao.cargo.ContractDao;
import cn.itcast.dao.cargo.ExtCproductDao;
import cn.itcast.domain.cargo.Contract;
import cn.itcast.domain.cargo.ExtCproduct;
import cn.itcast.domain.cargo.ExtCproductExample;
import cn.itcast.service.cargo.ExtCproductService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class ExtCproductServiceImpl implements ExtCproductService{

    @Autowired
    private ExtCproductDao extCproductDao;
    @Autowired
    private ContractDao contractDao;

    @Override
    public void save(ExtCproduct extCproduct) {
        //设置基本数据(id 总金额)
        extCproduct.setId(UUID.randomUUID().toString().replace("-",""));
        //总金额=单价*数量
        double total=extCproduct.getPrice()*extCproduct.getCnumber();
        extCproduct.setAmount(total);
        // 查询该附件的合同
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        // 修改合同的总金额
        //总金额=以前的+现在的
        contract.setTotalAmount(contract.getTotalAmount()+total);
        // 修改合同的附件数
        contract.setExtNum(contract.getExtNum()+1);
        // 保存附件
        extCproductDao.insertSelective(extCproduct);
        // 修改合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void update(ExtCproduct extCproduct) {
        // 货物以前的附件总金额
        ExtCproduct old_contractProduct= extCproductDao.selectByPrimaryKey(extCproduct.getId());
        // 计算最新的附件总金额
        double total=extCproduct.getPrice()*extCproduct.getCnumber();
        // 设置给附件
        extCproduct.setAmount(total);
        // 根据附件获取合同
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        // 修改合同的总金额  总金额=总金额-货物以前的+货物现在的
        contract.setTotalAmount(contract.getTotalAmount()-old_contractProduct.getAmount()+total);
        // 修改附件
        extCproductDao.updateByPrimaryKeySelective(extCproduct);
        // 修改合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void delete(String id) {
        // 获取附件
        ExtCproduct extCproduct = extCproductDao.selectByPrimaryKey(id);
        // 获取附件总金额
        Double total = extCproduct.getAmount();
        // 删除附件
        extCproductDao.deleteByPrimaryKey(id);
        // 获取合同
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        // 设置合同金额
        contract.setTotalAmount(contract.getTotalAmount()-total);
        // 设置合同附件数
        contract.setExtNum(contract.getExtNum()-1);
        // 修改合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public ExtCproduct findById(String id) {
        return extCproductDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ExtCproductExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<ExtCproduct> list = extCproductDao.selectByExample(example);
        return new PageInfo(list);
    }



}
