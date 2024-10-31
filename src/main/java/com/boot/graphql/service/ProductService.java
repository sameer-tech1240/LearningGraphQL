package com.boot.graphql.service;


import com.boot.graphql.dtos.ProductDto;
import com.boot.graphql.entity.Product;
import com.boot.graphql.repo.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper mapper;

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

    @Override
    public Product updateProductQuantityShipment(int id, int stock) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found" + id));
        int productQuantity = product.getProductQuantity();
        int addQuantity = productQuantity + stock;
        product.setProductQuantity(addQuantity);
        return productRepository.save(product);
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = new Product();

        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setProductQuantity(productDto.getProductQuantity());

        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id : " + id));
        if (product != null) {
            productRepository.deleteById(id);
        }
        return "delete product successfully";
    }



    /*@Override
    public List<Product> getProductByProductPrice(float minPrice , float maxPrice) {
        return productRepository.getProductByPriceBetween(minPrice , maxPrice);
    }
*/

}

