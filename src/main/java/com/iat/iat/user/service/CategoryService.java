package com.iat.iat.user.service;

import com.iat.iat.user.dto.CategoryDto;
import com.iat.iat.user.model.Category;

import java.util.List;

public interface CategoryService {
    Category addCat(CategoryDto categoryDto);
    Category getCat(int id);
    List<Category> getAll();
    Category updateCat(CategoryDto categoryDto);
    Void deleteCat(int id);
}
