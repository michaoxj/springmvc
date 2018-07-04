package cn.springmvc.dao;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import cn.springmvc.model.Product;

// dao是数据访问层. Java中一个子类只有一个父类(super)
@Component(value = "pd") // 不<bean id="pd" class="cn.yd.oa.dao.ProductDao">
public class ProductDao {
	
	// 依赖,应该Spring-bean.xml中通过set方法赋值
	@Resource(name="jt")  // 依赖 <bean id="jt" class="org.springframework.jdbc.core.JdbcTemplate">
	private JdbcTemplate jdbcTemplate = null;

//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}

	// ctrl + shift +o 导入导出包
	public ArrayList<Product> queryByName(String name) {
		String sql = "select * from product where name like ?";
		return (ArrayList<Product>)jdbcTemplate
				.query(sql, new Object[] {"%" + name + "%"}, 
						new BeanPropertyRowMapper<Product>(Product.class));
	}
	
//	ResultSet rs = pre.executeQuery();
//	// rs对象具有指向其当前数据行的光标。最初，光标被置于第一行之前。
//	// next 方法将光标移动到下一行,如果当前行有效则返回为true
//	if(rs.next()) {
//	    product = new Product();
//		// db中一条记录等于Java中的一个对象
//		product.setRemark(rs.getString("remark"));
//	}

	// 编写一个根据ID查询对象的方法
	public Product getById(Integer id) {
		String sql = "select * from product where id = ?";
		// BeanPropertyRowMapper: spring提供的属性映射工具类(如果字段与属性相同则数据自动匹配)
		// <Product> 代表的是返回的类型, Product.class: 参数自动赋值对象类型
		return jdbcTemplate.queryForObject(sql,new Object[] {id},
				new BeanPropertyRowMapper<Product>(Product.class));
	}

	public void delete(Integer id) {
		// ?称为占位符
		String sql = "delete from product where id = ?";
		jdbcTemplate.update(sql, new Object[] { id });
	}

	// 编写一个方法,完成数据的插入操作
	public void update(Product product) {
		String sql = "update product set name=?,price=?,remark=? where id = ?";
		Object[] param = new Object[] { product.getName(), product.getPrice(), product.getRemark(), product.getId() };
		jdbcTemplate.update(sql, param);
	}

	// 编写一个方法,完成数据的插入操作
	public void save(Product product) {
		// ?称为占位符
		String sql = "insert into product (name,price,remark) values (?,?,?)";
		jdbcTemplate.update(sql, new Object[] { product.getName(), product.getPrice(), product.getRemark() });
	}
}
