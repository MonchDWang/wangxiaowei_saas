package cn.itcast.dao.cargo;

import cn.itcast.domain.cargo.Coshippingorder;
import cn.itcast.domain.cargo.CoshippingorderExample;

import java.util.List;

public interface CoshippingorderDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table co_shipping_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String shippingOrderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table co_shipping_order
     *
     * @mbg.generated
     */
    int insert(Coshippingorder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table co_shipping_order
     *
     * @mbg.generated
     */
    int insertSelective(Coshippingorder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table co_shipping_order
     *
     * @mbg.generated
     */
    List<Coshippingorder> selectByExample(CoshippingorderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table co_shipping_order
     *
     * @mbg.generated
     */
    Coshippingorder selectByPrimaryKey(String shippingOrderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table co_shipping_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Coshippingorder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table co_shipping_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Coshippingorder record);
}