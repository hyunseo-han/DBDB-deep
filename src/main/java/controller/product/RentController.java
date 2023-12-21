package controller.product;

import java.sql.SQLException;
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
        else if (request.getMethod().equals("GET")) {    
            // 예약 확정 페이지
            int rentId = Integer.parseInt(request.getParameter("rentId"));
            Rent rent = rentDAO.findRentById(rentId); // rentId에 해당하는 Rent 객체 조회
            request.setAttribute("rent", rent);
            return showConfirmRentPage(request, response);
        }
        else    {return "";}
        
    }
    
    // 대여 확정 페이지 표시 메서드
    private String showConfirmRentPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RentManager rentManager = RentManager.getInstance();
        ProductManager productManager = ProductManager.getInstance();
        
        int rentId = Integer.parseInt(request.getParameter("rentId")); // 대여 ID 파라미터 받기
        Rent rent = rentManager.findRentById(rentId); // 대여 정보 조회
        Product product = productManager.getProductById(rent.getPrdt_id());

        if (rent == null || product == null) {
            request.setAttribute("error", "대여 정보를 찾을 수 없습니다.");
            return "redirect:/product/list"; // 오류 페이지로 리디렉션
        }

        request.setAttribute("rent", rent);
        request.setAttribute("product", product);
        return "/rent/confirmRent.jsp"; // 대여 확정 정보 페이지로 이동
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
            int rentId = rentManager.addRent(rent);  
            
            if (rentId > -1) {
                return "redirect:/rent/confirmRent?rentId=" + rentId; // 대여 확정 페이지로 리디렉션
            } else {
                // 레코드 생성 실패 처리
                request.setAttribute("error", "물품 대여 실패");
                return "redirect:/product/list";
            }
        } catch (Exception e) {
            logger.error("Error ordering product", e);
            request.setAttribute("error", "물품 대여 중 오류가 발생했습니다.");
            
            return "redirect:/product/list"; // 대여 실패 시 상품 리스트 페이지로 리디렉션
        }
               
    }

}
