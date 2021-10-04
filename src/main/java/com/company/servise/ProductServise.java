package com.company.servise;

import com.company.converter.ProductConverter;
import com.company.dto.ProductDTO;
import com.company.entity.CategoryEntity;
import com.company.entity.ImageEntity;
import com.company.entity.ProductEntity;
import com.company.exceptions.CategoryNotFoundException;
import com.company.exceptions.ImageNotFoundException;
import com.company.exceptions.ProdactNotFoundException;
import com.company.repository.CategoryRepository;
import com.company.repository.ImageRepository;
import com.company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServise {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageRepository imageRepository;

//    product create
    public ProductDTO create(ProductDTO dto){

        Optional<CategoryEntity> optional = categoryRepository.findById(dto.getCategoryId());

        if (!optional.isPresent()) {
            throw new CategoryNotFoundException("Category not found!!!");
        }

        Optional<ImageEntity> imageEntity = imageRepository.findById(dto.getPhotoId());

        if (!imageEntity.isPresent()) {
            throw new ImageNotFoundException("Image not found");
        }

        ProductEntity entity = new ProductEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCategoryId(dto.getCategoryId());
        entity.setPrice(dto.getPrice());
        entity.setPhotoId(dto.getPhotoId());
        productRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCategory(entity.getCategory());
        dto.setImage(imageEntity.get());

        return dto;
    }

//    bulk_products
    public List<ProductDTO> bulkProducts(){

        List<ProductEntity> list = productRepository.bulkProducts();
        List<ProductDTO> dtoList = new LinkedList<>();

        list.forEach(productEntity -> {

            ProductDTO dto = ProductDTO.builder().
                    id(productEntity.getId()).price(productEntity.getPrice()).build();

            dtoList.add(dto);
        });

        if (dtoList.isEmpty()) {
            throw new ProdactNotFoundException("Product not found!!!");
        }

        return dtoList;
    }


//    GET /product/list
    public List<ProductDTO> list(){
        List<ProductEntity> list = productRepository.findAll();
        return list.stream().map(ProductConverter::toDTO).collect(Collectors.toList());
    }


//    GET  /product/details?product_id={product_id}
    public ProductDTO productGetById(Integer id){

        Optional<ProductEntity> optional = productRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ProdactNotFoundException("Product not found!!!");
        }
        ProductEntity entity = optional.get();
        return ProductConverter.toDTO(entity);
    }


}
