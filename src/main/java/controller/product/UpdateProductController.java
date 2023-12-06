package controller.product;

import model.Product;
import model.service.ProductManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;

@WebServlet("/UpdateProductController")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
        maxFileSize = 1024 * 1024 * 10,       // 10MB
        maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class UpdateProductController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateProductController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        
        int productId = Integer.parseInt(request.getParameter("id"));
    	ProductManager manager = ProductManager.getInstance();
    	Product existingProduct = manager.getProductById(productId);
    	
        if(request.getMethod().equals("GET")) {
        	request.setAttribute("product", existingProduct);
        	
        	return "/product/updateProductForm.jsp";
        }
      
       
        // 새로운 이미지가 업로드되었을 경우에만 새로운 이미지 저장
      Part filePart = request.getPart("product_photo");
      String newFileName;
      if (filePart != null && filePart.getSize() > 0) {
          // 기존 이미지 삭제
          deleteImage(existingProduct.getProductPhoto());

          // 새로운 이미지 저장
          String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
          String uniqueID = UUID.randomUUID().toString();
          newFileName = uniqueID + "_" + fileName;
          String uploadPath = request.getServletContext().getRealPath("") + File.separator + "upload";
          File uploadDir = new File(uploadPath);
          if (!uploadDir.exists()) uploadDir.mkdir();
          String saveAs = uploadPath + File.separator + newFileName;

          try (InputStream fileContent = filePart.getInputStream();
               FileOutputStream fos = new FileOutputStream(saveAs)) {
              int read;
              final byte[] bytes = new byte[1024];
              while ((read = fileContent.read(bytes)) != -1) {
                  fos.write(bytes, 0, read);
              }
          }
      }
      else {
    	  newFileName = request.getParameter("productPhoto");
      }
      Product product = new Product(
      		productId, Integer.parseInt(request.getParameter("regularPrice")),
      		Integer.parseInt(request.getParameter("rentalFee")), request.getParameter("description"),
      		Integer.parseInt(request.getParameter("deposit")), newFileName,
      		request.getParameter("address"), request.getParameter("detailAddress"),
      		false, Integer.parseInt(request.getParameter("customerId")), 
      		request.getParameter("title"), request.getParameter("category")
      		);
  	log.debug("Update Product : {}", product);
      manager.updateProduct(product);
      return "redirect:/product/list";
               
    }
      
   // 이미지 파일 삭제
    private void deleteImage(String fileName) {
        String uploadPath = System.getProperty("catalina.base") + File.separator + "webapps" + File.separator + "YourAppName" + File.separator + "upload";
        File fileToDelete = new File(uploadPath + File.separator + fileName);
        if (fileToDelete.exists()) {
            fileToDelete.delete();
        }
    }
    }
