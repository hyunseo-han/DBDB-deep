package model.dao.mybatis.mapper;

import java.util.List;

import model.Product;

public interface MainMapper {
    // 카테고리 선택해서 물품 보여주기 
    List<Product> selectProductsByCategory(String category);

}
