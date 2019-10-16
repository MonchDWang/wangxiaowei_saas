package cn.itcast.service.cargo;

import cn.itcast.domain.cargo.CoPackingList;
import cn.itcast.domain.cargo.CoPackingListExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CoPackingListService {

    //根据id查询
    CoPackingList findById(String id);

    //保存
    void save(CoPackingList packingList);

    //更新
    void update(CoPackingList packingList);

    //删除
    void delete(String id);

    //分页查询
    PageInfo findAll(CoPackingListExample example, int pageNum, int size);

    List<CoPackingList> findByExample(CoPackingListExample copackinglistExample);
}
