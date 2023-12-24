package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.RentInfo;

public class OrderDAO {
	private JDBCUtil jdbcUtil = null;

	public OrderDAO() {
		jdbcUtil = new JDBCUtil(); 
	}

	// 고객이 빌린 물품 조회
	public List<RentInfo> getrentInfo(int customerId) throws SQLException {
		String sql = "SELECT R.RENTID, R.CUSTOMERID, R.PRODUCTID, R.STATUS, "
				+ "R.BORROW_START_DAY as START_DAY, R.BORROW_END_DAY as END_DAY, R.RENTAL_FEE, "
				+ "P.TITLE, P.PRODUCT_PHOTO as PHOTO, P.ADDRESS, " + "C.MANNER_SCORE " + "FROM RENT R "
				+ "JOIN PRODUCT P ON R.PRODUCTID = P.PRODUCTID " + "JOIN CUSTOMER C ON R.CUSTOMERID = C.CUSTOMERID "
				+ "WHERE R.CUSTOMERID = ?";

		Object[] param = new Object[] { customerId };
		jdbcUtil.setSqlAndParameters(sql, param);
		List<RentInfo> rentList = new ArrayList<>();

		try {
			ResultSet rs = jdbcUtil.executeQuery();

			while (rs.next()) {
				RentInfo rent = new RentInfo(rs.getInt("RENTID"), rs.getInt("CUSTOMERID"), rs.getInt("PRODUCTID"),
						rs.getInt("STATUS"),
						rs.getDate("START_DAY") != null ? rs.getDate("START_DAY").toLocalDate() : null,
						rs.getDate("END_DAY") != null ? rs.getDate("END_DAY").toLocalDate() : null,
						rs.getInt("RENTAL_FEE"), rs.getString("TITLE"), rs.getString("PHOTO"), rs.getString("ADDRESS"),
						rs.getInt("MANNER_SCORE"));
				rentList.add(rent);
			}
		} finally {
			jdbcUtil.close(); 
		}
		return rentList;
	}

	public int getMannerScore(int productId) throws SQLException {
		String sql = "SELECT C.MANNER_SCORE FROM CUSTOMER C " + "JOIN PRODUCT P ON C.CUSTOMERID = P.CUSTOMERID "
				+ "WHERE P.PRODUCTID = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { productId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				return rs.getInt("MANNER_SCORE");
			} else {
				return 0; // 매너 점수가 없는 경우 기본값으로 0을 반환
			}
		} finally {
			jdbcUtil.close();
		}
	}
	
	private int getOwnerIdByProductId(int productId) throws SQLException {
	    String sql = "SELECT CUSTOMERID FROM PRODUCT WHERE PRODUCTID = ?";
	    jdbcUtil.setSqlAndParameters(sql, new Object[] { productId });
	    
	    try {
	        ResultSet rs = jdbcUtil.executeQuery();
	        if (rs.next()) {
	            return rs.getInt("CUSTOMERID");
	        } else {
	            throw new SQLException("ProductId " + productId + "에 해당하는 주인을 찾을 수 없습니다.");
	        }
	    } finally {
	        jdbcUtil.close();
	    }
	}

	public void saveMannerScore(int customerId, int additionalScore, int productId, int rentId) throws SQLException {
		int currentScore = getMannerScore(productId);
		int newScore = currentScore + additionalScore; // 기존 점수에 새로운 점수를 더한다

	
		// 물품 주인의 현재 매너 점수
	    int ownerId = getOwnerIdByProductId(productId);
	   

	    // 새로운 점수로 매너 점수를 업데이트
	    String updateSql = "UPDATE CUSTOMER SET MANNER_SCORE = ? WHERE CUSTOMERID = ?";
	    Object[] updateParams = new Object[] { newScore, ownerId };

	    jdbcUtil.setSqlAndParameters(updateSql, updateParams);
	    try {
	        int count = jdbcUtil.executeUpdate();
	        if (count == 0) {
	            throw new SQLException("매너 점수 업데이트 실패: CustomerID " + ownerId + "를 찾을 수 없습니다.");
	        }
	        jdbcUtil.commit();
	    } catch (Exception e) {
	        jdbcUtil.rollback();
	        e.printStackTrace();
	    } finally {
	        jdbcUtil.close();
	    }

	}

	public void updateProductStatus(int rentId, int status) throws SQLException {
		String sql = "UPDATE RENT SET STATUS = ? WHERE RENTID = ?"; 
	    jdbcUtil.setSqlAndParameters(sql, new Object[] { status, rentId }); 

		try {
			jdbcUtil.executeUpdate();
			jdbcUtil.commit();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
	}
}
