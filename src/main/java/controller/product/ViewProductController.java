package controller.product;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Product;
import model.Rent;
import model.User;
import model.dao.Product.ProductDAO;
import model.dao.Rent.RentDao;
import model.service.ProductManager;
import model.service.RentManager;
import model.service.UserManager;

public class ViewProductController implements Controller {
    private ProductDAO productDAO;
    private RentDao rentDAO;

    public ViewProductController() {
        this.productDAO = new ProductDAO();
        this.rentDAO = new RentDao();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Product product = null;
        User user = null;
        List<Rent> rents = null;
        
        ProductManager manager = ProductManager.getInstance();
        RentManager rentManager = RentManager.getInstance();
        UserManager userManager = UserManager.getInstance();
        
        int productId = Integer.parseInt(request.getParameter("id"));
        
        try {
            product = manager.getProductById(productId);
            user = userManager.findUserById(productId);
            rents = rentManager.getDateById(productId); 
            
            request.setAttribute("product", product);
            request.setAttribute("customer", user);
            request.setAttribute("rents", rents);
            
            return "/product/view.jsp";
        } catch (Exception e) {
//            logger.error("Error ordering product", e);
            request.setAttribute("error", "상세 페이지 출력 중 에러 발생");          
            return "redirect:/product/list"; // 대여 실패 시 상품 리스트 페이지로 리디렉션
        }


        

        
        
    }

}
