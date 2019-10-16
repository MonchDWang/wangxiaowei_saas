package cn.itcast.dao.stat;

import java.util.List;

public interface StatDao {
    List findFactoryData(String companyId);

    List findSellData(String companyId);

    List findOnlineData(String companyId);

    List findCountIpData(String companyId);

    List findPriceData();

}
