package model.dao.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDAO {
    private Connection conn;

    // 생성자: 데이터베이스 연결
    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    // 제품 추가 (Create)
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO product (product_id, name, price, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getId());
            pstmt.setString(2, product.getName());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setString(4, product.getDescription());
            pstmt.executeUpdate();
        }
    }

    // 제품 ID로 검색 (Read)
    public Product getProductById(String productId) throws SQLException {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getString("product_id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("description")
                    );
                }
            }
        }
        return null;
    }

    // 모든 제품 검색 (Read)
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                products.add(new Product(
                        rs.getString("product_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description")
                ));
            }
        }
        return products;
    }

    // 제품 정보 업데이트 (Update)
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE product SET name = ?, price = ?, description = ? WHERE product_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setString(3, product.getDescription());
            pstmt.setString(4, product.getId());
            pstmt.executeUpdate();
        }
    }

    // 제품 삭제 (Delete)
    public void deleteProduct(String productId) throws SQLException {
        String sql = "DELETE FROM product WHERE product_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productId);
            pstmt.executeUpdate();
        }
    }
}
