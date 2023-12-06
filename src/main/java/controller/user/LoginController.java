package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.service.UserManager;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String passwd = request.getParameter("password");
        
        try {
            // 모델에 로그인 처리를 위임
            UserManager manager = UserManager.getInstance();
            User user = manager.login(email, passwd);
//            manager.login(email, password);
    
            // 세션에 사용자 이이디 저장
            HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, email);
            session.setAttribute("customerId", user.getCustomerId());
            session.setAttribute("name", user.getNickname());
            System.out.println("로그인 성공");
            return "redirect:/product/list";        
        } catch (Exception e) {
            /* UserNotFoundException이나 PasswordMismatchException 발생 시
             * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
             */
            request.setAttribute("loginFailed", true);
            request.setAttribute("exception", e);
            System.out.println("로그인 실패");
            return "/user/loginForm.jsp";           
        }
    }
}