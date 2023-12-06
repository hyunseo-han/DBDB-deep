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
            return "/user/registerForm.jsp";   // 검색한 커뮤니티 리스트를 registerForm으로 전송       
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
        user.setManner_score(0); 
        user.setCustomerId(0);
        log.debug("Create User : {}", user);

        try {
            UserManager manager = UserManager.getInstance();
            manager.create(user);
            return "redirect:/user/login/form";   // 성공 시 로그인 화면으로 
            //userman3에서는 list로 리다이렉트
            
        } catch (ExistingUserException e) { // 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
            request.setAttribute("exception", e);
            request.setAttribute("user", user);
            return "/user/registerForm.jsp";
        }
    }
}

