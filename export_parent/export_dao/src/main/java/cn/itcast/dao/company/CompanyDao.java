package cn.itcast.dao.company;

import cn.itcast.domain.Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyDao {

    List<Company> findAll();

    void save(Company company);

    Company findById(String id);

    void update(Company company);

    void deleteById(String id);
    /*
    *    new map()
    *       map.put("start",1)
    *       map.put("pageSize",4)
    *
    * */
    List<Company> findList(@Param("start") int pageNum, @Param("pageSize") int pageSize);

    int findCount();

    List<Company> findPageList();
}
