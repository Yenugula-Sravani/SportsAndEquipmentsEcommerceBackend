package com.restapi.service;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Cart;
import com.restapi.model.Product;
import com.restapi.repository.CartRepository;
import com.restapi.repository.ProductRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Cart> findUserCart(Long userId) {
        List<Cart> cart = cartRepository.findUserCart(userId)
                .orElseThrow(() -> new ResourceNotFoundException("cart", "userId", userId));
        return cart;
    }

    @Transactional
    public List<Cart> addToCart(CartRequest cartRequest) {

        //finding particular cart for particular user based on userId
       AppUser appUser= userRepository.findById(cartRequest.getUserId())
               .orElseThrow(() -> new ResourceNotFoundException("userId", "userId",
                       cartRequest.getUserId()));
       //finding product which is in the cart
      Product product= productRepository.findById(cartRequest.getProductId()).orElseThrow(
               ()->new ResourceNotFoundException("productId","productId",cartRequest.getProductId()));
       //We are getting particular user cart from cartrepository Derived queries.
      Optional<List<Cart>> userCart=cartRepository.findUserCart(cartRequest.getUserId());

        if (userCart.isPresent()) {
            boolean isPresent = false;
            for (Cart cart : userCart.get()) {
                if (cart.getProduct().getProductId().equals(cartRequest.getProductId())) {
                    cart.setCount(cartRequest.getCount());
                    cartRepository.save(cart);
                    isPresent = true;
                }
            }

            if (!isPresent){
                Cart cart = new Cart();
                cart.setAppUser(appUser);
                cart.setProduct(product);
                cart.setCount(cartRequest.getCount());
                cartRepository.save(cart);
            }
        }
        else {
            Cart cart = new Cart();
            cart.setAppUser(appUser);
            cart.setProduct(product);
            cart.setCount(cartRequest.getCount());
            cartRepository.save(cart);
        }
        return findUserCart(cartRequest.getUserId());
    }

    public List<Cart> deleteProductFromCart(Long userId, Long productId) {
        return findUserCart(userId);
    }
}
