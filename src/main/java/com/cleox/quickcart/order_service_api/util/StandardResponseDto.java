package com.cleox.quickcart.order_service_api.util;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardResponseDto {
    private int status;
    private String message;
    private Object data;
}
