package com.iat.iat.user.service.serviceImpl;

import com.iat.iat.exceptions.ResourceNotFoundException;
import com.iat.iat.user.converter.CategoryConverter;
import com.iat.iat.user.dao.CategoryDao;
import com.iat.iat.user.dto.CategoryDto;
import com.iat.iat.user.model.Category;
import com.iat.iat.user.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CategoryService {
    @Autowired CategoryDao categoryDao;
    @Autowired CategoryConverter categoryConverter;
    private final Logger logger = LoggerFactory.getLogger(CatServiceImpl.class);

    @Override
    public Category addCat(CategoryDto categoryDto) {
        logger.info("converting...");
        Category category =categoryConverter.dtoToEntity(categoryDto);
        logger.info("saving...");
        return categoryDao.save(category);
    }

    @Override
    public Category getCat(int id) {
        return categoryDao.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No such category With ID: " +id));
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category updateCat(CategoryDto categoryDto) {
        logger.info("getting category by id...");
        Category category= getCat(categoryDto.getId());
        logger.info("updating category Name ...");
        category.setName(categoryDto.getName());
        return categoryDao.save(category);
    }

    @Override
    public Void deleteCat(int id) {
        return null;
    }
}
