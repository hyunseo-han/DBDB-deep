package model.service;

import java.sql.SQLException;
import java.util.List;

import model.User;
import model.dao.UserDAO;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class UserManager {
    private static UserManager userMan = new UserManager();
    private UserDAO userDAO;
    private UserAnalysis userAanlysis;

    private UserManager() {
        try {
            userDAO = new UserDAO();
            userAanlysis = new UserAnalysis(userDAO);
        } catch (Exception e) {
            e.printStackTrace();
        }           
    }
    
    public static UserManager getInstance() {
        return userMan;
    }
    
    public int create(User user) throws SQLException, ExistingUserException {
        
        return userDAO.create(user);
    }

    public UserDAO getUserDAO() {
        return this.userDAO;
    }
}
