package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.RentInfo;

public class OrderDAO {
    private JDBCUtil jdbcUtil = null;
    
    public OrderDAO() {          
        jdbcUtil = new JDBCUtil();  // JDBCUtil 객체 생성
    }
    
    // 고객이 빌린 물품 조회
    public List<RentInfo> getrentInfo(int customerId) throws SQLException {
    	String sql = "SELECT R.RENTID, R.CUSTOMERID, R.PRODUCTID, R.STATUS, " +
                "R.BORROW_START_DAY as START_DAY, R.BORROW_END_DAY as END_DAY, R.RENTAL_FEE, " +
                "P.TITLE, P.PRODUCT_PHOTO as PHOTO, P.ADDRESS, " +
                "C.MANNER_SCORE " +
                "FROM RENT R " +
                "JOIN PRODUCT P ON R.PRODUCTID = P.PRODUCTID " +
                "JOIN CUSTOMER C ON R.CUSTOMERID = C.CUSTOMERID " +
                "WHERE R.CUSTOMERID = ?";


        Object[] param = new Object[] { customerId };
        jdbcUtil.setSqlAndParameters(sql, param);
        List<RentInfo> rentList = new ArrayList<>();

       System.out.println("OrderDAO에서 확인한 custoemerId: " + customerId);

        
        try {        	
            ResultSet rs = jdbcUtil.executeQuery();

            while (rs.next()) {
                RentInfo rent = new RentInfo(
                    rs.getInt("RENTID"),
                    rs.getInt("CUSTOMERID"),
                    rs.getInt("PRODUCTID"),
                    rs.getInt("STATUS"),
                    rs.getDate("START_DAY") != null ? rs.getDate("START_DAY").toLocalDate() : null,
                    rs.getDate("END_DAY") != null ? rs.getDate("END_DAY").toLocalDate() : null,
                    rs.getInt("RENTAL_FEE"),
                    rs.getString("TITLE"),
                    rs.getString("PHOTO"),
                    rs.getString("ADDRESS"),
                    rs.getInt("MANNER_SCORE")
                );
                rentList.add(rent);
            }
            System.out.println("실행이 되는갸?");
        } finally {
            jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 닫기
        }
        return rentList;
    }
    
    public int getMannerScore(int customerId) throws SQLException {
        String sql = "SELECT MANNER_SCORE FROM CUSTOMER WHERE CUSTOMERID = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { customerId });
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                return rs.getInt("MANNER_SCORE");
            } else {
                return 0; // 기본값으로 0을 반환할 수 있음
            }
        } finally {
            jdbcUtil.close();
        }
    }
    
    public void saveMannerScore(int customerId, int additionalScore) throws SQLException {
        // 먼저 기존의 매너 점수를 가져옵니다.
        int currentScore = getMannerScore(customerId);
        int newScore = currentScore + additionalScore; // 기존 점수에 새로운 점수를 더합니다.

        // 새로운 점수로 매너 점수를 업데이트합니다.
        String updateSql = "UPDATE CUSTOMER SET MANNER_SCORE = ? WHERE CUSTOMERID = ?";
        Object[] updateParams = new Object[] { newScore, customerId };

        jdbcUtil.setSqlAndParameters(updateSql, updateParams);

        try {
            int count = jdbcUtil.executeUpdate();
            if (count == 0) {
                throw new SQLException("매너 점수 업데이트 실패: CustomerID " + customerId + "를 찾을 수 없습니다.");
            }
            System.out.println("매너점수 업뎃" + newScore + "로 개같이 성공 ");
			jdbcUtil.commit();
        } catch (Exception e) {
			// TODO Auto-generated catch block
        	jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
            jdbcUtil.close(); // 자원 해제
        }
    }

    public void updateProductStatus(int productId, int status) throws SQLException {
        String sql = "UPDATE RENT SET STATUS = ? WHERE PRODUCTID = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { status, productId });
        
        try {
            jdbcUtil.executeUpdate();
            System.out.println("상태 " +status + "으로 업뎃 개같이 성공 ");			
            jdbcUtil.commit();

        } catch (Exception e) {
			// TODO Auto-generated catch block
        	jdbcUtil.rollback();
			e.printStackTrace();
			System.out.println("개같이 에러");
		} finally {
            jdbcUtil.close();
        }
    }
}
