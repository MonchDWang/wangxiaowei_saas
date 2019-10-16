package cn.itcast.service.impl;

import cn.itcast.dao.syslog.SysLogDao;
import cn.itcast.domain.SysLog;
import cn.itcast.service.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    public PageInfo findAll(int pageNum, int pageSize, String companyId) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysLog> list = sysLogDao.findAll(companyId);
        return new PageInfo(list);
    }

    public int save(SysLog log) {
        return sysLogDao.save(log);
    }
}
