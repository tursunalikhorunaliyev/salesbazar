package com.hamkor.salesbazar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseData {
    private boolean status;
    private String message;
    private Object data;
}
