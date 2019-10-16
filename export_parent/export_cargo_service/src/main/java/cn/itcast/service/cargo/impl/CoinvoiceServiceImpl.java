package cn.itcast.service.cargo.impl;

import cn.itcast.dao.cargo.CoinvoiceDao;
import cn.itcast.dao.cargo.CoshippingorderDao;
import cn.itcast.domain.cargo.Coinvoice;
import cn.itcast.domain.cargo.CoinvoiceExample;
import cn.itcast.domain.cargo.Coshippingorder;
import cn.itcast.service.cargo.CoinvoiceService;
import cn.itcast.service.cargo.CoshippingorderService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CoinvoiceServiceImpl implements CoinvoiceService {

    @Autowired//发票
    private CoinvoiceDao coinvoiceDao;

    @Autowired//委托单
    private CoshippingorderDao coshippingorderDao;


    @Override//分页查询
    public PageInfo findAll(CoinvoiceExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Coinvoice> list = coinvoiceDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override//根据主键删除
    public int deleteByPrimaryKey(String invoiceId) {
        //根据共享id查询委托单
        Coshippingorder coshippingorder = coshippingorderDao.selectByPrimaryKey(invoiceId);
        //修改委托单的状态
        coshippingorder.setState(1);
        //保存委托单
        coshippingorderDao.updateByPrimaryKey(coshippingorder);
        //删除发票
        return coinvoiceDao.deleteByPrimaryKey(invoiceId);
    }

    @Override//添加发票
    public int insert(Coinvoice record) {
        //通过传过来的数据获取id通过id查询委托单
        String invoiceId = record.getInvoiceId();
        Coshippingorder coshippingorder = coshippingorderDao.selectByPrimaryKey(invoiceId);
        //修改委托单的状态
        coshippingorder.setState(2);
        //修改委托单
        coshippingorderDao.updateByPrimaryKey(coshippingorder);
        //设置发票的状态
        record.setState(1);

        return coinvoiceDao.insert(record);
    }

    @Override//选择插入
    public int insertSelective(Coinvoice record) {
        return 0;
    }

    @Override//条件查询
    public List<Coinvoice> selectByExample(CoinvoiceExample example) {
        return null;
    }

    @Override//根据主键查询
    public Coinvoice selectByPrimaryKey(String invoiceId) {
        return coinvoiceDao.selectByPrimaryKey(invoiceId);
    }

    @Override//根据条件修改
    public int updateByPrimaryKeySelective(Coinvoice record) {
        record.setState(1);
        coinvoiceDao.updateByPrimaryKey(record);
        return 0;
    }

    @Override//根据主键修改
    public int updateByPrimaryKey(Coinvoice record) {
        return coinvoiceDao.updateByPrimaryKey(record);
    }
}
