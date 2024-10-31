package com.boot.graphql.repo;

import com.boot.graphql.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product , Integer> {
    List<Product> getProductByPriceBetween(float minPrice , float maxPrice);

}
