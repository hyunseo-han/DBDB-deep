package controller.product;


import model.Product;
import model.service.ProductManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;



@WebServlet("/ProductController")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class ProductController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");       
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "delete":
                    return deleteProduct(request, response);
                default:
                    return "/product/list.jsp";
            }
        }

        
        if (request.getMethod().equals("GET")) {    
            // 전체 보기 
            return getProductList(request, response);
        }
        else if(request.getMethod().equals("POST")) { 
          // 물건 등록 
//          String action1 = getValue(request.getPart("action"));
          return addProduct(request, response);         
        }
        else {
            return "";
        }
       
    }
    
    // 상품 등록
    private String addProduct(HttpServletRequest request, HttpServletResponse response)  throws Exception {
        Part filePart = request.getPart("product_photo");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        // UUID 생성
        String uniqueID = UUID.randomUUID().toString();
        String newFileName = uniqueID + "_" + fileName;
        // 업로드 폴더의 실제 서버 경로를 찾음
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + "upload";
        log.debug("uploadPath parameter: {}", uploadPath); // SLF4J 로깅
        System.out.println("uploadPath at: " + uploadPath);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        String saveAs = uploadPath + File.separator + newFileName;
        System.out.println("saveAs: " + saveAs);

        // 파일 저장
        try (InputStream fileContent = filePart.getInputStream();
             FileOutputStream fos = new FileOutputStream(saveAs)) {
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                fos.write(bytes, 0, read);
            }
        }  
        
        HttpSession session = request.getSession();
        int customerId = (int) session.getAttribute("customerId");
        
        String title = getValue(request.getPart("title"));
        String description = getValue(request.getPart("description"));
        int regularPrice = Integer.parseInt(getValue(request.getPart("regular_price")));
        int rentalFee = Integer.parseInt(getValue(request.getPart("rental_fee")));
        int deposit = Integer.parseInt(getValue(request.getPart("deposit")));
        String category = getValue(request.getPart("category"));
        String address = getValue(request.getPart("address"));
        String detailAddress = getValue(request.getPart("detail_address"));
             
        Product product = new Product(
                0, 
                regularPrice,
                rentalFee,
                description,
                deposit,                
                newFileName,
                address,
                detailAddress,
                false,
                customerId,
                title,
                category);
        try {
            ProductManager productManager = ProductManager.getInstance();
            productManager.addProduct(product);

            log.debug("Create Product : {}", product);
            return "redirect:/product/list"; // 성공시 물건  리스트 화면으로 redirect
            
        } catch (Exception e) {     // 예외 발생 시 입력 form으로 forwarding
            request.setAttribute("creationFailed", true);
            request.setAttribute("exception", e);
            request.setAttribute("product", product);
            return "/product/addProductForm.jsp"; 
        }
    }
    
 // Part에서 값을 추출하는 메소드
    private String getValue(Part part) throws IOException {
        try (InputStream inputStream = part.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            StringBuilder value = new StringBuilder();
            char[] buffer = new char[1024];
            int length;
            while ((length = reader.read(buffer)) != -1) {
                value.append(buffer, 0, length);
            }
            return value.toString();
        }
    }

    // 전체 상품 조회
    private String getProductList(HttpServletRequest request, HttpServletResponse response) {
        try {
            ProductManager productManager = ProductManager.getInstance();
            List<Product> products = productManager.getAllProducts();
            
            request.setAttribute("products", products);
            
            return "/product/list.jsp"; 
        } catch (Exception e) {
            log.error("Error listing products", e);
            request.setAttribute("error", "상품 목록을 불러오는 중 오류가 발생했습니다.");
            
            return "/product/list.jsp"; 
        }
    }
    
    private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int productId = Integer.parseInt(request.getParameter("id"));

        try {
            ProductManager productManager = ProductManager.getInstance();
            productManager.deleteProduct(productId);

            return "redirect:/product/list"; // 삭제 성공 시 물품 목록 페이지로 리디렉션
        } catch (Exception e) {
            log.error("Error deleting product", e);
            request.setAttribute("error", "물품 삭제 중 오류가 발생했습니다.");
            
            return "redirect:/product/view?id=" + productId; // 삭제 실패 시 상품 상세 페이지로 리디렉션
        }
    }
    

}
