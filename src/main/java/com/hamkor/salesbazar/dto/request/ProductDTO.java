package com.hamkor.salesbazar.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ProductDTO {
    @NotBlank(message = "product_type_id berilmadi")
    private Long product_type_id;
    @NotBlank(message = "name berilmadi")
    private String name;

}
