package com.cheungjaidev.service.mock;

import com.cheungjaidev.domain.ProductInfo;
import com.cheungjaidev.domain.ProductOption;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

import static com.cheungjaidev.util.CommonUtil.delay;

@Data
@NoArgsConstructor
public class ProductInfoClient {
    public ProductInfo getProductInfo(
            @NonNull final String productId
    ) {

        /* Dependency -> Mocking External Service Call (Blocking) */
        delay(1000);
        List<ProductOption> productOptions = List.of(
                new ProductOption(1, "16GB", "Black", 599.99),
                new ProductOption(2, "32GB", "Black", 699.99),
                new ProductOption(3, "64GB", "Black", 799.99),
                new ProductOption(4, "128GB", "Black", 899.99),
                new ProductOption(5, "16GB", "Blue", 599.99),
                new ProductOption(6, "32GB", "Blue", 699.99),
                new ProductOption(7, "64GB", "Blue", 799.99),
                new ProductOption(8, "128GB", "Blue", 899.99),
                new ProductOption(9, "16GB", "White", 599.99),
                new ProductOption(10, "32GB", "White", 699.99),
                new ProductOption(11, "64GB", "White", 799.99),
                new ProductOption(12, "128GB", "White", 899.99)
        );

        return ProductInfo.builder()
                .productId(productId)
                .options(productOptions)
                .build();
    }
}
