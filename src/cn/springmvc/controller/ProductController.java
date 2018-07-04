package cn.springmvc.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.springmvc.model.Product;
import cn.springmvc.service.ProductService;

@Component //����Ӧ�ý���spring����,ǰ����ͨ��url��ַ���ʶ���id��˿��Բ���ָ������
@RequestMapping("/product") //���ʵ�ǰcontroller�ĵ�ַ: /������/product




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
		// ����ҵ���߼�
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
