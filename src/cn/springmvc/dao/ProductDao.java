package cn.springmvc.dao;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import cn.springmvc.model.Product;

// dao�����ݷ��ʲ�. Java��һ������ֻ��һ������(super)
@Component(value = "pd") // ��<bean id="pd" class="cn.yd.oa.dao.ProductDao">
public class ProductDao {
	
	// ����,Ӧ��Spring-bean.xml��ͨ��set������ֵ
	@Resource(name="jt")  // ���� <bean id="jt" class="org.springframework.jdbc.core.JdbcTemplate">
	private JdbcTemplate jdbcTemplate = null;

//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}

	// ctrl + shift +o ���뵼����
	public ArrayList<Product> queryByName(String name) {
		String sql = "select * from product where name like ?";
		return (ArrayList<Product>)jdbcTemplate
				.query(sql, new Object[] {"%" + name + "%"}, 
						new BeanPropertyRowMapper<Product>(Product.class));
	}
	
//	ResultSet rs = pre.executeQuery();
//	// rs�������ָ���䵱ǰ�����еĹ�ꡣ�������걻���ڵ�һ��֮ǰ��
//	// next ����������ƶ�����һ��,�����ǰ����Ч�򷵻�Ϊtrue
//	if(rs.next()) {
//	    product = new Product();
//		// db��һ����¼����Java�е�һ������
//		product.setRemark(rs.getString("remark"));
//	}

	// ��дһ������ID��ѯ����ķ���
	public Product getById(Integer id) {
		String sql = "select * from product where id = ?";
		// BeanPropertyRowMapper: spring�ṩ������ӳ�乤����(����ֶ���������ͬ�������Զ�ƥ��)
		// <Product> ������Ƿ��ص�����, Product.class: �����Զ���ֵ��������
		return jdbcTemplate.queryForObject(sql,new Object[] {id},
				new BeanPropertyRowMapper<Product>(Product.class));
	}

	public void delete(Integer id) {
		// ?��Ϊռλ��
		String sql = "delete from product where id = ?";
		jdbcTemplate.update(sql, new Object[] { id });
	}

	// ��дһ������,������ݵĲ������
	public void update(Product product) {
		String sql = "update product set name=?,price=?,remark=? where id = ?";
		Object[] param = new Object[] { product.getName(), product.getPrice(), product.getRemark(), product.getId() };
		jdbcTemplate.update(sql, param);
	}

	// ��дһ������,������ݵĲ������
	public void save(Product product) {
		// ?��Ϊռλ��
		String sql = "insert into product (name,price,remark) values (?,?,?)";
		jdbcTemplate.update(sql, new Object[] { product.getName(), product.getPrice(), product.getRemark() });
	}
}
