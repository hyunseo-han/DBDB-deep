package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO {
	private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	//user 생성	
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

}
