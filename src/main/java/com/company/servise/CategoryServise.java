package com.company.servise;

import com.company.converter.CategoryConverter;
import com.company.dto.CategoryDTO;
import com.company.entity.CategoryEntity;
import com.company.entity.ProductEntity;
import com.company.exceptions.CAtegoryExistsException;
import com.company.exceptions.CategoryNotFoundException;
import com.company.exceptions.ProdactNotFoundException;
import com.company.repository.CategoryRepository;
import com.company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServise {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

//    create
    public CategoryDTO create(CategoryDTO dto){

        Optional<CategoryEntity> optional = categoryRepository.findByName(dto.getName());
        if (optional.isPresent()) {
            throw new CAtegoryExistsException("Bu categoriya oldindan mavjud!!!");
        }

        CategoryEntity entity = new CategoryEntity();

        entity.setName(dto.getName());

        categoryRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }


//    GET /category/list
    public List<CategoryDTO> list(){
        List<CategoryEntity> list = categoryRepository.findAll();
        return list.stream().map(CategoryConverter::toDTO).collect(Collectors.toList());
    }


//    GET /category?product_id={product_id}
    public CategoryDTO categoryProductId(Integer id){

        Optional<ProductEntity> optional1 = productRepository.findById(id);
        if (!optional1.isPresent()) {
            throw new ProdactNotFoundException("Product not found!!!");
        }

        ProductEntity entity = optional1.get();

        Optional<CategoryEntity> optional2 = categoryRepository.findById(entity.getCategoryId());

        CategoryEntity categoryEntity = optional2.get();

        return CategoryConverter.toDTO(categoryEntity);
    }
}
