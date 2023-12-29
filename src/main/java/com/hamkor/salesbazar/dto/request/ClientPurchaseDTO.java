package com.hamkor.salesbazar.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ClientPurchaseDTO {
    @NotBlank(message = "clint tanlanmadi")
    private Long client_id;

    @NotBlank(message = "birorta ham maxsulot tanlanmadi")
    private String products;
    @NotBlank(message = "norma kiritilmadi")
    private Integer normal_payment;

    @NotBlank(message = "interval kiritilmadi")
    private Long interval_id;

    private Long week_day;


}
