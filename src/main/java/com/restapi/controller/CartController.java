package com.restapi.controller;

import com.restapi.dao.CartDAO;
import com.restapi.model.Role;
import com.restapi.request.CartRequest;
import com.restapi.response.CartResponse;
import com.restapi.response.CategoryResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RolesAllowed(Role.USER)
public class CartController {


    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDAO cartDAO;

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUserCart(@PathVariable Long userId) {
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.findUserCart(userId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping("/usercart")
    public ResponseEntity<APIResponse> addToCart(@RequestBody CartRequest cartRequest) {
        List<CartResponse> cartResponse=cartDAO.mapToCartResponse(cartService.addToCart(cartRequest));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<APIResponse> deleteBookFromCart(@PathVariable Long userId,
                                                          @PathVariable Long productId) {
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.deleteProductFromCart(userId, productId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
