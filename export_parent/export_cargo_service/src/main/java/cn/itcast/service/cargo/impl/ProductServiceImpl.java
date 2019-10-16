package cn.itcast.service.cargo.impl;

import cn.itcast.dao.cargo.ProductDao;
import cn.itcast.domain.cargo.Product;
import cn.itcast.service.cargo.ProductService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @创建人 liujie
 * @创建时间 2019/8/11 21:27
 * @描述
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findById(String id) {
        return productDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Product product) {

        productDao.insertSelective(product);

    }

    @Override
    public void update(Product product) {

        productDao.updateByPrimaryKeySelective(product);

    }

    @Override
    public void delete(String id) {

        productDao.deleteByPrimaryKey(id);

    }

    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<Product> list = productDao.selectByExample(null);
        return new PageInfo(list);
    }
}
