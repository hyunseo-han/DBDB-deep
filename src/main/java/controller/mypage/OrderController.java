package controller.mypage;

import model.RentInfo; // 수정: Rent 대신 RentInfo를 사용합니다.
import model.service.OrderManager;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;

public class OrderController implements Controller {
    private OrderManager orderManager;

    public OrderController() {
        // OrderManager의 인스턴스를 싱글톤으로 받아옵니다.
        this.orderManager = OrderManager.getInstance();
    }
    
    // 고객이 빌린 물품 목록을 가져오는 메소드
    public List<RentInfo> getBorrowedItems(String customerId) {
        try {
            return orderManager.getBorrowedItemsByCustomer(customerId);
        } catch (SQLException e) {
            // 에러 로깅, 예외 처리, 적절한 응답 반환
            e.printStackTrace();
            return null; // 또는 에러 핸들링에 따라 사용자 정의 예외를 발생시킵니다.
        }
    }

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션에서 customerId를 가져옵니다.
	    String customerId = (String) request.getSession().getAttribute("customerId");
	    if (customerId == null) {
	        // customerId가 없는 경우 로그인 페이지로 리다이렉트할 수 있습니다.
	        return "redirect:/index.jsp";
	    }
	    
	    // OrderManager를 통해 해당 customerId의 빌린 물품 목록을 가져옵니다.
	    List<RentInfo> borrowedItems = orderManager.getBorrowedItemsByCustomer(customerId);
	    request.setAttribute("borrowedItems", borrowedItems); // JSP에서 사용할 수 있도록 request에 설정
	    return "/borrowedProduct.jsp";
	}
}
