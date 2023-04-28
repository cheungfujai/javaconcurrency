package com.cheungjaidev.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @NonNull
    private String productId;
    @NonNull
    private ProductInfo productInfo;
    @NonNull
    private Review review;

}
