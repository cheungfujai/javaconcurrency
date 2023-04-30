package com.cheungjaidev;

import com.cheungjaidev.domain.Product;
import com.cheungjaidev.service.ProductInfoService;
import com.cheungjaidev.service.java5.ProductService;
import com.cheungjaidev.service.ReviewService;
import com.cheungjaidev.service.mock.ProductInfoClient;
import com.cheungjaidev.service.mock.ReviewClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import static com.cheungjaidev.util.LoggerUtil.log;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ProductInfoClient productInfoClient = new ProductInfoClient();
        ReviewClient reviewClient = new ReviewClient();

        ProductInfoService productInfoService = new ProductInfoService(productInfoClient);
        ReviewService reviewService = new ReviewService(reviewClient);

        // Java 5 Using ExecutorService
        final int cpuCore = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cpuCore);
        //ProductService productService = new ProductService(productInfoService, reviewService);
        ProductService productService = new ProductService(productInfoService, reviewService, executorService);

        final String productId = "1";
        Product product1 = productService.retrieveProduct(productId);
        log("Product is " + product1);

        executorService.shutdown();
    }
}