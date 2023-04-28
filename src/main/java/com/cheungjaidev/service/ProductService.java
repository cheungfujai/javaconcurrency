package com.cheungjaidev.service;

import com.cheungjaidev.domain.Product;
import com.cheungjaidev.domain.ProductInfo;
import com.cheungjaidev.domain.Review;
import com.cheungjaidev.service.java1util.ProductInfoRunnable;
import com.cheungjaidev.service.java1util.ReviewRunnable;
import lombok.NonNull;

import static com.cheungjaidev.util.LoggerUtil.log;
import static com.cheungjaidev.util.CommonUtil.stopWatch;

public class ProductService {
    private ReviewService reviewService;
    private ProductInfoService productInfoService;

    public ProductService(
            ProductInfoService productInfoService,
            ReviewService reviewService
    ) {
        this.reviewService = reviewService;
        this.productInfoService = productInfoService;
    }


    public Product retrieveProductNoPerformanceEnhancement(
            @NonNull final String productId
    ) {
        stopWatch.start();

        ProductInfo productInfo = productInfoService.retrieveProductInfo(productId); // blocking
        Review review = reviewService.retrieveReviews(productId); // blocking

        stopWatch.stop();

        log("Total Time Taken : "+ stopWatch.getTime());

        return new Product(productId, productInfo, review);
    }

    public Product retrieveProductJava1(
            @NonNull final String productId
    ) throws InterruptedException {
        stopWatch.start();

        ProductInfoRunnable productInfoRunnable = new ProductInfoRunnable(productId, productInfoService);
        ReviewRunnable reviewRunnable = new ReviewRunnable(productId, reviewService);

        Thread productInfoThread = new Thread(productInfoRunnable);
        Thread reviewThread = new Thread(reviewRunnable);

        productInfoThread.start();
        reviewThread.start();

        productInfoThread.join();
        reviewThread.join();

        stopWatch.stop();

        log("Total Time Taken : "+ stopWatch.getTime());

        return new Product(productId, productInfoRunnable.getProductInfo(), reviewRunnable.getReview());
    }

}
