package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.cartItem.AddCartItemController;
import controller.cartItem.CartItemController;
import controller.product.*;
import controller.user.CheckDuplicateUserController;
import controller.user.LoginController;
import controller.user.LogoutController;
import controller.user.MannerScoreController;
import controller.user.RegisterUserController;
import controller.mypage.OrderController;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));

        // 물건 전체 보기 (메인 페이지)
        mappings.put("/product/list", new ProductController());
        
        // 물건 검색 (메인 페이지)
        mappings.put("/product/search", new SearchController());
        
        // 물건 상세 보기 
        mappings.put("/product/view", new ViewProductController());
        
        // 물건 대여하기
        mappings.put("/product/order", new OrderController());
        
        // 회원가입, 로그인, 로그아
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/checkDuplicate", new CheckDuplicateUserController()); //이상하면 지워
        mappings.put("/user/logout", new LogoutController());
        
        // 물건 등록
        mappings.put("/product/create", new ProductController());
        
        // 물건 삭제
        mappings.put("/product/delete", new ProductController());
        
        //물건 수정
        mappings.put("/product/update", new UpdateProductController());
        logger.info("Initialized Request Mapping!");
        
        //마이페이지 물건조회 
        mappings.put("/mypage/borrowedProduct", new OrderController());
        mappings.put("/mypage/mannerScore", new OrderController()); //지워도 되는가?
        mappings.put("/mypage/returnProduct", new OrderController());
        mappings.put("mypage/lendProduct", new OrderController());

        //장바구니
//        mappings.put("/user/cartItem", new CartItemController());
        mappings.put("/user/cartItem", new ForwardController("/user/cartItem.jsp"));
        
        mappings.put("/user/cartItem/add", new AddCartItemController());
    } 

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}