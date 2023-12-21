package controller.cartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.CartItemManager;

public class RemoveCartItemController implements Controller{
	 @Override
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 CartItemManager manager = CartItemManager.getInstance();
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession();
			
	    	int customerId = (int) session.getAttribute("customerId");
	    	int productId = Integer.parseInt(request.getParameter("productId"));
	    	
			boolean removed = manager.deleteCartItem(customerId, productId);

	        // 삭제 성공
	        if (removed) {
	            return "redirect:/product/list";
	        } else {
	            // 삭제 실패
	            request.setAttribute("errorMessage", "장바구니에 물품을 삭제하지 못했습니다.");
	            return "/product/view.jsp";
	        }
	 }
}
