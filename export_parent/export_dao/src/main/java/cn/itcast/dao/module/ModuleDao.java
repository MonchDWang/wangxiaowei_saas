package cn.itcast.dao.module;

import cn.itcast.domain.Module;

import java.util.List;

/**
 */
public interface ModuleDao {

    //根据id查询
    Module findById(String moduleId);

    //根据id删除
    int delete(String moduleId);

    //添加用户
    int save(Module module);

    //更新用户
    int update(Module module);

    //查询全部
    List<Module> findAllPage();

    List<Module> findRoleModule(String id);

    List<Module> FindByUserModule(String s);

    List<Module> FindByUserModuleToRbac(String id);
}