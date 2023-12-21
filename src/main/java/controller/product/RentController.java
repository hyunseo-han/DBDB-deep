package controller.product;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Product;
import model.Rent;
import model.User;
import model.dao.Product.ProductDAO;
import model.dao.Rent.RentDao;
import model.service.ProductManager;
import model.service.RentManager;

public class RentController implements Controller {
    private ProductDAO productDAO;
    private RentDao rentDAO;
    private static final Logger logger = LoggerFactory.getLogger(RentController.class);

    public RentController() {
        this.productDAO = new ProductDAO();
        this.rentDAO =  new RentDao();
    }
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("execute 진입");
        if(request.getMethod().equals("POST")) { 
            // 빌리기
            logger.info("Executing OrderController");
            return rentProduct(request, response);         
          }
        else    {return "";}
        
    }
    
    private String rentProduct(HttpServletRequest request, HttpServletResponse response)  throws Exception {
        RentManager rentManager = RentManager.getInstance();
        
        String productIdStr = request.getParameter("productId");
        String customerIdStr = request.getParameter("customerId");

        logger.info("Received productId: {}", productIdStr);
        logger.info("Received customerId: {}", customerIdStr);
       
        LocalDate startDay = LocalDate.parse(request.getParameter("start_day"));
        LocalDate endDay = LocalDate.parse(request.getParameter("end_day"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        
        Product product = productDAO.getProductById(productId);
        logger.info("Received product!!!: {}", productId);
        logger.info("Received customer!!!: {}", customerId);
        Rent rent = new Rent(0, customerId, productId, 1, startDay, endDay, product.getRentalFee());
        
        try {
            rentManager.addRent(rent);  
            return "redirect:/product/list"; // 추후 대여 확정 페이지로 수
        } catch (Exception e) {
            logger.error("Error ordering product", e);
            request.setAttribute("error", "물품 대여 중 오류가 발생했습니다.");
            
            return "redirect:/product/list"; // 대여 실패 시 상품 리스트 페이지로 리디렉션
        }
               
    }

}
