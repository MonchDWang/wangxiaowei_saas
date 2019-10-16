package cn.itcast.dao.syslog;

import cn.itcast.domain.SysLog;

import java.util.List;

public interface SysLogDao {
    //查询全部
    List<SysLog> findAll(String companyId);

    //添加
    int save(SysLog log);
}