package com.cheungjaidev.service.java5;

import com.cheungjaidev.domain.Product;
import com.cheungjaidev.domain.ProductInfo;
import com.cheungjaidev.domain.Review;
import com.cheungjaidev.service.ProductInfoService;
import com.cheungjaidev.service.ReviewService;
import lombok.NonNull;

import java.util.concurrent.*;

import static com.cheungjaidev.util.CommonUtil.stopWatch;
import static com.cheungjaidev.util.LoggerUtil.log;

public class ProductService {

    private ExecutorService executorService;
    private ReviewService reviewService;
    private ProductInfoService productInfoService;

    public ProductService(
            ProductInfoService productInfoService,
            ReviewService reviewService,
            ExecutorService executorService
    ) {
        this.reviewService = reviewService;
        this.productInfoService = productInfoService;
        this.executorService = executorService;
    }


    public Product retrieveProduct(
            @NonNull final String productId
    ) throws ExecutionException, InterruptedException, TimeoutException {
        stopWatch.start();

        Future<ProductInfo> productInfoFuture = executorService.submit(() -> productInfoService.retrieveProductInfo(productId));
        Future<Review> reviewFuture = executorService.submit(() -> reviewService.retrieveReviews(productId));

        ProductInfo productInfo = productInfoFuture.get(2, TimeUnit.SECONDS); // .get() here is blocking
        Review review = reviewFuture.get(); // .get() here is blocking
        // we cannot combine Future here at Java 5

        stopWatch.stop();

        log("Total Time Taken : "+ stopWatch.getTime());

        return new Product(productId, productInfo, review);
    }

}
