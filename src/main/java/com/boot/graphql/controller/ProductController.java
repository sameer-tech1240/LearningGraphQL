package com.boot.graphql.controller;

import com.boot.graphql.dtos.ProductDto;
import com.boot.graphql.entity.Product;
import com.boot.graphql.service.IProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Log4j2
public class ProductController {

    @Autowired
    private IProductService productService;

    @MutationMapping
    public Product createProduct(@Argument ProductDto productDto) {

        return productService.createProduct(productDto);
    }


    @QueryMapping
    public Product getProductById(@Argument int id) {
        Product productById = productService.getProductById(id);
        log.info("Fetch product by id from db : {}", productById);
        return productById;
    }

    @QueryMapping
    public List<Product> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        log.info("Fetch all products from db : {}", allProducts);
        return allProducts;
    }

    @MutationMapping
    public Product updateProductQuantity(@Argument int id, @Argument int productQuantity) {
        Product product = productService.updateProduct(id, productQuantity);
        log.info("Update product quantity from db : {}", product);
        return product;
    }


    @MutationMapping
    public Product updateProductQuantityShipment(@Argument int id, @Argument int stock) {
        Product product = productService.updateProductQuantityShipment(id, stock);
        log.info("Update product stock shipment from db : {}", product);
        return product;
    }



   /* @QueryMapping
    public List<Product> getProductByPriceBetween(float minPrice, float maxPrice) {
        List<Product> productByPriceBetween = productService.getProductByProductPrice(minPrice, maxPrice);
        log.info("Fetch product by price between from db : {}", productByPriceBetween);
        return productByPriceBetween;
    }*/


}
