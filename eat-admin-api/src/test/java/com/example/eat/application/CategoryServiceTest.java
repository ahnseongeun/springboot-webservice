package com.example.eat.application;

import com.example.eat.domain.Category;
import com.example.eat.domain.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CategoryServiceTest {


    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks (this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void getRegions(){
        List<Category> Mockcategories =new ArrayList<> ();
        Mockcategories.add (Category.builder ().name ("Korean food").build ());
        given(categoryRepository.findAll ()).willReturn(Mockcategories);

        List<Category> categories= categoryService.getCategories();
        Category category= categories.get (0);
        assertThat(category.getName (),is("Korean food"));
    }

    @Test
    public void addCategory(){
        Category category= categoryService.addCategory ("Korean food");
        verify (categoryRepository).save (any());
        //given (regionService.addRegion ("Seoul")).willReturn (region);
        assertThat (category.getName (),is ("Korean food"));
    }
}