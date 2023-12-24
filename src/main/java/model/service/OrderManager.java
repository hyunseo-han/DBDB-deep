package model.service;

import java.sql.SQLException;
import java.util.List;

import model.RentInfo; // 수정: Rent 대신 RentInfo를 사용합니다.
import model.dao.OrderDAO;

public class OrderManager {
    private static OrderManager orderMan = new OrderManager();
    private OrderDAO orderDAO;

    private OrderManager() {
        try {
            orderDAO = new OrderDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }           
    }
    
    public static OrderManager getInstance() {
        return orderMan;
    }
    
    // 고객이 빌린 물품 조회
    public List<RentInfo> getBorrowedItemsByCustomer(int customerId) throws SQLException {
        try {
            return orderDAO.getrentInfo(customerId); 
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public void updateProductStatusAndMannerScore(int customerId, int rentId, int starRating, int productId) throws SQLException {
        int existingMannerScore = orderDAO.getMannerScore(customerId);
        int newMannerScore = existingMannerScore + starRating;

        orderDAO.updateProductStatus(rentId, 0); 
        orderDAO.saveMannerScore(customerId, newMannerScore, productId, rentId); // 매너점수 저장
    }
    
    public void updateProductStatus(int rentId, int status) throws SQLException {
    	orderDAO.updateProductStatus(rentId, 0); // 상태를 0으로 변경
    }

    public OrderDAO getOrderDAO() {
        return this.orderDAO;
    }
}
