package cn.itcast.service.impl;

import cn.itcast.dao.dept.DeptDao;
import cn.itcast.domain.Dept;
import cn.itcast.service.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;


    public PageInfo findPageAll(int pageNum, int pageSize, String companyId) {

        //调用pageHelper的插件
        PageHelper.startPage(pageNum,pageSize);
        // 查询所有
        List<Dept> list = deptDao.findPageAll(companyId);
        // 返回自带的pageInfo
        PageInfo page = new PageInfo(list);
        return page;
    }

    public List<Dept> findAllDept(String companyId) {
        return deptDao.findAllDept(companyId);
    }

    public void save(Dept dept) {
        deptDao.save(dept);
    }

    public Dept findById(String id) {
        return deptDao.findById(id);
    }

    public void update(Dept dept) {
        deptDao.update(dept);
    }

    public void delete(String id) {
        deptDao.delete(id);
    }
}
