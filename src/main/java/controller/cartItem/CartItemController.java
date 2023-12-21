package controller.cartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.CartItem;
import model.dao.CartItem.*;
import model.service.CartItemManager;

public class CartItemController implements Controller{
   
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	CartItemManager manager = CartItemManager.getInstance();
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
    	int customerId = (int) session.getAttribute("customerId");
    	manager.getCartItem(customerId);
        request.setAttribute("cartItems", manager.getCartItem(customerId));

        return "/user/cartItem.jsp";

}
    }
    

