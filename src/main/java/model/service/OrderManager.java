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
    public List<RentInfo> getBorrowedItemsByCustomer(String customerId) throws SQLException {
        try {
            return orderDAO.rentInfo(customerId); // 수정: 메소드 이름 변경 및 반환 타입 수정
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public OrderDAO getOrderDAO() {
        return this.orderDAO;
    }
}
