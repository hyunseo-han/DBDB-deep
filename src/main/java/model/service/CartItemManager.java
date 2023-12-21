package model.service;

import model.dao.CartItem.CartItemDAO;
import model.CartItem;

import java.sql.SQLException;
import java.util.List;

public class CartItemManager {
	private CartItemDAO cartItemDao;
	private static CartItemManager cartItemManager = new CartItemManager();
	
	public CartItemManager() {
		cartItemDao = new CartItemDAO();
	}
	
	public static CartItemManager getInstance() {
		return cartItemManager;
	}
	
	//장바구니 조회
	public List<CartItem> getCartItem(int customerId){
		try {
			return cartItemDao.getCartItem(customerId);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 장바구니 물품 추가
    public boolean addCartItem(CartItem item) throws SQLException, Exception {
        try {
            return cartItemDao.addCartItem(item) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
 // 장바구니 물품 삭제
    public boolean deleteCartItem(int customerId, int productId) {
        try {
            return cartItemDao.deleteCartItem(customerId, productId) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
