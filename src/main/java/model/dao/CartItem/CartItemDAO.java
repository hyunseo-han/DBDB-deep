package model.dao.CartItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CartItem;
import model.dao.JDBCUtil;

public class CartItemDAO {
    private JDBCUtil jdbcUtil = null;

    public CartItemDAO() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }

//  고객 장바구니 조회 
    public List<CartItem> getCartItem(int customerId) throws SQLException {

        String sql = "SELECT * FROM CARTITEM WHERE CUSTOMERID = ?";

        jdbcUtil.setSqlAndParameters(sql, new Object[] { customerId });

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<CartItem> items = new ArrayList<>();
            while (rs.next()) {
                CartItem item = new CartItem(rs.getInt("CUSTOMERID"), rs.getInt("PRODUCTID"), rs.getInt("QUANTITY"),
                        rs.getInt("RENTAL_FEE"));
                items.add(item);
            }
            return items;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }

//    장바구니 아이템 저장 
    public int addCartItem(CartItem item) throws SQLException {
        String sql = "INSERT INTO CartItem (customerId, productId, quantity, rental_fee) VALUES (?, ?, ?, ?)";
        Object[] param = new Object[] {item.getCustomerId(), item.getProductId(), item.getQuantity(), item.getRentalFee()};
       
        jdbcUtil.setSqlAndParameters(sql, param);

        try {
            int result = jdbcUtil.executeUpdate();
            System.out.println(result+"%%%%%%%%%%%%%%%");
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
        String sql = "DELETE FROM CARTITEM WHERE CUSTOMERID = ? AND PRODUCTID = ?";
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
