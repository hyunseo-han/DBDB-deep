package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Rent;
import model.dao.Product.ProductDAO;
import model.dao.Rent.RentDao;

public class RentManager {
    private static RentManager rentManager = new RentManager();
    private RentDao rentDAO;
    
    private RentManager() {
        try {
            rentDAO = new RentDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static RentManager getInstance() {
        return rentManager;
    }
    
    public int addRent(Rent rent) {
        return rentDAO.addRent(rent);     
    }
    
    public List<Rent> getDateById(int productId) throws SQLException {
        return rentDAO.getDateById(productId);        
    }

}
