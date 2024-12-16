package com.example.Comandas.Controllers;


import com.example.Comandas.Models.Categories;
import com.example.Comandas.Repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")


public class CategoriesController {

    @Autowired
    private CategoriesRepository categorieRepository;

    //Método get all categories
    @GetMapping("/get/all")
    public ResponseEntity<List<Categories>> getAllCategories() {
        return ResponseEntity.ok(categorieRepository.findAll());
    }

    //método post categories
    @PostMapping("/add")
    public ResponseEntity<Categories> addCategory(@RequestBody Categories categories) {
        return ResponseEntity.created(URI.create("/api/categories/add")).body(categorieRepository.save(categories));
    }

    //método get por idCategory
    @GetMapping("/get/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable Long id) {
        return categorieRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //método put categories
    @PutMapping("/update/{id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable Long id, @RequestBody Categories categoriesDetails) {
        return categorieRepository.findById(id).map(categories -> {
            categories.setDetailsCategory(categoriesDetails.getDetailsCategory());
            return ResponseEntity.ok(categorieRepository.save(categories));
                }).orElse(ResponseEntity.notFound().build());
    }

    //método delete categories
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        return categorieRepository.findById(id).map(categories -> {
            categorieRepository.delete(categories);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
