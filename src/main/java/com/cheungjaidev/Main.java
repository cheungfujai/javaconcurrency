package com.cheungjaidev;

import com.cheungjaidev.domain.Product;
import com.cheungjaidev.service.ProductInfoService;
import com.cheungjaidev.service.ProductService;
import com.cheungjaidev.service.ReviewService;
import com.cheungjaidev.service.mock.ProductInfoClient;
import com.cheungjaidev.service.mock.ReviewClient;

import static com.cheungjaidev.util.LoggerUtil.log;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ProductInfoClient productInfoClient = new ProductInfoClient();
        ReviewClient reviewClient = new ReviewClient();

        ProductInfoService productInfoService = new ProductInfoService(productInfoClient);
        ReviewService reviewService = new ReviewService(reviewClient);

        ProductService productService = new ProductService(productInfoService, reviewService);

        final String productId = "1";
        Product product1 = productService.retrieveProductNoPerformanceEnhancement(productId);
        log("Product is " + product1);

        //Product product2 = productService.retrieveProductJava1(productId);
        //log("Product is " + product2);
    }
}