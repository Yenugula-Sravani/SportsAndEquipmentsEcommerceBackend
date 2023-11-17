package com.restapi.dao;

import com.restapi.model.Product;
import com.restapi.request.ProductRequest;
import com.restapi.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO {

    public ProductResponse mapToProductResponse(List<Product> productList) {
        return new ProductResponse(productList);
    }

    public Product mapToProduct(ProductRequest productRequest) {
        Product product=new Product();
        if (productRequest.getProductId() != null) {
            product.setProductId(productRequest.getProductId());
        }
        product.setEquipmentName(productRequest.getEquipmentName());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setSpecifications(productRequest.getSpecifications());
        product.setPhoto(productRequest.getPhoto());
        return product;
    }
}
