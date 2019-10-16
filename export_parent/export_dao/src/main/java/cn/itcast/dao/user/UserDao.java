package cn.itcast.dao.user;

import cn.itcast.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    // 查询所有部门（根据企业id查询）
    List<User> findPageAll(String companyId);

    User findById(String id);
    
    void save(User user);

    void update(User user);

    void delete(String id);

    void deleteUserRole(String userid);

    void saveUserRole(@Param("userid") String userid, @Param("roleId")String roleId);

    User findByEmail(String email);


}
