package controller.product;


import model.Product;
import model.service.ProductManager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.comm.CreateCommunityController;

public class ProductController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
     // 'title' 파라미터 값 로깅
        String title = request.getParameter("title");
        log.debug("Title parameter: {}", title); // SLF4J 로깅

        // System.out.println을 사용하여 콘솔에 출력할 수도 있습니다.
        System.out.println("Title parameter: " + title);
//        Product product = new Product(
//                0, 
//                Integer.parseInt(request.getParameter("regular_price")),
//                Integer.parseInt(request.getParameter("rental_fee")),
//                request.getParameter("description"),
//                Integer.parseInt(request.getParameter("deposit")),
//                request.getParameter("product_photo"),
//                request.getParameter("address"),
//                request.getParameter("detail_address"),
//                false,
//                Integer.parseInt(request.getParameter("customerId")),
//                request.getParameter("title"));  
//        try {
//            ProductManager productManager = ProductManager.getInstance();
//            productManager.addProduct(product);
//
//            log.debug("Create Product : {}", product);
//            return "redirect:/product/view"; // 성공시 물 리스트 화면으로 redirect
//            
//        } catch (Exception e) {     // 예외 발생 시 입력 form으로 forwarding
//            request.setAttribute("creationFailed", true);
//            request.setAttribute("exception", e);
//            request.setAttribute("product", product);
//            return "/product/addProductForm.jsp"; 
//        }
                
        String action = request.getParameter("action");
//        if (action == null || action.isEmpty()) {
//            return "/product/addProductForm.jsp"; // GET 요청에 대해 addProductForm.jsp 반환
//        }
        switch (action) {
            case "add":
                return addProduct(request, response);
////            case "view":
////                return detailProduct(request, response);
////            case "update":
////                return updateProduct(request, response);
////            case "delete":
////                return deleteProduct(request, response);
            default:
                return "/product/addProductForm.jsp";
//                return "/product/view"; // 기본 액션
        }
        
    }
    
    // 상품 등록
    private String addProduct(HttpServletRequest request, HttpServletResponse response)  throws Exception {
        Product product = new Product(
                0, 
                Integer.parseInt(request.getParameter("regular_price")),
                Integer.parseInt(request.getParameter("rental_fee")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("deposit")),
                
                request.getParameter("product_photo"),
                request.getParameter("address"),
                request.getParameter("detail_address"),
                false,
                Integer.parseInt(request.getParameter("customerId")),
                request.getParameter("title"));  
        try {
            ProductManager productManager = ProductManager.getInstance();
            productManager.addProduct(product);

            log.debug("Create Product : {}", product);
            return "redirect:/product/view"; // 성공시 물 리스트 화면으로 redirect
            
        } catch (Exception e) {     // 예외 발생 시 입력 form으로 forwarding
            request.setAttribute("creationFailed", true);
            request.setAttribute("exception", e);
            request.setAttribute("product", product);
            return "/product/addProductForm.jsp"; 
        }
    }

//    private String detailProduct(HttpServletRequest request, HttpServletResponse response) {
//        // 상품 조회 로직 구현
//        return "/WEB-INF/view/productDetail.jsp";
//    }
//
//    private String updateProduct(HttpServletRequest request, HttpServletResponse response) {
//        // 상품 수정 로직 구현
//        return "redirect:/product/view?id=" + request.getParameter("id");
//    }
//
//    private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
//        // 상품 삭제 로직 구현
//        return "redirect:/product/list";
//    }

}
