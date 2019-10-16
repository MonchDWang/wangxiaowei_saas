package cn.itcast.service.cargo.impl;

import cn.itcast.dao.cargo.CoPackingListDao;
import cn.itcast.dao.cargo.CoshippingorderDao;
import cn.itcast.domain.cargo.CoPackingList;
import cn.itcast.domain.cargo.Coshippingorder;
import cn.itcast.domain.cargo.CoshippingorderExample;
import cn.itcast.service.cargo.CoshippingorderService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CoshippingorderServiceImpl implements CoshippingorderService {

    @Autowired
    private CoshippingorderDao coshippingorderDao;

    @Autowired
    private CoPackingListDao coPackingListDao;


    @Override
    public Coshippingorder findById(String id) {
        return coshippingorderDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Coshippingorder coshippingorder, String packingListId) {
        //
        //主键共享 设置coshippingorder主键id
        coshippingorder.setShippingOrderId(packingListId);
        coshippingorder.setState(0); //草稿状态
        coshippingorderDao.insert(coshippingorder);
        //修改装箱单状态
        CoPackingList coPackingList = new CoPackingList();
        coPackingList.setPackingListId(packingListId);
        coPackingList.setState(2L);
        coPackingListDao.updateByPrimaryKeySelective(coPackingList);

    }


    @Override
    public void delete(String id) {
        //删除委托单
        coshippingorderDao.deleteByPrimaryKey(id);
        //更改装箱单状态
        CoPackingList coPackingList = new CoPackingList();
        coPackingList.setPackingListId(id);
        coPackingList.setState(1L);
        coPackingListDao.updateByPrimaryKeySelective(coPackingList);
    }

    @Override
    public PageInfo findAll(CoshippingorderExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Coshippingorder> list = coshippingorderDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public void update(Coshippingorder coshippingorder, String packingListId) {
        //保存委托单
        //主键共享 设置coshippingorder主键id
        coshippingorder.setShippingOrderId(packingListId);
        coshippingorder.setState(0); //草稿状态
        coshippingorderDao.insert(coshippingorder);
        //更改之前的装箱单的状态
        CoPackingList OldcoPackingList = coPackingListDao.selectByPrimaryKey(coshippingorder.getShippingOrderId());
        OldcoPackingList.setState(1L);
        coPackingListDao.updateByPrimaryKeySelective(OldcoPackingList);
        //更改装箱单状态
        CoPackingList coPackingList = new CoPackingList();
        coPackingList.setPackingListId(packingListId);
        coPackingList.setState(2L);
        coPackingListDao.updateByPrimaryKeySelective(coPackingList);
    }

    @Override
    public List<Coshippingorder> selectByExample(CoshippingorderExample coshippingorderExample) {
        return coshippingorderDao.selectByExample(coshippingorderExample);
    }

    @Override
    public void update(Coshippingorder coshippingorder) {
        coshippingorderDao.updateByPrimaryKeySelective(coshippingorder);
    }

}
