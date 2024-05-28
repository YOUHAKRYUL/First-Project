package com.itwill.sangbusamjo.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itwill.sangbusamjo.shop.common.DataSource;
import com.itwill.sangbusamjo.shop.dao.sql.ProductSQL;
import com.itwill.sangbusamjo.shop.vo.Product;

public class ProductDao {
	private DataSource dataSource;
	public ProductDao() throws Exception{
		dataSource=new DataSource();
	}

	/*
	 * 상품 전체 리스트
	 */
	public List<Product> findAll() throws Exception{
		List<Product> productList=new ArrayList<>();

		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(ProductSQL.PRODUCT_SELECT_ALL);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			Product product=new Product(
								rs.getInt("p_no"),
								rs.getString("p_name"),
								rs.getString("p_desc"),
								rs.getString("p_image"),
								rs.getInt("p_price"),
								rs.getInt("p_category"));
			productList.add(product);
		}
		return productList;
	}

	/*
	 * 상품번호로 검색
	 */
	public Product findByPrimaryKey(int p_no) throws Exception{
		Product product=null;
		Connection con=dataSource.getConnection();


		PreparedStatement pstmt=con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_NO);
		pstmt.setInt(1, p_no);

		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			product = new Product(rs.getInt("p_no"),
					  rs.getString("p_name"),
					  rs.getString("p_desc"),
					  rs.getString("p_image"),
					  rs.getInt("p_price"),
					  rs.getInt("p_category"));
		}
		return product;
	}
	/*
	 * 상품이름으로 검색
	 */
	public Product findByProductName(String p_name) throws Exception{
		Product product=null;
		Connection con=dataSource.getConnection();

		PreparedStatement pstmt=con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_NAME);
		pstmt.setString(1,"케이블 나라 니트");
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			product = new Product(rs.getInt("p_no"),
								  rs.getString("p_name"),
								  rs.getString("p_desc"),
								  rs.getString("p_image"),
								  rs.getInt("p_price"),
								  rs.getInt("p_category"));
		}
		return product;
	}
	/*
	 * 상품리스트 이름으로 검색
	 */
	public List<Product> findAllProductName(String p_name) throws Exception{
		List<Product> productList=new ArrayList<>();

		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_NAME);
		pstmt.setString(1, p_name);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			Product product=new Product(
								rs.getInt("p_no"),
								rs.getString("p_name"),
								rs.getString("p_desc"),
								rs.getString("p_image"),
								rs.getInt("p_price"),
								rs.getInt("p_category"));
			productList.add(product);
		}
		return productList;
	}
	/*
	 * 상품분류로 검색
	 */
	public Product findByProductCategory(int p_category) throws Exception{
		Product product=null;
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_CATEGORY);
		pstmt.setInt(1,p_category);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			product = new Product(rs.getInt("p_no"),
					  			  rs.getString("p_name"),
					  			  rs.getString("p_desc"),
					  			  rs.getString("p_image"),
					  			  rs.getInt("p_price"),
					  			  rs.getInt("p_category"));
		}
		return product;
	}
}