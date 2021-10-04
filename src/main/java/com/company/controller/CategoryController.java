package com.company.controller;

import com.company.dto.CategoryDTO;
import com.company.servise.CategoryServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServise categoryServise;

//    category create
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CategoryDTO dto){
        CategoryDTO response = categoryServise.create(dto);
        return ResponseEntity.ok().body(response);
    }

    //    GET /category/list
    @GetMapping("/list")
    public ResponseEntity<?> list(){
        List<CategoryDTO> response = categoryServise.list();
        return ResponseEntity.ok().body(response);
    }

//    GET /category?product_id={product_id}
    @GetMapping()
    @ResponseBody
    public ResponseEntity<?> categoryProductId(@RequestParam("product_id") Integer id){
        CategoryDTO response = categoryServise.categoryProductId(id);
        return ResponseEntity.ok(response);
    }
}
