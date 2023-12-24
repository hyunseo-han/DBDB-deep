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
    
    public void updateProductStatusAndMannerScore(int customerId, int productId, int starRating) throws SQLException {
        // DB에서 기존 매너 점수를 가져옵니다.
        int existingMannerScore = orderDAO.getMannerScore(customerId);
        
        // 새 점수를 더합니다.
        int newMannerScore = existingMannerScore + starRating;

        // 상품 상태를 업데이트하고 매너 점수를 누적합니다.
        orderDAO.updateProductStatus(productId, 0); // 상태를 0으로 변경
        
        orderDAO.saveMannerScore(customerId, newMannerScore, productId); // 매너점수 저장
    }
    
    public void updateProductStatus(int productId, int status) throws SQLException {
    	orderDAO.updateProductStatus(productId, 0); // 상태를 0으로 변경
    }

    public OrderDAO getOrderDAO() {
        return this.orderDAO;
    }
}
