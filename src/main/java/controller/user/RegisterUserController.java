package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.User;
import model.service.ExistingUserException;
import model.service.UserManager;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {    
            // GET request: 회원정보 등록 form 요청 
            log.debug("RegisterForm Request");        
            return "/user/registerForm.jsp";   
        }   
        
        String birthDateString = request.getParameter("birth_date");
        java.sql.Date birthDate = java.sql.Date.valueOf(birthDateString); // 문자열을 java.sql.Date로 변환
              
        // POST request (회원정보가 parameter로 전송됨)
        User user = new User(
                request.getParameter("name"),
                birthDate,
                request.getParameter("nickname"),
                request.getParameter("email"),
                request.getParameter("passwd"),
                request.getParameter("phone"),
                request.getParameter("address"));
        log.debug("Create User : {}", user); //잘 됨

        try {
            UserManager manager = UserManager.getInstance();
            manager.create(user);
            return "redirect:/user/login";   // 성공 시 로그인 화면으로 
            
        } catch (ExistingUserException e) { // 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
            request.setAttribute("exception", e);
            request.setAttribute("user", user);
            System.out.println("회원가입 실패");
            log.debug("ERR"); //잘 됨

            log.debug(e.getMessage()); //잘 됨
            return "/user/registerForm.jsp";
        }
    }
}
