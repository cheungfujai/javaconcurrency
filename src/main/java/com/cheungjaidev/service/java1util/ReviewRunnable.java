package com.cheungjaidev.service.java1util;

import com.cheungjaidev.domain.Review;
import com.cheungjaidev.service.ReviewService;

public class ReviewRunnable implements Runnable {

    private String productId;
    private Review review;

    private ReviewService reviewService;

    public ReviewRunnable(
            String productId,
            ReviewService reviewService
    ){
        this.productId = productId;
        this.reviewService = reviewService;
    }

    @Override
    public void run(){
        review = this.reviewService.retrieveReviews(productId);
    }

    public Review getReview(){
        return review;
    }

}
