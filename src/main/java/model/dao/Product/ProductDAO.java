package model.dao.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.dao.JDBCUtil;

public class ProductDAO {
    private JDBCUtil jdbcUtil;

    public ProductDAO() {
        this.jdbcUtil = new JDBCUtil();
    }

    // 상품 등록
    public boolean addProduct(Product product) throws Exception {
        String sql = "INSERT INTO products (productId, regularPrice, rentalFee, description, deposit, rentalLocation, productPhoto, address, detailAddress, isBorrowed, customerId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = { product.getProductId(), product.getRegularPrice(), product.getRentalFee(), product.getDescription(), product.getDeposit(), product.getRentalLocation(), product.getProductPhoto(), product.getAddress(), product.getDetailAddress(), product.isBorrowed(), product.getCustomerId() };

        jdbcUtil.setSqlAndParameters(sql, params);

        try {
            int result = jdbcUtil.executeUpdate();
            return result > 0;
        } finally {
            jdbcUtil.close();
        }
    }

    // 상품 수정
    public boolean updateProduct(Product product) throws Exception {
        String sql = "UPDATE products SET regularPrice = ?, rentalFee = ?, description = ?, deposit = ?, rentalLocation = ?, productPhoto = ?, address = ?, detailAddress = ?, isBorrowed = ?, customerId = ? WHERE productId = ?";
        Object[] params = { product.getRegularPrice(), product.getRentalFee(), product.getDescription(), product.getDeposit(), product.getRentalLocation(), product.getProductPhoto(), product.getAddress(), product.getDetailAddress(), product.isBorrowed(), product.getCustomerId(), product.getProductId() };

        jdbcUtil.setSqlAndParameters(sql, params);

        try {
            int result = jdbcUtil.executeUpdate();
            return result > 0;
        } finally {
            jdbcUtil.close();
        }
    }

    // 상품 삭제
    public boolean deleteProduct(int productId) throws Exception {
        String sql = "DELETE FROM products WHERE productId = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { productId });

        try {
            int result = jdbcUtil.executeUpdate();
            return result > 0;
        } finally {
            jdbcUtil.close();
        }
    }

    // 상품 조회
    public List<Product> getAllProducts() throws SQLException {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM products";
        jdbcUtil.setSqlAndParameters(sql, null);

        try {
            ResultSet rs = jdbcUtil.executeQuery();
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
        } finally {
            jdbcUtil.close();
        }
        return productList;
    }
}
