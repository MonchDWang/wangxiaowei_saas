package cn.itcast.service.stat.impl;

import cn.itcast.dao.stat.StatDao;
import cn.itcast.service.stat.StatService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatDao statDao;

    @Override
    public List findFactoryData(String companyId) {
        return statDao.findFactoryData(companyId);
    }

    @Override
    public List findSellData(String companyId) {
        return statDao.findSellData(companyId);
    }

    @Override
    public List findOnlineData(String companyId) {
        return statDao.findOnlineData(companyId);
    }

    @Override
    public List findCountIpData(String companyId) {
        return statDao.findCountIpData(companyId);
    }

    @Override
    public List findPriceData() {
        return statDao.findPriceData();
    }
}
