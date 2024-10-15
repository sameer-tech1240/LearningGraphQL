package com.boot.graphql.service;


import com.boot.graphql.entity.Product;
import com.boot.graphql.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).get();

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(int id, int productQuantity) {
        Product productUpdate = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found" + id));
        productUpdate.setProductQuantity(productQuantity);
        return productRepository.save(productUpdate);
    }


}

