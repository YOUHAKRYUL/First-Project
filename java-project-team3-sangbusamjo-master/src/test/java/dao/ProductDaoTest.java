package dao;

import com.itwill.sangbusamjo.shop.dao.ProductDao;

public class ProductDaoTest {

	public static void main(String[]args) throws Exception{
		ProductDao productDao = new ProductDao();

		System.out.println(productDao.findAll());
		System.out.println(productDao.findByPrimaryKey(3));
		System.out.println(productDao.findByProductName("케이블 나라 니트"));
		System.out.println(productDao.findByProductCategory(2));
		System.out.println(productDao.findAllProductName("케이블 나라 니트"));
	}

}
