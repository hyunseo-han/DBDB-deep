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

        //상품상태 
        if ("returnProduct".equals(action)) {
            int rentId = Integer.parseInt(request.getParameter("rentId"));
            String productName = request.getParameter("productName");
            int productId = Integer.parseInt(request.getParameter("productId"));
            // 상품 상태 업데이트 (상태를 0으로 변경)
            //잘 되는것 같음
            orderManager.updateProductStatus(rentId, 0);

            // star.jsp로 리다이렉트하기 전에 productName을 request에 설정
            request.setAttribute("productName", productName);
            request.setAttribute("rentId", rentId);
            request.setAttribute("productId", productId);
            return "/user/star.jsp";
        }

        //별점 
        if ("returnStar".equals(action)) {
            int rentId = Integer.parseInt(request.getParameter("rentId"));
            int starRating = Integer.parseInt(request.getParameter("starRating"));
            int productId = Integer.parseInt(request.getParameter("productId"));

            // 상품 상태 업데이트 및 별점 누적
            orderManager.updateProductStatusAndMannerScore(customerId, rentId, starRating, productId);

            // 최신화된 빌린 물품 목록을 가져옴
            List<RentInfo> updatedBorrowedItems = orderManager.getBorrowedItemsByCustomer(customerId);
            request.setAttribute("borrowedItems", updatedBorrowedItems);

            // borrowedProduct.jsp 페이지로 리다이렉션
            return "/user/borrowedProduct.jsp";
        }
       
        List<RentInfo> borrowedItems = orderManager.getBorrowedItemsByCustomer(customerId);
        request.setAttribute("borrowedItems", borrowedItems);

        return "/user/borrowedProduct.jsp";
    }
}
