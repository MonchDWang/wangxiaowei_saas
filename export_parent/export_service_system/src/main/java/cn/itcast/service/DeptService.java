package cn.itcast.service;

import cn.itcast.domain.Dept;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DeptService {
    //pageHelper的分页查询
    PageInfo findPageAll(int pageNum, int pageSize, String companyId);

    List<Dept> findAllDept(String companyId);

    void save(Dept dept);

    Dept findById(String id);

    void update(Dept dept);

    void delete(String id);
}
