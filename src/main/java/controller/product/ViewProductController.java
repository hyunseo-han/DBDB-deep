package controller.product;

import java.lang.System.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;

import controller.Controller;
import model.Product;
import model.dao.Product.ProductDAO;
import model.service.ProductManager;

public class ViewProductController implements Controller {
    private ProductDAO productDAO;

    public ViewProductController() {
        this.productDAO = new ProductDAO();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
   
        Product product = null;
        ProductManager manager = ProductManager.getInstance();
        int productId = Integer.parseInt(request.getParameter("id"));  
        System.out.println("aksdfjlaksdfjla "  +  productId);
        product = productDAO.getProductById(productId);
        request.setAttribute("product", product);
        return "/product/view.jsp"; 

    }
    
}

