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
    	int customerId = Integer.parseInt(request.getParameter("customerId"));
    	
        request.setAttribute("cartItems", manager.getCartItem(customerId));
        return "/cartItem.jsp";
    	
//        String action = request.getParameter("action");

//        if (action != null) {
//            switch (action) {
//                case "getCartItem":
//                    return getCartItem(request, response);
////                case "addCartItem":
////                    return addCartItem(request, response);
////                case "deleteCartItem":
////                    return deleteCartItem(request, response);
//                default:
//                    return "Invalid action";
//            }
//        }

//        return null;
    }

    private String getCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	CartItemManager manager = CartItemManager.getInstance();
    	int customerId = Integer.parseInt(request.getParameter("customerId"));
    	
        request.setAttribute("cartItems", manager.getCartItem(customerId));
        return "/user/cartItem.jsp";
    }

    private String addCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	int customerId = (int) session.getAttribute("customerId");
    	int productId = (int)request.getAttribute("productId");
    	//int quantity = (int)request.getAttribute("quantity");
    	int rentalFee = (int)request.getAttribute("rentalFee");
    	
    	CartItem item = new CartItem(
    		customerId, productId, 1, rentalFee
    			);
    	
    	try {
    		CartItemManager manager = CartItemManager.getInstance();
    		manager.addCartItem(item);
    		
    		return "redirect:/product/list";
    	}catch(Exception e) {
    		return "/product/view";
    	}
    }
//
//    private String deleteCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        int customerId = 1; // 예시로 1로 지정
//        int productId = 101; // 예시로 101로 지정
//        cartItemManager.deleteCartItem(customerId, productId);
//        return "CartItem deleted successfully";
//    }
}
    

