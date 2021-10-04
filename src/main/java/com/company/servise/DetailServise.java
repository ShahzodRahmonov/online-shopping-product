package com.company.servise;

import com.company.dto.DetailDTO;
import com.company.entity.DetailEntity;
import com.company.entity.OrderEntity;
import com.company.entity.ProductEntity;
import com.company.enums.OrderDetailStatus;
import com.company.exceptions.OrderNotFoundException;
import com.company.exceptions.ProdactNotFoundException;
import com.company.repository.DetailRepository;
import com.company.repository.OrderRepository;
import com.company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailServise {
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

//    detail create
    public DetailDTO create(DetailDTO dto){

        Optional<OrderEntity> orderEntity = orderRepository.findById(dto.getOrdId());
        if (!orderEntity.isPresent()) {
            throw new OrderNotFoundException("Order not found!!!");
        }

        OrderEntity orderEntity1 = orderEntity.get();

        Optional<ProductEntity> productEntity = productRepository.findById(dto.getProductId());
        if (!productEntity.isPresent()) {
            throw new ProdactNotFoundException("Prodact not found");
        }

        DetailEntity entity = new DetailEntity();

        entity.setOrderId(dto.getOrdId());
        entity.setProductId(dto.getProductId());
        entity.setQuantity(dto.getQuantity());

        detailRepository.save(entity);

        orderEntity1.setDetailStatus(OrderDetailStatus.YES);
        orderRepository.save(orderEntity1);

        dto.setId(entity.getId());
        return dto;
    }

//    7. high_demand_products
    public List<Object> highDemandProducts(){
        List<Object> list = detailRepository.highDemandProducts();
        return list;
    }

}
