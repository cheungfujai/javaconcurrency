package com.cheungjaidev.service;

import com.cheungjaidev.domain.Product;
import com.cheungjaidev.domain.ProductInfo;
import com.cheungjaidev.domain.Review;
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


    public Product retrieveProduct(
            @NonNull final String productId
    ) {
        stopWatch.start();

        ProductInfo productInfo = productInfoService.retrieveProductInfo(productId); // blocking
        Review review = reviewService.retrieveReviews(productId); // blocking

        stopWatch.stop();

        log("Total Time Taken : "+ stopWatch.getTime());

        return new Product(productId, productInfo, review);
    }

}
