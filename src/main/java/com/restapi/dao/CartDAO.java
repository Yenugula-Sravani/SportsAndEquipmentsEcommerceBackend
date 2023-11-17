package com.restapi.dao;

import com.restapi.model.Cart;
import com.restapi.response.CartResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartDAO {
    public List<CartResponse> mapToCartResponse(List<Cart> carts) {
        CartResponse cartResponse=new CartResponse();
        List<CartResponse> rs=new ArrayList<>();
        for(int i=0;i<carts.size();i++){
            cartResponse.setUserId(carts.get(i).getId());
            cartResponse.setProductId(carts.get(i).getProduct().getProductId());
            cartResponse.setProductName(carts.get(i).getProduct().getEquipmentName());
            cartResponse.setCount(carts.get(i).getCount());
            rs.add(cartResponse);
        }
        return rs;
    }
}
