package com.restapi.response;

import com.restapi.request.CartRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartResponse {

    private Long userId;
    private int count;
    private Long productId;
    private String productName;



}
