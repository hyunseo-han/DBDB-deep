package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.service.UserManager;

public class MyPageController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String loginUserId = UserSessionUtils.getLoginUserId(session);

        UserManager manager = UserManager.getInstance();
        User user = manager.findUserByEmail(loginUserId); 

        request.setAttribute("user", user);
        return "/user/mypage.jsp"; 
    }
}