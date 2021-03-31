package com.iat.iat.user.converter;

import com.iat.iat.user.dto.CategoryDto;
import com.iat.iat.user.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {

    public CategoryDto entityToDto(Category category){
        CategoryDto categoryDto =new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    public  Category dtoToEntity(CategoryDto categoryDto){
        Category category =new Category();
        category.setName(categoryDto.getName());
        return category;
    }

    public List<CategoryDto> entityToDto(List<Category> categories){
        return categories.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<Category> dtoToEntity(List<CategoryDto> categoryDtos){
        return categoryDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
