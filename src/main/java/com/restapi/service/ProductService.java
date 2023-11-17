package com.restapi.service;

import com.restapi.dao.ProductDAO;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Product;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.ProductRepository;
import com.restapi.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductDAO productDAO;
    @Autowired
    ProductRepository productRepository;
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public List<Product> createProduct(@Valid ProductRequest productRequest) {
        Product product = productDAO.mapToProduct(productRequest);
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", productRequest.getCategoryId()));
        product.setCategory(category);
        productRepository.save(product);
        return findAll();
    }

    @Transactional
    public List<Product> updateProduct(@Valid ProductRequest productRequest) {
      Product product= productDAO.mapToProduct(productRequest);
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", productRequest.getCategoryId()));
        product.setCategory(category);
        productRepository.save(product);
        return findAll();
    }

    public List<Product> deleteById(Long id) {
        productRepository.deleteById(id);
        return findAll();
    }















}
