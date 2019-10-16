package cn.itcast.service.impl;

import cn.itcast.dao.module.ModuleDao;
import cn.itcast.domain.Module;
import cn.itcast.service.ModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleDao moduleDao;

    public PageInfo findAllPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Module> list = moduleDao.findAllPage();
        return new PageInfo(list);
    }

    public List<Module> findAllModule() {
        return moduleDao.findAllPage();
    }

    public List<Module> findRoleModule(String id) {
        return moduleDao.findRoleModule(id);
    }

    public Module findById(String moduleId) {
        return moduleDao.findById(moduleId);
    }

    public int delete(String moduleId) {
        return moduleDao.delete(moduleId);
    }

    public int save(Module module) {
        return moduleDao.save(module);
    }

    public int update(Module module) {
        return moduleDao.update(module);
    }


}
