package com.company.controller;

import com.company.dto.ProductDTO;
import com.company.servise.ProductServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductServise productServise;

//    product create
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductDTO dto){
        ProductDTO response = productServise.create(dto);
        return ResponseEntity.ok().body(response);
    }

    //    8. bulk_products
    @GetMapping("/bulkProducts")
    ResponseEntity<?> bulkProducts(){
        List<ProductDTO> response = productServise.bulkProducts();
        return ResponseEntity.ok().body(response);
    }

//    GET /product/list
    @GetMapping("/list")
    public ResponseEntity<?> list(){
        List<ProductDTO> response = productServise.list();
        return ResponseEntity.ok(response);
    }


    //    GET  /product/details?product_id={product_id}
    @GetMapping("/details")
    public ResponseEntity<?> productGetById(@RequestParam("product_id") Integer id){
        ProductDTO response = productServise.productGetById(id);
        return ResponseEntity.ok().body(response);
    }

}
