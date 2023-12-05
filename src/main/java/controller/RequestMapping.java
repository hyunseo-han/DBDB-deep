package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.comm.CreateCommunityController;
//import controller.user.*;
//import controller.comm.*;
import controller.product.*;
import controller.user.LoginController;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        
        // 물건 등록 url GET 요청
        mappings.put("/user/loginForm", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/product/addProductForm", new ForwardController("/product/addProductForm.jsp"));
        mappings.put("/product/create", new ProductController());
        
        logger.info("Initialized Request Mapping!");
    } 

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}