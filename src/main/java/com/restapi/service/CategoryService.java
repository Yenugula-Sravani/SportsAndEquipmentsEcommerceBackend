package com.restapi.service;

import com.restapi.dao.CategoryDAO;
import com.restapi.model.Category;
import com.restapi.repository.CategoryRepository;
import com.restapi.request.CategoryRequest;
import com.restapi.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    CategoryDAO categoryDAO;

    public CategoryResponse findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryDAO.mapToCategoryResponse(categories);
    }

    @Transactional
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = categoryDAO.mapToCategory(categoryRequest);
        categoryRepository.save(category);
        return findAll();
    }

    @Transactional
    public CategoryResponse updateCategory(CategoryRequest categoryRequest) {
        Category category = categoryDAO.mapToCategory(categoryRequest);
        categoryRepository.save(category);
        return findAll();
    }

    public CategoryResponse deleteCategoryById(Integer id) {
        categoryRepository.deleteById(Long.valueOf(id));
        return findAll();
    }
}
