package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.lecture.*;
import controller.user.*;
import controller.dib.*;

import controller.status.CreateStatusController;
import controller.status.DeleteStatusController;
import controller.status.StatusController;


public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/home", new ForwardController("/home.jsp"));
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        
       
        //uri 하나로 해서 get/post로 나눠 mapping
        mappings.put("/user/mypage", new myPageController());
        mappings.put("/user/mypage/edit", new ForwardController("/user/updateForm.jsp")); 
        mappings.put("/user/mypage/deleteDib", new DeleteDibController()); 
        mappings.put("/user/mypage/othersDib", new OthersDibController());
        
        //mappings.put("/user/mypage/update", new UpdateUserController());
        //mappings.put("/user/list", new ListUserController());
        //mappings.put("/user/view", new ViewUserController());
        
        // 회원 가입 폼 요청과 가입 요청 처리 병합 (폼에 커뮤니티 선택 메뉴 추가를 위함)
        mappings.put("/user/join", new JoinUserController());

        // 사용자 정보 수정 폼 요청과 수정 요청 처리 병합      
        mappings.put("/user/update", new UpdateUserController());
        
        mappings.put("/user/delete", new DeleteUserController());
        
		mappings.put("/lecture/filter/form", new ForwardController("/lecture/filterForm.jsp"));
		mappings.put("/lecture/filter", new FilterLectureController());
        mappings.put("/lecture/searchResult", new SearchResultLectureController());
        mappings.put("/lecture/searchResult/createDib", new CreateDibController());
                
        mappings.put("/lecture/searchResult/status", new StatusController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}