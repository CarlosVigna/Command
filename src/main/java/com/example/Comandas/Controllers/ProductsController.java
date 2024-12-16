package com.example.Comandas.Controllers;

import com.example.Comandas.Dtos.ProductsRecordDto;
import com.example.Comandas.Models.Products;
import com.example.Comandas.Repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProductsController {
    @Autowired
    private ProductsRepository productsRepository;

    //método get products
    @GetMapping("/get/all")
    public ResponseEntity<List<Products>> getProducts() {
        return ResponseEntity.ok(productsRepository.findAll());
    }

    //método post products
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> saveProducts(@RequestBody ProductsRecordDto productsRecordDto)   {
        Map<String, Object> response = new HashMap<>();

        try {

            var product = new Products();
            product.setDetails(productsRecordDto.details());
            product.setPrice(productsRecordDto.price());
            product.setStock(productsRecordDto.stock());
            product.setCategory(productsRecordDto.category());
            product.setUnit(productsRecordDto.unit());

            Products savedTitulo = productsRepository.save(product);

            response.put("message", "Produto salvo com sucesso!");
            response.put("success", true);


            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);

        } catch (Exception e) {
            response.put("message", "Erro ao salvar Produto: " + e.getMessage());
            response.put("success", false);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        return productsRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //método put product by id
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") Long id,
                                                @RequestBody ProductsRecordDto productsRecordDto) {
        return productsRepository.findById(id)
                .map(product -> {
                    product.setDetails(productsRecordDto.details());
                    product.setPrice(productsRecordDto.price());
                    product.setStock(productsRecordDto.stock());
                    product.setCategory(productsRecordDto.category());
                    product.setUnit(productsRecordDto.unit());

                    // Salva o produto atualizado
                    productsRepository.save(product);
                    return ResponseEntity.ok().build(); // Retorna status 200 com corpo vazio
                })
                .orElseGet(() -> {
                    // Caso o produto não seja encontrado, retorna 404 sem corpo
                    return ResponseEntity.notFound().build(); // Retorna status 404 com corpo vazio
                });
    }

        //método delete product by id
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
            Optional<Products> productOpt = productsRepository.findById(id);

            if (productOpt.isPresent()) {
                productsRepository.delete(productOpt.get());
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
