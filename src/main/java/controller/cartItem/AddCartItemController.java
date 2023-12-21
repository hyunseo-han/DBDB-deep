package controller.cartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.CartItem;
import model.service.CartItemManager;

public class AddCartItemController implements Controller{
	private CartItemManager cartItemManager = CartItemManager.getInstance();
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
    	int customerId = (int) session.getAttribute("customerId");
    	int productId = Integer.parseInt(request.getParameter("id"));
        //int quantity = Integer.parseInt(request.getParameter("quantity"));
    	String rentalFeeParam = request.getParameter("rentalFee");
    	int rentalFee = 0; // 기본값 설정
    	if (rentalFeeParam != null && !rentalFeeParam.isEmpty()) {
    	    rentalFee = (int) Double.parseDouble(rentalFeeParam);
    	}
    	
        // 장바구니에 물품 추가
    	CartItem item = new CartItem(customerId, productId, 1, rentalFee);
    	
        boolean added = cartItemManager.addCartItem(item);

        // 추가 성공
        if (added) {
            return "redirect:/product/list";
        } else {
            // 추가 실패
            request.setAttribute("errorMessage", "장바구니에 물품을 추가하지 못했습니다.");
            return "/product/view.jsp"; 
        }
    }
}
