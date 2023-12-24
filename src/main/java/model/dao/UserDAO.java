package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class UserDAO {
	private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {			
		jdbcUtil = new JDBCUtil();	
	}

	//회원가입
	public int create(User user) throws SQLException {
	    String sql = "INSERT INTO CUSTOMER (customerId, name, passwd, address, email, phone, nickname, manner_score, birth_date) VALUES (CUSTOMER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";       
	    Object[] param = new Object[] {user.getName(), user.getPasswd(), user.getAddress(), user.getEmail(), user.getPhone(), user.getNickname(), user.getManner_score(), user.getBirth_date()};                
	    jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil에 insert문과 매개 변수 설정
	                    
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
	
	//중복확인
	public boolean isEmailDuplicate(String email) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM CUSTOMER WHERE email=?";
	    jdbcUtil.setSqlAndParameters(sql, new Object[] {email});  
	    try {
	        ResultSet rs = jdbcUtil.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } finally {
	        jdbcUtil.close();
	    }
	    return false;
	}

	public boolean isNicknameDuplicate(String nickname) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM CUSTOMER WHERE nickname=?";
	    jdbcUtil.setSqlAndParameters(sql, new Object[] {nickname});  
	    try {
	        ResultSet rs = jdbcUtil.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } finally {
	        jdbcUtil.close();
	    }
	    return false;
	}

	
	//로그인 (해당 회원이 있는지 판별)
	public boolean login(String email, String passwd) {
        boolean result = false;
        String sql = "SELECT * FROM CUSTOMER WHERE email = ? AND passwd =?";
        Object[] param = new Object[] {email, passwd};
        
        jdbcUtil.setSqlAndParameters(sql, param);
        
        try {
            ResultSet rs = jdbcUtil.executeQuery(); 
            if (rs.next()) {
               result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }  
        
        return result;
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
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return false;
    } 
    
    public User findUserByEmail(String email) throws SQLException {
        User user = null;
        
        String sql = "SELECT * FROM CUSTOMER WHERE email = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {email});
        try {
            ResultSet rs = jdbcUtil.executeQuery(); 
            if (rs.next()) {
                user = new User(
                        rs.getInt("customerId"),
                        rs.getString("name"),
                        rs.getString("passwd"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("nickname"),
                        rs.getInt("manner_score"),
                        rs.getDate("birth_date"));  
                return user;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return null;
    }
    
    // 물건 아이디로 주인 찾기
   public User findUserById(int productId) throws SQLException {
       String query = "SELECT * FROM CUSTOMER c, PRODUCT p WHERE c.customerId = p.customerId and productId = ?";
       Object[] param = new Object[]{productId};
       User user = null;
       
       jdbcUtil.setSqlAndParameters(query, param);
       
       try {
           ResultSet rs = jdbcUtil.executeQuery();
           
           if(rs.next()) {
               user = new User();       
               user.setName(rs.getString("name"));
               user.setManner_score(rs.getInt("manner_score"));
           } 
           
           return user;
       } catch (Exception ex) {
           ex.printStackTrace();
           return user;
       }finally {
           jdbcUtil.close();
       }
   }
}
