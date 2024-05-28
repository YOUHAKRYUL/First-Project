package com.itwill.sangbusamjo.shop.service;

import java.util.List;

import com.itwill.sangbusamjo.shop.dao.ProductDao;
import com.itwill.sangbusamjo.shop.vo.Product;

public class ProductService {
	private ProductDao productDao;
	public ProductService() throws Exception{
		productDao=new ProductDao();
	}

	/*
	 * 전체상품보기
	 */
	public List<Product> ProductList() throws Exception{
		return productDao.findAll();
	}
	/*
	 * 상품번호로 상세보기
	 */
	public Product ProductDetail(int p_no) throws Exception{
		return productDao.findByPrimaryKey(p_no);
	}
	/*
	 * 상품이름으로 찾기
	 */
	public List<Product> ProductSearchName(String p_name) throws Exception{
		return productDao.findAllProductName(p_name);
	}
}
