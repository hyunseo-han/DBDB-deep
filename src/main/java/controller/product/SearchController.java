package controller.product;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Product;
import model.service.ProductManager;

public class SearchController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String viewUrl = "/product/list.jsp";
        ProductManager manager = ProductManager.getInstance();

        try {
            String keyword = request.getParameter("keyword");

            if (keyword == null || keyword.trim().isEmpty()) {
                request.setAttribute("error", "검색어를 입력하지 않았습니다.");
                return viewUrl;
            }

            List<Product> searchProducts = manager.searchProducts(keyword);

            if (searchProducts.isEmpty()) {
                request.setAttribute("error", "검색 결과가 없습니다. 상단 로고를 누르면 메인 화면으로 이동합니다.");
            } else {
                request.setAttribute("products", searchProducts);
            }
        } catch (Exception ex) {
            request.setAttribute("error", "검색 중 문제가 발생했습니다: " + ex.getMessage());
            ex.printStackTrace();
        }

        return viewUrl;
    }
}
