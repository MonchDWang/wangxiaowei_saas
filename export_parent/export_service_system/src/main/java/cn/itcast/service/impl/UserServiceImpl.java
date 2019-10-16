package cn.itcast.service.impl;

import cn.itcast.dao.module.ModuleDao;
import cn.itcast.dao.user.UserDao;
import cn.itcast.domain.Module;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModuleDao moduleDao;


    public PageInfo findPageAll(int pageNum, int pageSize, String companyId) {

        //调用pageHelper的插件
        PageHelper.startPage(pageNum,pageSize);
        // 查询所有
        List<User> list = userDao.findPageAll(companyId);
        // 返回自带的pageInfo
        PageInfo page = new PageInfo(list);
        return page;
    }

    
    public void save(User user) {
        userDao.save(user);
    }

    public User findById(String id) {
        return userDao.findById(id);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(String id) {
        userDao.delete(id);
    }


    public void changeRole(String userid, String[] roleIds) {
        //先删除
        userDao.deleteUserRole(userid);
        // 后循环增加
        for (String roleId : roleIds) {
            userDao.saveUserRole(userid,roleId);
        }
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    // 动态构造菜单数据
    public List<Module> FindByUserModule(User user) {
        //1 先判断该用户的种类等级
        if(user.getDegree()==0){
            // saas管理员的菜单数据
            //2 根据不用的等级做不同的查询
           return moduleDao.FindByUserModule("0");
        }else if(user.getDegree()==1){
            // 企业管理员的菜单数据
            return moduleDao.FindByUserModule("1");
        }else{
            // 企业其他用户（rbac的用户角色菜单权限表）
            return moduleDao.FindByUserModuleToRbac(user.getId());
        }


    }
}
