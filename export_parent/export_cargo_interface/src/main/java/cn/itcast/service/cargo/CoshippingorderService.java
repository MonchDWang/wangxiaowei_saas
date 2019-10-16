package cn.itcast.service.cargo;

import cn.itcast.domain.cargo.Coshippingorder;
import cn.itcast.domain.cargo.CoshippingorderExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CoshippingorderService {

    //根据id查询
    Coshippingorder findById(String id);

    //保存
    void save(Coshippingorder coshippingorder, String packingListId);

    //更新
    void update(Coshippingorder coshippingorder);

    //删除
    void delete(String id);

    //分页查询
    PageInfo findAll(CoshippingorderExample example, int page, int size);

    void update(Coshippingorder coshippingorder, String packingListId);

    List<Coshippingorder> selectByExample(CoshippingorderExample coshippingorderExample);

}