package cn.itcast.dao.role;

import cn.itcast.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {
    //查询全部用户
    List<Role> findAllPage(String companyId);

    //根据id查询
    Role findById(String id);

    //根据id删除
    int delete(String id);

    //添加
    int save(Role role);

    //更新
    int update(Role role);

    void deleteByRoleId(String roleId);

    void saveRoleModule(@Param("roleId") String roleId, @Param("mId") String mId);

    List<Role> findByUserRole(String id);
}
