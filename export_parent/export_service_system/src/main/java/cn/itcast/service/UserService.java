package cn.itcast.service;

import cn.itcast.domain.Module;
import cn.itcast.domain.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    //pageHelper的分页查询
    PageInfo findPageAll(int pageNum, int pageSize, String companyId);

    void save(User user);

    User findById(String id);

    void update(User user);

    void delete(String id);

    void changeRole(String userid, String[] roleIds);

    User findByEmail(String email);

    List<Module> FindByUserModule(User user);
}
