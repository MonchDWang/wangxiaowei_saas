package cn.itcast.service.cargo;



import cn.itcast.domain.cargo.Factory;
import cn.itcast.domain.cargo.FactoryExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 */
public interface FactoryService {

	/**
	 * 保存
	 */
	void save(Factory factory);

	/**
	 * 更新
	 */
	void update(Factory factory);

	/**
	 * 删除
	 */
	void delete(String id);

	/**
	 * 根据id查询
	 */
	Factory findById(String id);

	//查询所有
	public List<Factory> findAll(FactoryExample example);

	PageInfo findPageAll(int pageNum, int pageSize);
}
