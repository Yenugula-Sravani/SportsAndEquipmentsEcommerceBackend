package com.restapi.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartRequest {

    private Long userId;
    private Long productId;
    private Integer count;
}
