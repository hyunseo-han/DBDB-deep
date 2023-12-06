package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class UserDAO {
	private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}

	//회원가입
	//?9개로 수정
	//매너스코어 빼먹음
	public int create(User user) throws SQLException {
	    String sql = "INSERT INTO CUSTOMER VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";       
	    Object[] param = new Object[] {user.getCustomerId(), user.getName(), user.getPasswd(), user.getAddress(), user.getEmail(), user.getPhone(), user.getNickname(), user.getManner_score(), user.getBirth_date()};                
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
    
    //여기 수정하기 Customer -> User
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
	
	//마이페이지 개발 하면
	//update
	//회원 탈퇴 개발
}
