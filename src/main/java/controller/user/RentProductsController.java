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
        String userId = UserSessionUtils.getLoginUserId(session);
        
        RentDao rentDao = new RentDao();

        List<RentInfo> rentProducts = rentDao.getRentListByUserId(userId);

        request.setAttribute("rentProducts", rentProducts);

        return "/user/rentProduct.jsp"; 
    }

}
