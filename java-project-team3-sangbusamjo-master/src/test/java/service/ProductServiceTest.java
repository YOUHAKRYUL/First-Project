package service;

import com.itwill.sangbusamjo.shop.service.ProductService;

public class ProductServiceTest {

	public static void main(String[] args)throws Exception {
		ProductService productService=new ProductService();
		System.out.println("1.상품리스트보기");
		System.out.println("  "+productService.ProductList());
		System.out.println("2.상품상세보기");
		System.out.println("  "+productService.ProductDetail(5));
		System.out.println("3.상품이름으로 상세보기");
		System.out.println(productService.ProductSearchName("케이블 나라 니트"));

	}

}
