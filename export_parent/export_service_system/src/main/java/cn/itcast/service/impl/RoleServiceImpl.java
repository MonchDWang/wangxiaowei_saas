package cn.itcast.service.impl;

import cn.itcast.dao.role.RoleDao;
import cn.itcast.domain.Role;
import cn.itcast.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    public PageInfo findAllPage(int pageNum, int pageSize, String companyId) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> list = roleDao.findAllPage(companyId);
        return new PageInfo(list);
    }

    public Role findById(String id) {
        return roleDao.findById(id);
    }

    public int delete(String id) {
        return roleDao.delete(id);
    }

    public int save(Role role) {
        return roleDao.save(role);
    }

    public int update(Role role) {
        return roleDao.update(role);
    }

    public void updateRoleModule(String roleId, String moduleId) {

        // 先删 --roleid
        System.out.println(roleId);
        roleDao.deleteByRoleId(roleId);
        // 后增
        String[]  moduleIds= moduleId.split(",");
        for (String mId : moduleIds) {
            roleDao.saveRoleModule(roleId,mId);
        }

    }

    public List<Role> findAllRole(String companyId) {
        return roleDao.findAllPage(companyId);
    }

    public List<Role> findByUserRole(String id) {
        return roleDao.findByUserRole(id);
    }
}
