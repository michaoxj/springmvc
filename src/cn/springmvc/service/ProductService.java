package cn.springmvc.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.springmvc.dao.ProductDao;
import cn.springmvc.model.Product;

// ������Ҫ������ʵ��ҵ���߼�
@Component(value="ps")  // <bean id="ps" class="cn.yd.oa.service.ProductService">
public class ProductService {
	@Resource(name="pd")
	private ProductDao productDao = null;
	
	public void save(Product product) {
		// �˴�����ʵ��ҵ���߼�����,���������Ʒ
		productDao.save(product);
		// �����־
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
