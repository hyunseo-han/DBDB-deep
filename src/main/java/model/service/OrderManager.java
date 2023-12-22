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
            System.out.println("넘겨주는 값은 " + customerId);
            return orderDAO.getrentInfo(customerId); 
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public void updateProductStatusAndMannerScore(int customerId, int productId, int starRating) throws SQLException {
        // DB에서 기존 매너 점수를 가져옵니다.
        int existingMannerScore = orderDAO.getMannerScore(customerId);
        System.out.println("기존 매너 점수 " + existingMannerScore);
        
        // 새 점수를 더합니다.
        int newMannerScore = existingMannerScore + starRating;
        System.out.println("new 매너 점수 " + newMannerScore);

        // 상품 상태를 업데이트하고 매너 점수를 누적합니다.
        orderDAO.updateProductStatus(productId, 0); // 상태를 0으로 변경
        System.out.println("상품넘버는  " + productId );
        
        orderDAO.saveMannerScore(customerId, newMannerScore); // 누적된 점수 저장
        System.out.println(customerId + " 그리고  ! " + newMannerScore);
    }
    
    public void updateProductStatus(int productId, int status) throws SQLException {
    	orderDAO.updateProductStatus(productId, 0); // 상태를 0으로 변경
    }

    public OrderDAO getOrderDAO() {
        return this.orderDAO;
    }
}
