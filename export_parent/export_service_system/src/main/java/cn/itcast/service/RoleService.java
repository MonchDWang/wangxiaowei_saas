package cn.itcast.service;

import cn.itcast.domain.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {

    //查询全部用户
    PageInfo findAllPage(int pageNum, int pageSize, String companyId);

    //根据id查询
    Role findById(String id);

    //根据id删除
    int delete(String id);

    //添加
    int save(Role role);

    //更新
    int update(Role role);

    void updateRoleModule(String roleId, String moduleId);

    //查询所有的角色
    List<Role> findAllRole(String companyId);

    List<Role> findByUserRole(String id);
}
