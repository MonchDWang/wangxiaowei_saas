package cn.itcast.service.cargo;

import cn.itcast.domain.cargo.Product;
import com.github.pagehelper.PageInfo;

public interface ProductService {
    //根据id查询
    Product findById(String id);

    //保存
    void save(Product product);

    //更新
    void update(Product product);

    //删除
    void delete(String id);

    //分页查询
    public PageInfo findAll(int page, int size);
}
