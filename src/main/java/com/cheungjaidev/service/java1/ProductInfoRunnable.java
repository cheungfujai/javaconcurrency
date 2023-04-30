package com.cheungjaidev.service.java1;

import com.cheungjaidev.domain.ProductInfo;
import com.cheungjaidev.service.ProductInfoService;

public class ProductInfoRunnable implements Runnable {

    private String productId;
    private ProductInfo productInfo;

    private ProductInfoService productInfoService;

    public ProductInfoRunnable(
            String productId,
            ProductInfoService productInfoService
    ){
        this.productId = productId;
        this.productInfoService = productInfoService;
    }

    @Override
    public void run(){
        productInfo = this.productInfoService.retrieveProductInfo(productId);
    }

    public ProductInfo getProductInfo(){
        return productInfo;
    }
}
