package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.UserManager;

public class CheckDuplicateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String type = request.getParameter("type");
        String value = request.getParameter("value");
        UserManager manager = UserManager.getInstance();

        boolean isDuplicate = false;
        if ("email".equals(type)) {
            isDuplicate = manager.isEmailDuplicate(value);
        } else if ("nickname".equals(type)) {
            isDuplicate = manager.isNicknameDuplicate(value);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"isDuplicate\": " + isDuplicate + "}");
        return null; // View 반환 없음
    }
}