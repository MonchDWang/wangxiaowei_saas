package cn.itcast.dao.dept;

import cn.itcast.domain.Dept;

import java.util.List;

public interface DeptDao {

    // 查询所有部门（根据企业id查询）
    List<Dept> findPageAll(String companyId);

    List<Dept> findAllDept(String companyId);

    void save(Dept dept);

    Dept findById(String id);

    void update(Dept dept);

    void delete(String id);
}
