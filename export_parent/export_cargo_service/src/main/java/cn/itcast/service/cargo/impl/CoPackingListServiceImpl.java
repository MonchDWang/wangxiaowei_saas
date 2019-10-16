package cn.itcast.service.cargo.impl;

import cn.itcast.dao.cargo.CoPackingListDao;
import cn.itcast.domain.cargo.CoPackingList;
import cn.itcast.domain.cargo.CoPackingListExample;
import cn.itcast.service.cargo.CoPackingListService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CoPackingListServiceImpl implements CoPackingListService {

    @Autowired
    private CoPackingListDao coPackingListDao;

    @Override
    public CoPackingList findById(String id) {
        return coPackingListDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(CoPackingList coPackingList) {
        //设置装箱单id
        coPackingList.setPackingListId(UUID.randomUUID().toString().replace("-",""));
        //设置装箱单状态
        coPackingList.setState(0l);
        coPackingList.setCreateTime(new Date());
        coPackingListDao.insertSelective(coPackingList);

    }

    @Override
    public void update(CoPackingList coPackingList) {
        coPackingListDao.updateByPrimaryKeySelective(coPackingList);
    }

    @Override
    public void delete(String id) {
        coPackingListDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(CoPackingListExample example, int pageNum, int size) {
        PageHelper.startPage(pageNum,size);
        List<CoPackingList> list = coPackingListDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public List<CoPackingList> findByExample(CoPackingListExample copackinglistExample) {
        return coPackingListDao.selectByExample(copackinglistExample);
    }

}
