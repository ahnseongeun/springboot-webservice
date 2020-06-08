package com.example.eat.interfaces;

import com.example.eat.application.CategoryService;
import com.example.eat.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> list(){
        List<Category> categories= categoryService.getCategories();

        return categories;
    }

   /* @PostMapping("/categories")
    public ResponseEntity<?> create(
            @RequestBody Category resource
    ) throws URISyntaxException {
        String name= resource.getName ();
        Category category=  categoryService.addCategory (name);
        String url="/categories/"+category.getId();
        return ResponseEntity.created (new URI (url)).body ("{}");
    }*/
}