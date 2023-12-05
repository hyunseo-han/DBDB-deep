package model.service;

import java.sql.SQLException;

import model.Customer;
import model.dao.Customer.CustomerDAO;

public class CustomerManager {
    private static CustomerManager custMan = new CustomerManager();
    private CustomerDAO customerDAO;

    private CustomerManager() {
        try {
            customerDAO = new CustomerDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }           
    }
    
    public static CustomerManager getInstance() {
        return custMan;
    }
    
    public int create(Customer customer) throws SQLException, ExistingUserException {
        if (customerDAO.existingUser(customer.getCustomerId()) == true) {
            throw new ExistingUserException(customer.getCustomerId() + "는 존재하는 아이디입니다.");
        }
        return customerDAO.create(customer);
    }
    
    public boolean login(String email, String password)
            throws SQLException, UserNotFoundException, PasswordMismatchException {
            if(customerDAO.login(email, password) == false) {
                throw new PasswordMismatchException("로그인에 실패했습니다.");
            }
            
//          if (!user.matchPassword(password)) {
//              
//          }
            return true;
        }
}
