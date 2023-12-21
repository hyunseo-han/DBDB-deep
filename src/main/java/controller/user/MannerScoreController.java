package controller.user;

import model.RentInfo; // 수정: Rent 대신 RentInfo를 사용합니다.
import model.service.OrderManager;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;

public class MannerScoreController implements Controller {
	private OrderManager orderManager;

	public MannerScoreController() {
		// OrderManager의 인스턴스를 싱글톤으로 받아옵니다.
		this.orderManager = OrderManager.getInstance();
	}

	// 고객이 빌린 물품 목록을 가져오는 메소드
	public List<RentInfo> getBorrowedItems(int customerId) {
		try {
			return orderManager.getBorrowedItemsByCustomer(customerId);
		} catch (SQLException e) {
			// 에러 로깅, 예외 처리, 적절한 응답 반환
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 세션에서 customerId
		Integer customerId = (Integer) request.getSession().getAttribute("customerId");

		int productId = Integer.parseInt(request.getParameter("productId"));
		String productName = request.getParameter("productName");
		int starRating = Integer.parseInt(request.getParameter("starRating")); // 평가 점수

		orderManager.updateProductStatusAndMannerScore(customerId, productId, starRating);
		request.setAttribute("productName", productName); // 상품명 설정
		return "/user/star.jsp"; // 사용자를 만족도 조사 페이지로 리다이렉션

	}
}