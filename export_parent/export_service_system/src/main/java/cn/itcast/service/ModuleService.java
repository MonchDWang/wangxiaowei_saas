package cn.itcast.service;

import cn.itcast.domain.Module;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ModuleService {

    //根据id查询
    Module findById(String moduleId);

    //根据id删除
    int delete(String moduleId);

    //添加用户
    int save(Module module);

    //更新用户
    int update(Module module);

    //查询全部
    PageInfo findAllPage(int pageNum,int pageSize);

    //查询所有菜单模块
    List<Module> findAllModule();

    List<Module> findRoleModule(String id);
}
