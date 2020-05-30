package com.example.eat.application;

import com.example.eat.domain.Category;
import com.example.eat.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {

        this.categoryRepository =categoryRepository;
    }

    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        //regions.add (Region.builder ().name ("Seoul").build ());
        return categories;
    }


    public Category addCategory(String name) {
        Category category= Category.builder().name(name).build();
        categoryRepository.save(category);
        return  category;
    }
}
