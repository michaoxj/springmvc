package cn.springmvc.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.springmvc.dao.ProductDao;
import cn.springmvc.model.Product;

// 此类主要是用来实现业务逻辑
@Component(value="ps")  // <bean id="ps" class="cn.yd.oa.service.ProductService">
public class ProductService {
	@Resource(name="pd")
	private ProductDao productDao = null;
	
	public void save(Product product) {
		// 此处用来实现业务逻辑代码,例如添加商品
		productDao.save(product);
		// 添加日志
//		Integer.parseInt("loginfo.............");
	}
	
	public ArrayList<Product> queryByName(String keyword){
		return productDao.queryByName(keyword);
	}
	
	public void delete(Integer id) {
		productDao.delete(id);
	}
	public void update(Product product) {
		productDao.update(product);
	}
	
	public Product getById(Integer id) {
		return productDao.getById(id);
	}
	

	
	
}
