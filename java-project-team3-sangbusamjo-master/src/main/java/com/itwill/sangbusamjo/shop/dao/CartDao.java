package com.itwill.sangbusamjo.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itwill.sangbusamjo.shop.common.DataSource;
import com.itwill.sangbusamjo.shop.dao.sql.CartSQL;
import com.itwill.sangbusamjo.shop.dao.sql.OrderSQL;
import com.itwill.sangbusamjo.shop.vo.Cart;
import com.itwill.sangbusamjo.shop.vo.Member;
import com.itwill.sangbusamjo.shop.vo.Product;

public class CartDao {
	private DataSource dataSource;

	public CartDao() throws Exception {
		dataSource = new DataSource();
	}

	/*
	 * 로그인한 멤버의 카트 리스트
	 */
	public List<Cart> findByUserId(String m_id) throws Exception {
		List<Cart> cartList = new ArrayList<Cart>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(CartSQL.CART_LIST);
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Cart cart = Cart.builder().cNo(rs.getInt("c_no")).cQty(rs.getInt("c_qty"))
						.member(Member.builder().mId(rs.getString("m_id")).build())
						.product(Product.builder().pNo(rs.getInt("p_no")).pName(rs.getString("p_name"))
								.pPrice(rs.getInt("p_price")).pImage(rs.getString("p_image"))
								.pDesc(rs.getString("p_desc"))

								.build())
						.build();

				cartList.add(cart);

			}

		} finally {
			if (con != null) {
				con.close();
			}
		}

		return cartList;
	}

	/*
	 * 로그인한 멤버의 카트 리스트 전체삭제
	 */

	public int cartListDeleteAll(String m_id) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement(CartSQL.CART_LIST_DELETE_ALL);
			pstmt.setString(1, m_id);

			rowCount = pstmt.executeUpdate();

		} finally {
			if (con != null) {
				con.close();
			}

		}
		return rowCount;
	}

	// 로그인한멤버의 카트에 상품추가
	public int cartInsert(int c_qty, int p_no, String m_id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(CartSQL.CART_INSERT);
			pstmt.setInt(1, c_qty);
			pstmt.setInt(2, p_no);
			pstmt.setString(3, m_id);
			rowCount = pstmt.executeUpdate();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return rowCount;

	}

	/*
	 * 로그인한 멤버의 카트 리스트 선택삭제
	 */

	public int cartListDelete(String m_id, int c_no) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement(CartSQL.CART_LIST_DELETE);
			pstmt.setString(1, m_id);
			pstmt.setInt(2, c_no);
			rowCount = pstmt.executeUpdate();

		} finally {
			if (con != null) {
				con.close();
			}

		}
		return rowCount;
	}

	/*
	 * 로그인한 멤버의 카트 1개 아이템 수량 변경
	 */

	public int cartQtyUpdate(int c_qty, String m_id, int p_no) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(CartSQL.CART_PRODUCT_UPDATE);
			
			pstmt.setInt(1, c_qty);
			pstmt.setString(2, m_id);
			pstmt.setInt(3, p_no);
			rowCount = pstmt.executeUpdate();
			
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return rowCount;
	}

	// 로그인한 멤버의 카트에 존재하는 제품수량증가
	public int CartQtyUpdateU(String m_id, int p_no) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(CartSQL.CART_QTY_UPDATE_U);

			pstmt.setString(1, m_id);
			pstmt.setInt(2, p_no);

			rowCount = pstmt.executeUpdate();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return rowCount;
	}

	// 로그인한 멤버의 카트에 존재하는 제품수량감소

	public int CartQtyUpdateD(String m_id, int p_no) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(CartSQL.CART_QTY_UPDATE_D);
			pstmt.setString(1, m_id);
			pstmt.setInt(2, p_no);
			rowCount = pstmt.executeUpdate();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return rowCount;
	}
	// insert update delete
	// selete 
	/*
	 * 로그인한 멤버의 카트에 존재하는 제품의 수 (제품존재여부판단)
	 */
	public int countByProductNo(String m_id, int p_no) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int abc=0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(CartSQL.CART_PRODUCT_COUNT_EXIST);
			pstmt.setString(1, m_id);
			pstmt.setInt(2, p_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				abc = rs.getInt("p_count");
			}
			
			

		} finally {
			if (con != null) {
				con.close();
			}
		}
		return abc;
	}

	/*
	 * 로그인 한 멤버의 카트 번호로 카트 아이템 찾기
	 */

	public Cart findByCartNo(int c_no) throws Exception {
		Cart cart = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(CartSQL.CART_SELECT_BY_CART_NO);
			pstmt.setInt(1, c_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cart = Cart.builder().cNo(rs.getInt("c_no")).cQty(rs.getInt("c_qty"))
						.member(Member.builder().mId(rs.getString("m_Id")).build())
						.product(Product.builder().pNo(rs.getInt("p_No")).pName(rs.getString("p_name"))
								.pDesc(rs.getString("p_desc")).pImage(rs.getString("p_image"))
								.pPrice(rs.getInt("p_price")).pCategory(rs.getInt("p_Category")).build())
						.build();

			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return cart;
	}

	public int cartDeleteByNo(int c_no) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(CartSQL.CART_LIST_DELETE_NO);
			pstmt.setInt(1, c_no);
			rowCount = pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return rowCount;
	}

	/*
	 * 로그인한 멤버의 카트에 담기(수정)
	 * 
	 * public int updateByCartNo(Cart cart) throws Exception{ Connection con=null;
	 * PreparedStatement pstmt=null; int rowCount=0; try {
	 * con=dataSource.getConnection();
	 * pstmt=con.prepareStatement(CartSQL.CART_UPDATE_BY_CART_NO);
	 * pstmt.setInt(1,cart.getCartQty()); pstmt.setInt(2,cart.getCartNo()); rowCount
	 * = pstmt.executeUpdate(); }finally { if(con!=null) { con.close(); } } return
	 * rowCount; }
	 */
}
