package controller.product;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        product = productDAO.getProductById(productId);
        request.setAttribute("product", product);
        return "/product/view.jsp";
    }

}
