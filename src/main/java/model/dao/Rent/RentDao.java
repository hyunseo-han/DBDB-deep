package model.dao.Rent;

import java.time.LocalDate;

import model.dao.JDBCUtil;

public class RentDao {
    private JDBCUtil jdbcUtil = null;

    public RentDao() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }
    
    // 빌린 물건 rent 테이블에 추가
    public int addRent(Product product, Customer customer, LocalDate endDate) {
        int result = 0;
        LocalDate today = LocalDate.now();
        
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO RENT "); //
        query.append("VALUES (?, ?, ?, ?, ?, ?)");
        
        Object[] params = new Object[]{1, today, endDate, product.getProductId(), customer.getCustomerId(), product.getRentalFee()};
        
        jdbcUtil.setSqlAndParameters(query.toString(), params);
        
        try {
            result = jdbcUtil.executeUpdate();           
        } catch (Exception ex) {
            jdbcUtil.rollback();    // 트랜잭션 rollback 실행
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();      // 트랜잭션 commit 실행
            jdbcUtil.close();
        }
    }
    
    // 빌려진 물건 상태 변경 (대여됨)
    public int modifyProductrent(Product product) {
        int result = 0;
        String update = "UPDATE PRODUCT SET is_borrowed = 1 WHERE productId = ?";
        jdbcUtil.setSqlAndParameters(update, new Object[] {product.getProductId()});
        
        try {
            result = jdbcUtil.executeUpdate();           
        } catch (Exception ex) {
            jdbcUtil.rollback();    // 트랜잭션 rollback 실행
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();      // 트랜잭션 commit 실행
            jdbcUtil.close();
        }
    }
    
    // 반납
    public int modifyProductreturn(ProductDto product) {
        int result = 0;
        String update = "UPDATE PRODUCT SET is_borrowed = 0 WHERE productId = ?";
        jdbcUtil.setSqlAndParameters(update, new Object[] {product.getProductId()});
        
        try {
            result = jdbcUtil.executeUpdate();           
        } catch (Exception ex) {
            jdbcUtil.rollback();    // 트랜잭션 rollback 실행
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();      // 트랜잭션 commit 실행
            jdbcUtil.close();
        }
    }
    
    

}
