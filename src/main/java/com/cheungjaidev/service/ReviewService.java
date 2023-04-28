package com.cheungjaidev.service;

import com.cheungjaidev.domain.Review;
import com.cheungjaidev.service.mock.ProductInfoClient;
import com.cheungjaidev.service.mock.ReviewClient;
import lombok.NonNull;

public class ReviewService {
    private ReviewClient reviewClient;

    public ReviewService(
            ReviewClient reviewClient
    ){
        this.reviewClient = reviewClient;
    }
    public Review retrieveReviews(
            @NonNull final String productId
    ) {
        Review review = reviewClient.getProductReview(productId);
        return review;
    }
}
