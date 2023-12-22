package controller.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.Controller;
import model.RentInfo;
import model.dao.Rent.*;

public class RentProductsController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        int loginUserId = (int) session.getAttribute("customerId");
        System.out.println("로그인된 사용자 ID: " + loginUserId); 

        RentDao productDao = new  RentDao();
        List<RentInfo> rentProducts = productDao.getRentListByUserId(loginUserId);

        System.out.println("빌려준 상품 수: " + rentProducts.size()); 

        request.setAttribute("rentProducts", rentProducts);

        return "/user/rentProduct.jsp"; 
    }
}

