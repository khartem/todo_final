package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.CategoryRepository;
import com.example.demo.SERVER.tables.Category;
import com.example.demo.SERVER.tables.User;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/addCategory")
    Category createCategory(@RequestBody Category category){
        System.out.println(category);
        return this.categoryRepository.save(category);
    }

    @GetMapping("/getCategoryAll")
    List<Category> getCategoryAll(){
        return this.categoryRepository.findAll();
    }

    @GetMapping("/getCategory={id}")
    Category getClient(@PathVariable Long id){
        return this.categoryRepository.findCategoryById(id);
    }

    @GetMapping("/allClients")
    List<Category> getClientAll(){
        return this.categoryRepository.findAll();
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));

    }

    @PutMapping("/updateCategory/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryUpdate) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(categoryUpdate.getName());
                    category.setTasks(categoryUpdate.getTasks());
                    category.setCreated(categoryUpdate.getCreated());
                    category.setUpdated(categoryUpdate.getUpdated());
                    return categoryRepository.save(category);
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));
    }







}

