package com.itwill.sangbusamjo.shop.service;

import java.util.List;

import com.itwill.sangbusamjo.shop.dao.CartDao;
import com.itwill.sangbusamjo.shop.vo.Cart;

public class CartService {
	private CartDao cartDao;

	public CartService() throws Exception {
		cartDao = new CartDao();
	}

	/*
	 * 카트보기
	 */
	public List<Cart> getCartItemByUserId(String m_Id) throws Exception {
		return cartDao.findByUserId(m_Id);
	}

	// 카트추가
	public int addCart_(int c_qty, int p_no, String m_id) throws Exception {
		if (cartDao.countByProductNo(m_id, p_no) > 0) {
			return cartDao.cartQtyUpdate(c_qty, m_id, p_no);
		} else {
			return cartDao.cartInsert(c_qty, p_no, m_id);
		}
		
	}
	
	
	// 카트추가
		public int addCart(int c_qty, int p_no, String m_id) throws Exception {
				return cartDao.cartInsert(c_qty, p_no, m_id);
			
		}

	// 카트 수량수정
	public int updateCart(int c_qty, String m_id, int p_no) throws Exception {
		return cartDao.cartQtyUpdate(c_qty, m_id, p_no);
	}

	// 카트 수량증가
	public int CartItemQtyUpdateU(String m_id, int p_no) throws Exception {
		return cartDao.CartQtyUpdateU(m_id, p_no);
	}

	// 카트 수량감소
	public int CartItemQtyUpdateD(String m_id, int p_no) throws Exception {
		return cartDao.CartQtyUpdateD(m_id, p_no);
	}

	// 카트리스트 전체삭제
	public int deleteCartItemByUserId(String m_id) throws Exception {
		return cartDao.cartListDeleteAll(m_id);
	}

	// 카트리스트 선택삭제
	public int deleteCartItemByCartNo(String m_id, int c_no) throws Exception {
		return cartDao.cartListDelete(m_id, c_no);
	}

	/*
	 * 로그인한 멤버의 카트번호로 카트 리스트 선택삭제
	 */
	public int deleteCartItemByNo(int c_no) throws Exception {
		return cartDao.cartDeleteByNo(c_no);
	}

}
