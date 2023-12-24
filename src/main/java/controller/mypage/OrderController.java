package controller.mypage;

import model.RentInfo;
import model.service.OrderManager;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;

public class OrderController implements Controller {
    private OrderManager orderManager;

    public OrderController() {
        this.orderManager = OrderManager.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String action = request.getParameter("action");
        
        Integer customerId = (Integer) request.getSession().getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/user/loginForm.jsp";
        }

        //이건 어디?
        if ("returnProduct".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            String productName = request.getParameter("productName");

            // 상품 상태 업데이트 (상태를 0으로 변경)
            orderManager.updateProductStatus(productId, 0);

            // star.jsp로 리다이렉트하기 전에 productName을 request에 설정
            request.setAttribute("productName", productName);
            request.setAttribute("productId", productId);
            return "/user/star.jsp";
        }

        if ("returnStar".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int starRating = Integer.parseInt(request.getParameter("starRating"));

            // 상품 상태 업데이트 및 별점 누적
            orderManager.updateProductStatusAndMannerScore(customerId, productId, starRating);

            // 최신화된 빌린 물품 목록을 가져옴
            List<RentInfo> updatedBorrowedItems = orderManager.getBorrowedItemsByCustomer(customerId);
            request.setAttribute("borrowedItems", updatedBorrowedItems);

            // borrowedProduct.jsp 페이지로 리다이렉션
            return "/user/borrowedProduct.jsp";
        }
       

        // 고객이 빌린 물품 목록을 가져옵니다.
        List<RentInfo> borrowedItems = orderManager.getBorrowedItemsByCustomer(customerId);
        request.setAttribute("borrowedItems", borrowedItems);

        return "/user/borrowedProduct.jsp";
    }
}
