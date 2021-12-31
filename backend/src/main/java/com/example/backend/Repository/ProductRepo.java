package com.example.backend.Repository;
import com.example.backend.Model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<ProductModel, Long>
{
    @Query(value = "SELECT * FROM `product_info` WHERE `product_name` = :keyWord OR `product_type` = :keyWord", nativeQuery = true)
    List<ProductModel> searchProductsByNameOrType(String keyWord);

    @Query(value = "SELECT * FROM `product_info` WHERE `product_price` <= :productCost", nativeQuery = true)
    List<ProductModel> searchProductsUnderCost(String productCost);
}
