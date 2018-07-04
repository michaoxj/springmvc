package cn.springmvc.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.springmvc.model.Product;
import cn.springmvc.service.ProductService;

@Component //此类应该交给spring管理,前端是通过url地址访问而非id因此可以不用指定名称
@RequestMapping("/product") //访问当前controller的地址: /工程名/product




public class ProductController {
	@Resource(name="ps")
	private ProductService productService;
	@Resource
	private HttpServletRequest request;
	@Resource
	private HttpSession session;
	
	@RequestMapping("/save") 
	public String save(Product product) {
		productService.save(product);
		return "redirect:/query.jsp";
	}
	
	@RequestMapping("/query")
	public String queryByName(String keyword) {
		ArrayList<Product> prolist = productService.queryByName(keyword);
		request.setAttribute("proList", prolist);
		session.setAttribute("keyword", keyword);
		return "forward:/query.jsp";			
	}
	
	@RequestMapping("/delete")
	public String delete(Integer id) {
		// 调用业务逻辑
		productService.delete(id);
		String keyword = (String)session.getAttribute("keyword");
		request.setAttribute("proList", productService.queryByName(keyword));
		return "forward:/query.jsp";
	}
	@RequestMapping("/getById")
	public String getById(Integer id) {
		Product product = productService.getById(id);
		request.setAttribute("product", product);
		return "forward:/update.jsp";
	}
	
	@RequestMapping("/update")
	public String update(Product product) {
		productService.update(product);
		return "redirect:/query.jsp";
	}
	
//	public void delete1(Integer id) {
//		
//	}
	
	
	
}
