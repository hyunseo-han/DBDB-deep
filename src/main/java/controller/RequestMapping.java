package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.product.*;
import controller.user.CheckDuplicateUserController;
import controller.user.LoginController;
import controller.user.RegisterUserController;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));

        // 물건 전체 보기 (메인 페이지)
        mappings.put("/product/list", new ProductController());
        
        // 물건 상세 보기 
        mappings.put("/product/view", new ViewProductController());
        
        // 회원가입, 로그인
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/checkDuplicate", new CheckDuplicateUserController()); //이상하면 지워
        
        // 물건 등록
        mappings.put("/product/addProductForm", new ForwardController("/product/addProductForm.jsp"));
        mappings.put("/product/create", new ProductController());
        
        // 물건 삭제
        mappings.put("/product/delete", new ProductController());
        
        //물건 수정
        mappings.put("/product/update", new UpdateProductController());
        logger.info("Initialized Request Mapping!");
    } 

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}