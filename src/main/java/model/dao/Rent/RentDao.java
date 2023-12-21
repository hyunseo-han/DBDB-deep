package model.dao.Rent;

import java.time.LocalDate;

import model.Product;
import model.Rent;
import model.User;
import model.dao.JDBCUtil;

public class RentDao {
    private JDBCUtil jdbcUtil = null;

    public RentDao() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }
    
    // 빌린 물건 rent 테이블에 추가
    public int addRent(Rent rent) {
        int result = 0;
        
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO RENT (RENTID, STATUS, BORROW_START_DAY, BORROW_END_DAY, PRODUCTID, CUSTOMERID, RENTAL_FEE) "); //
        query.append("VALUES (?, ?, ?, ?, ?, ?, ?)");
        System.out.println("customerId : " + rent.getCstm_id());
        System.out.println("productId : " + rent.getPrdt_id());
        
        
        Object[] params = new Object[]{rent.getRent_id(), rent.getStatus(), rent.getStart_day(), rent.getEnd_day(), rent.getPrdt_id(), rent.getCstm_id(), rent.getRental_fee()};
        
        jdbcUtil.setSqlAndParameters(query.toString(), params);
        
        try {
            result = jdbcUtil.executeUpdate();
            if (result > 0)
                jdbcUtil.commit(); // 트랜잭션 commit 실행
        } catch (Exception ex) {
            System.out.println("대여 실패 ");
            ex.printStackTrace();
            jdbcUtil.rollback();    // 트랜잭션 rollback 실행      
        } finally {          
            jdbcUtil.close();
        }
        return result;
    }
    
//    // 빌린 물건 rent 테이블에 추가
//    public int addRent(Product product, User customer, LocalDate endDate) {
//        int result = 0;
//        LocalDate today = LocalDate.now();
//        
//        StringBuilder query = new StringBuilder();
//        query.append("INSERT INTO RENT (status, borrow_start_day, borrow_end_day, productId, CustomerId, rental_fee "); //
//        query.append("VALUES (?, ?, ?, ?, ?, ?)");
//        
//        Object[] params = new Object[]{1, today, endDate, product.getProductId(), customer.getCustomerId(), product.getRentalFee()};
//        
//        jdbcUtil.setSqlAndParameters(query.toString(), params);
//        
//        try {
//            result = jdbcUtil.executeUpdate();
//            jdbcUtil.commit(); // 트랜잭션 commit 실행
//        } catch (Exception ex) {
//            jdbcUtil.rollback();    // 트랜잭션 rollback 실행
//            ex.printStackTrace();
//        } finally {          
//            jdbcUtil.close();
//        }
//        return result;
//    }
    
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
        return result;
    }
    
    // 반납
    public int modifyProductreturn(Product product) {
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
        return result;
    }
    
    

}
