package com.boot.graphql.service;

import com.boot.graphql.dtos.ProductDto;
import com.boot.graphql.entity.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(int id);

    List<Product> getAllProducts();

    Product updateProduct(int id , int productQuantity);

    Product updateProductQuantityShipment(int id, int productQuantity);

    Product createProduct(ProductDto productDto);

    String deleteProduct(int id);


    /*List<Product> getProductByProductPrice(float minPrice , float maxPrice);*/
}