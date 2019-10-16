package cn.itcast.service.stat;

import java.util.List;

public interface StatService {
    List findFactoryData(String companyId);

    List findSellData(String companyId);

    List findOnlineData(String companyId);

    List findCountIpData(String companyId);

    List findPriceData();

}
