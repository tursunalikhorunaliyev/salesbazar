package com.hamkor.salesbazar.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class HamkorClientDTO {

    @NotBlank(message = "firstname bo'sh b'lishi mumkin emas")
    private String firstname;
    @NotBlank(message = "lastname bo'sh bo'lishi mumkin emas")
    private String lastname;

    @NotBlank(message = "phone bo'sh bo'lishi mumkin emas")
    private String phone;

    @NotBlank(message = "businessName bo'sh bo'lishi mumkin emas")
    private String businessName;

}
