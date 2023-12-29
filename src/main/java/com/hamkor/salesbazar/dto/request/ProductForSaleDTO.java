package com.hamkor.salesbazar.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ProductForSaleDTO {
    @NotBlank(message = "product ko'rsatilmadi")
    private Long product_id;
    @NotBlank(message = "soni berilmadi")
    private Integer count;
    @NotBlank(message = "maxsulot narxi kiritilmadi")
    private Integer product_price;

}
