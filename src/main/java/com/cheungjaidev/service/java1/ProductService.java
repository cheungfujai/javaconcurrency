package com.cheungjaidev.service.java1;

import com.cheungjaidev.domain.Product;
import com.cheungjaidev.domain.ProductInfo;
import com.cheungjaidev.domain.Review;
import com.cheungjaidev.service.ProductInfoService;
import com.cheungjaidev.service.ReviewService;
import lombok.NonNull;

import static com.cheungjaidev.util.CommonUtil.stopWatch;
import static com.cheungjaidev.util.LoggerUtil.log;

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


    public Product retrieveProduct(
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
