package com.example.eat.interfaces;

import com.example.eat.application.CategoryService;
import com.example.eat.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void list() throws Exception{
        List<Category> categories = new ArrayList<> ();
        categories.add (Category.builder ().name ("Korean Food").build ());

        given(categoryService.getCategories ()).willReturn (categories);

        mvc.perform(get("/categories"))
                .andExpect (status().isOk())
                .andExpect (content ().string (containsString("Korean Food")));
    }

    @Test
    public void create() throws Exception{
        Category category = Category.builder().name("Korean Food").build ();
        given (categoryService.addCategory ("Korean Food")).willReturn (category);

        mvc.perform (post("/categories")
                .contentType (MediaType.APPLICATION_JSON)
                .content ("{\"name\":\"Korean Food\"}"))
                .andExpect (status().isCreated ())
                .andExpect (content().string ("{}"));

        //만들어진 것을 실제 사용되는지 확인을 verify
        verify(categoryService).addCategory("Korean Food");
    }

}