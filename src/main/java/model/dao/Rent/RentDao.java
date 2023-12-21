package model.dao.Rent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Product;
import model.Rent;
import model.User;
import model.dao.JDBCUtil;

public class RentDao {
    private JDBCUtil jdbcUtil = null;

    public RentDao() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }
    
    
    
    
    public int addRent(Rent rent) {
        int result = -1;
        
        String query = "INSERT INTO RENT (STATUS, BORROW_START_DAY, BORROW_END_DAY, PRODUCTID, CUSTOMERID, RENTAL_FEE) VALUES (?, ?, ?, ?, ?, ?)";
        String maxQuery = "SELECT MAX(rentId) FROM RENT";
        
        Object[] params = new Object[]{rent.getStatus(), rent.getStart_day(), rent.getEnd_day(), rent.getPrdt_id(), rent.getCstm_id(), rent.getRental_fee()};
        
        jdbcUtil.setSqlAndParameters(query.toString(), params);
        
        try {
            result = jdbcUtil.executeUpdate();          
            if (result > 0) {
                jdbcUtil.commit(); // 트랜잭션 commit 실행

                // 새로 생성된 rentId를 가져오는 코드
                jdbcUtil.setSqlAndParameters(maxQuery, null);
                ResultSet res = jdbcUtil.executeQuery();
                if(res.next()) {
                    result = res.getInt(1);
                }               
            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            jdbcUtil.rollback();    // 트랜잭션 rollback 실행      
        } finally {          
            jdbcUtil.close();
        }
        return -1; // 실패했을 때 -1 반환
    }

    
    
    // 대여 내역 날짜 출력을 위해...
    public List<Rent> getDateById(int productId) throws SQLException {
        List<Rent> rents = new ArrayList<>();
        String sql = "SELECT * FROM RENT WHERE PRODUCTID = ?";

        jdbcUtil.setSqlAndParameters(sql, new Object[] { productId });

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                Rent rent = new Rent(
                    rs.getInt("RENTID"),
                    rs.getInt("CUSTOMERID"),
                    rs.getInt("PRODUCTID"),
                    rs.getInt("STATUS"),
                    rs.getDate("BORROW_START_DAY").toLocalDate(),
                    rs.getDate("BORROW_END_DAY").toLocalDate(),
                    rs.getInt("RENTAL_FEE")
                );
                rents.add(rent);
            }
        } finally {
            jdbcUtil.close();
        }
        Collections.sort(rents, Comparator.comparing(Rent::getStart_day));
        return rents;
    }
    
    public Rent findRentById(int rentId) throws SQLException {
        Rent rent = null;
        String sql = "SELECT * FROM RENT WHERE RENTID = ?";
        
        jdbcUtil.setSqlAndParameters(sql, new Object[] { rentId });
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            
            if (rs.next()) {
                rent = new Rent(
                    rs.getInt("RENTID"),
                    rs.getInt("CUSTOMERID"),
                    rs.getInt("PRODUCTID"),
                    rs.getInt("STATUS"),
                    rs.getDate("BORROW_START_DAY").toLocalDate(),
                    rs.getDate("BORROW_END_DAY").toLocalDate(),
                    rs.getInt("RENTAL_FEE"));    
            } 
            return rent;
        } finally {
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
