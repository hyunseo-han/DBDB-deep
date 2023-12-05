package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Product;
import model.dao.Product.ProductDAO;

public class ProductManager {
    private static ProductManager prodManager = new ProductManager();
    private ProductDAO productDAO;
    
    private ProductManager() {
        try {
            productDAO = new ProductDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ProductManager getInstance() {
        return prodManager;
    }
    
    public boolean addProduct(Product product) throws SQLException, Exception {
        return productDAO.addProduct(product);
    }

    public boolean deleteProduct(int productId) throws SQLException, Exception {
        return productDAO.deleteProduct(productId);
    }

    public boolean updateProduct(Product product) throws SQLException, Exception {
        return productDAO.updateProduct(product);
    }

    public List<Product> getAllProducts() throws SQLException, Exception {
        return productDAO.getAllProducts();
    }

    public Product getProduct(int productId) throws SQLException, Exception {
        // This method would need to be implemented in the DAO.
        // return productDAO.getProduct(productId);
        return null; // Placeholder return
    }

}
