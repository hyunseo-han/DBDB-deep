package model.dao.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;
import model.User;
import model.dao.JDBCUtil;

public class CustomerDAO {
    private JDBCUtil jdbcUtil = null;

    public CustomerDAO() {
        jdbcUtil = new JDBCUtil();
    }
    
    //회원가입
    public int create(Customer customer) throws SQLException {
        String sql = "INSERT INTO CUSTOMER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";      
        Object[] param = new Object[] {customer.getCustomerId(), customer.getPasswd(), 
                        customer.getName(), customer.getEmail(), customer.getPhone(), 
                        customer.getAddress(), customer.getNickname(), customer.getBirth_date(), };              
        jdbcUtil.setSqlAndParameters(sql, param);
                        
        try {               
            int result = jdbcUtil.executeUpdate();  // insert 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {     
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }       
        return 0;           
    }
    
    //로그인 (해당 회원이 있는지 판별)
    public boolean login(String email, String passwd) {
        boolean result = false;
        String sql = "SELECT * FROM CUSTOMER  WHERE email = ? AND passwd =?";
        Object[] param = new Object[] {email, passwd};
        
        jdbcUtil.setSqlAndParameters(sql, param);
        
        try {
             
            ResultSet rs = jdbcUtil.executeQuery(); 
            if (rs.next()) {
               result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    //프로필 수정
    public int update(Customer customer) throws SQLException {
        String sql = "UPDATE CUSTOMER "
                    + "SET passwd=?, name=?, email=?, phone=?, address=?, nickname=? "
                    + "WHERE customerId=?";
        Object[] param = new Object[] {customer.getPasswd(), customer.getName(), 
                    customer.getEmail(), customer.getPhone(), 
                    customer.getAddress(), customer.getNickname(), 
                    customer.getCustomerId()};              
        jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil에 update문과 매개 변수 설정
            
        try {               
            int result = jdbcUtil.executeUpdate();  // update 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }       
        return 0;
    }
    
    //회원 탈퇴
    public int remove(String customerId) throws SQLException {
        String sql = "DELETE FROM CUSTOMER WHERE customerId=?";     
        jdbcUtil.setSqlAndParameters(sql, new Object[] {customerId}); 

        try {               
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close(); 
        }       
        return 0;
    }
    
    //customerId에 해당하는 회원이 존재하는지 판단
    public boolean existingUser(int customerId) throws SQLException {
        String sql = "SELECT count(*) FROM CUSTOMER WHERE customerId=?";      
        jdbcUtil.setSqlAndParameters(sql, new Object[] {customerId});   
        try {
            ResultSet rs = jdbcUtil.executeQuery(); 
            if (rs.next()) {
                int count = rs.getInt(1);
                return (count == 1 ? true : false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return false;
    }
}
