package model.dao.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;


public class ProductDAO {
    private Connection conn; // 데이터베이스 연결을 위한 객체

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    // 상품 등록
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO products (productId, regularPrice, rentalFee, description, deposit, rentalLocation, productPhoto, address, detailAddress, isBorrowed, customerId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, product.getProductId());
            pstmt.setDouble(2, product.getRegularPrice());
            pstmt.setDouble(3, product.getRentalFee());
            pstmt.setString(4, product.getDescription());
            pstmt.setDouble(5, product.getDeposit());
            pstmt.setString(6, product.getRentalLocation());
            pstmt.setString(7, product.getProductPhoto());
            pstmt.setString(8, product.getAddress());
            pstmt.setString(9, product.getDetailAddress());
            pstmt.setBoolean(10, product.isBorrowed());
            pstmt.setInt(11, product.getCustomerId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 상품 수정
    public boolean updateProduct(Product product) {
        String sql = "UPDATE products SET regularPrice = ?, rentalFee = ?, description = ?, deposit = ?, rentalLocation = ?, productPhoto = ?, address = ?, detailAddress = ?, isBorrowed = ?, customerId = ? WHERE productId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, product.getRegularPrice());
            pstmt.setDouble(2, product.getRentalFee());
            pstmt.setString(3, product.getDescription());
            pstmt.setDouble(4, product.getDeposit());
            pstmt.setString(5, product.getRentalLocation());
            pstmt.setString(6, product.getProductPhoto());
            pstmt.setString(7, product.getAddress());
            pstmt.setString(8, product.getDetailAddress());
            pstmt.setBoolean(9, product.isBorrowed());
            pstmt.setInt(10, product.getCustomerId());
            pstmt.setInt(11, product.getProductId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // 상품 삭제
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 상품 조회
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("productId"),
                    rs.getDouble("regularPrice"),
                    rs.getDouble("rentalFee"),
                    rs.getString("description"),
                    rs.getDouble("deposit"),
                    rs.getString("rentalLocation"),
                    rs.getString("productPhoto"),
                    rs.getString("address"),
                    rs.getString("detailAddress"),
                    rs.getBoolean("isBorrowed"),
                    rs.getInt("customerId")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

}

