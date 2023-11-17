package com.restapi.dao;

import com.restapi.model.Category;
import com.restapi.request.CategoryRequest;
import com.restapi.response.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDAO {
    public CategoryResponse mapToCategoryResponse(List<Category> categories) {
        CategoryResponse categoryResponse = new CategoryResponse();
        ArrayList<CategoryRequest> categoryRequests = new ArrayList<>();
        for (Category category : categories) {
            categoryRequests.add(new CategoryRequest(category.getId(), category.getEquipmentName()));
        }
        categoryResponse.setCategories(categoryRequests);
        return categoryResponse;
    }
    public Category mapToCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        if (categoryRequest.getId() != null) {
            category.setId(categoryRequest.getId());
        }
        category.setEquipmentName(categoryRequest.getEquipmentName());
        return category;
    }
}
