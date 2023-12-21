package model.dao.CartItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CartItem;
import model.Product;
import model.dao.JDBCUtil;

public class CartItemDAO {
    private JDBCUtil jdbcUtil = null;

    public CartItemDAO() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }

//  고객 장바구니 조회              
    public List<Product> getCartItem(int customerId) throws SQLException {
    	List<Product> list = new ArrayList<>(); 
    	    
	    String sql = "SELECT * "
	    		+ "FROM cartItem c "
	    		+ "INNER JOIN product p ON c.productId = p.productId "
	    		+ "WHERE c.customerId = ?";
    	    
        jdbcUtil.setSqlAndParameters(sql, new Object[] { customerId });

        try {
        	 ResultSet rs = jdbcUtil.executeQuery();

             while (rs.next()) {
                 Product product = new Product(
                		 rs.getInt("productId"),
                         rs.getInt("regular_price"),
                         rs.getInt("rental_fee"),
                         rs.getString("description"),
                         rs.getInt("deposit"),
                         rs.getString("product_photo"),
                         rs.getString("address"),
                         rs.getString("detail_address"),
                         rs.getBoolean("is_borrowed"),
                         rs.getInt("customerId"),
                         rs.getString("title"),
                         rs.getString("category")
                 );
                list.add(product);
            }
         
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }   
        return list;
    }
    
//    장바구니 아이템 저장 
    public int addCartItem(CartItem item) throws SQLException {
        String sql = "INSERT INTO CartItem (customerId, productId, quantity, rental_fee) VALUES (?, ?, ?, ?)";
        Object[] param = new Object[] {item.getCustomerId(), item.getProductId(), item.getQuantity(), item.getRentalFee()};
       
        jdbcUtil.setSqlAndParameters(sql, param);

        try {
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return 0;
    }

//    장바구니 아이템 삭제 
    public int deleteCartItem(int customerId, int productId) throws SQLException {
        String sql = "DELETE FROM cartItem WHERE customerId = ? AND productId = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {customerId, productId});

        try {
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return 0;
    }

}
