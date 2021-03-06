package cn.itcast.service.cargo;

import cn.itcast.domain.cargo.Coinvoice;
import cn.itcast.domain.cargo.CoinvoiceExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CoinvoiceService {

    PageInfo findAll(CoinvoiceExample example , int page, int size);


    //根据id删除
    int deleteByPrimaryKey(String invoiceId);


    //添加
    int insert(Coinvoice record);


    // 修改
    int insertSelective(Coinvoice record);


    //根据条件查询

    List<Coinvoice> selectByExample(CoinvoiceExample example);

    // 根据id查询
    Coinvoice selectByPrimaryKey(String invoiceId);


    //根据主键选择更新
    int updateByPrimaryKeySelective(Coinvoice record);

    //根据主键全部更新
    int updateByPrimaryKey(Coinvoice record);
}
