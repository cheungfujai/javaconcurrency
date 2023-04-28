package com.cheungjaidev.service;

import com.cheungjaidev.domain.ProductInfo;
import com.cheungjaidev.service.mock.ProductInfoClient;
import lombok.NonNull;

public class ProductInfoService {

    private ProductInfoClient productInfoClient;

    public ProductInfoService(
            ProductInfoClient productInfoClient
    ){
        this.productInfoClient = productInfoClient;
    }

    public ProductInfo retrieveProductInfo(
            @NonNull final String productId
    ){
        ProductInfo productInfo = productInfoClient.getProductInfo(productId);
        return productInfo;
    }
}
