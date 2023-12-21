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
//        int rentalFee = Integer.parseInt(request.getParameter("rentalFee"));
        // 장바구니에 물품 추가
    	CartItem item = new CartItem(customerId, productId, 1, rentalFee);
    	
        boolean added = cartItemManager.addCartItem(item);
        System.out.println(added + "@@@@@@@@@@@@@@@@@@@@");
        // 추가가 성공했을 경우 메인 페이지로 리다이렉트
        if (added) {
            return "redirect:/product/list";
        } else {
            // 추가에 실패한 경우 처리 로직
            request.setAttribute("errorMessage", "장바구니에 물품을 추가하지 못했습니다.");
            return "/product/view.jsp"; // 실패 페이지로 이동
        }
    }
}
