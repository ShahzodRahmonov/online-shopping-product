package com.company.controller;

import com.company.dto.DetailDTO;
import com.company.servise.DetailServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detail")
public class DetailController {
    @Autowired
    private DetailServise detailServise;

//    detail create
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DetailDTO dto){
        DetailDTO response = detailServise.create(dto);
        return ResponseEntity.ok().body(response);
    }


    //    7. high_demand_products
    @GetMapping("/highDemandProducts")
    public ResponseEntity<?> highDemandProducts(){
        List<Object> response = this.detailServise.highDemandProducts();
        return ResponseEntity.ok().body(response);
    }
}
