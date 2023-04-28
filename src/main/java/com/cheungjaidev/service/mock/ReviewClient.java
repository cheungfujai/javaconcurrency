package com.cheungjaidev.service.mock;

import com.cheungjaidev.domain.ProductOption;
import com.cheungjaidev.domain.Review;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

import static com.cheungjaidev.util.CommonUtil.delay;

@Data
@NoArgsConstructor
public class ReviewClient {
    public Review getProductReview(
            @NonNull final String productId
    ) {
        delay(1000);
        return new Review(17, 4.68);
    }
}
