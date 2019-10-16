package cn.itcast.service;

import cn.itcast.domain.SysLog;
import com.github.pagehelper.PageInfo;

public interface SysLogService {

    //查询全部
   PageInfo findAll(int pageNum,int pageSize,String companyId);

    //添加
    int save(SysLog log);
}
