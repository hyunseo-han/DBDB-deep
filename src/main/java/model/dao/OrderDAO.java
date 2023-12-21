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
    public List<RentInfo> rentInfo(String customerId) throws SQLException {
        String sql = "SELECT R.RENTID, R.CUSTOMERID as RenterId, R.PRODUCTID, R.STATUS, " +
                     "R.BORROW_START_DAY as START_DAY, R.BORROW_END_DAY as END_DAY, R.RENTAL_FEE, " +
                     "P.TITLE, P.PRODUCT_PHOTO as PHOTO, P.ADDRESS, " +
                     "C.NAME AS OwnerName " +
                     "FROM RENT R " +
                     "JOIN PRODUCT P ON R.PRODUCTID = P.PRODUCTID " +
                     "JOIN CUSTOMER C ON P.CUSTOMERID = C.CUSTOMERID " +
                     "WHERE R.CUSTOMERID = ?";
        Object[] param = new Object[] { customerId };
        jdbcUtil.setSqlAndParameters(sql, param);
        List<RentInfo> rentList = new ArrayList<>();

        try {
            ResultSet rs = jdbcUtil.executeQuery();

            while (rs.next()) {
                RentInfo rent = new RentInfo(
                    rs.getInt("RENTID"),
                    rs.getInt("RenterId"),
                    rs.getInt("PRODUCTID"),
                    rs.getInt("STATUS"),
                    rs.getDate("START_DAY") != null ? rs.getDate("START_DAY").toLocalDate() : null,
                    rs.getDate("END_DAY") != null ? rs.getDate("END_DAY").toLocalDate() : null,
                    rs.getInt("RENTAL_FEE"),
                    rs.getString("TITLE"),
                    rs.getString("PHOTO"),
                    rs.getString("ADDRESS"),
                    rs.getString("OwnerName")
                );
                rentList.add(rent);
            }
            return rentList;
        } finally {
            jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 닫기
        }
    }
}
