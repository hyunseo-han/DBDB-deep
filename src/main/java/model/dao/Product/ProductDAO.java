package model.dao.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.RentInfo;
import model.User;
import model.dao.JDBCUtil;

public class ProductDAO {
    private JDBCUtil jdbcUtil;

    public ProductDAO() {
        this.jdbcUtil = new JDBCUtil();
    }

    // 상품 등록
    public boolean addProduct(Product product) throws Exception {
        String sql = "INSERT INTO product (productId, regular_price, rental_fee, description, deposit, product_photo, address, detail_address, is_borrowed, customerid, title, category) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = { product.getProductId(), product.getRegularPrice(), product.getRentalFee(), product.getDescription(), product.getDeposit(), product.getProductPhoto(), product.getAddress(), product.getDetailAddress(), product.isBorrowed(), product.getCustomerId(), product.getTitle(), product.getCategory() };

        jdbcUtil.setSqlAndParameters(sql, params);

        try {
            int result = jdbcUtil.executeUpdate();
            return result > 0;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {     
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }   
        return false;
    }

    // 상품 수정
    public boolean updateProduct(Product product) throws Exception {
        String sql = "UPDATE product SET regular_price = ?, rental_fee = ?, description = ?, deposit = ?, product_photo = ?, address = ?, detail_address = ?, customerId = ?, title = ?, category = ? WHERE productId = ?";
        //is borrowed 구현 안돼서 false로 대체
        Object[] params = { product.getRegularPrice(), product.getRentalFee(), product.getDescription(), product.getDeposit(), product.getProductPhoto(), product.getAddress(), product.getDetailAddress(), product.getCustomerId(), product.getTitle(), product.getCategory(), product.getProductId() };

        jdbcUtil.setSqlAndParameters(sql, params);

        try {
            int result = jdbcUtil.executeUpdate();
            return result > 0;
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

    // 상품 삭제
    public boolean deleteProduct(int productId) throws Exception {
        String sql = "DELETE FROM product WHERE productId = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { productId });

        try {
            int result = jdbcUtil.executeUpdate();
            return result > 0;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {     
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }  
        return false;
    }

    // 상품 조회
    public List<Product> getAllProducts() throws SQLException {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";
        jdbcUtil.setSqlAndParameters(sql, null);

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("productId"),
                    rs.getInt("regular_price"),
                    rs.getInt("rental_fee"),
                    rs.getString("description"),
                    rs.getInt("deposit"),
//                    rs.getString("rentalLocation"),
                    rs.getString("product_photo"),
                    rs.getString("address"),
                    rs.getString("detail_address"),
                    rs.getBoolean("is_borrowed"),
                    rs.getInt("customerId"),
                    rs.getString("title"),
                    rs.getString("category")
                );
                productList.add(product);
            }
        } finally {
            jdbcUtil.close();
        }
        return productList;
    }
    
    // 특정 상품 조회 
    public Product getProductById(int productId) throws SQLException {
        String sql = "SELECT * FROM product WHERE productId = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { productId });

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                Product product = new Product(
                    rs.getInt("productId"),
                    rs.getInt("regular_price"),
                    rs.getInt("rental_fee"),
                    rs.getString("description"),
                    rs.getInt("deposit"),
                    rs.getString("product_photo"),
                    rs.getString("address"),
                    rs.getString("detail_address"),
                    rs.getBoolean("is_borrowed"),
                    rs.getInt("customerId"),
                    rs.getString("title"),
                    rs.getString("category")
                );
                return product;
            }
        } finally {
            jdbcUtil.close();
        }
        return null; // 상품을 찾지 못한 경우 null 반환
    }

    
    // 물건 검색 
    public List<Product> searchProducts(String keyword) throws SQLException  {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM product WHERE title LIKE ?";
        jdbcUtil.setSqlAndParameters(query, new Object[] {"%" + keyword + "%"});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("productId"),
                    rs.getInt("regular_price"),
                    rs.getInt("rental_fee"),
                    rs.getString("description"),
                    rs.getInt("deposit"),
                    rs.getString("product_photo"),
                    rs.getString("address"),
                    rs.getString("detail_address"),
                    rs.getBoolean("is_borrowed"),
                    rs.getInt("customerId"),
                    rs.getString("title"),
                    rs.getString("category")
                );
                productList.add(product);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            jdbcUtil.close();
        }
        return productList;

    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM product WHERE category = ?";
        jdbcUtil.setSqlAndParameters(query, new Object[] {category});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("productId"),
                    rs.getInt("regular_price"),
                    rs.getInt("rental_fee"),
                    rs.getString("description"),
                    rs.getInt("deposit"),
                    rs.getString("product_photo"),
                    rs.getString("address"),
                    rs.getString("detail_address"),
                    rs.getBoolean("is_borrowed"),
                    rs.getInt("customerId"),
                    rs.getString("title"),
                    rs.getString("category")
                );
                productList.add(product);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            jdbcUtil.close();
        }
        return productList;

    }

}
