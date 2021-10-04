package com.company.servise;

import com.company.converter.CustomerConverter;
import com.company.dto.CustomerDTO;
import com.company.entity.CustomerEntity;
import com.company.exceptions.CustomerExistsException;
import com.company.exceptions.CustomerNotFoundException;
import com.company.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServise {
    @Autowired
    private CustomerRepository customerRepository;

    //create
    public CustomerDTO create(CustomerDTO dto){

        Optional<CustomerEntity> optional = customerRepository.findByPhone(dto.getPhone());
        if (optional.isPresent()) {
            throw new CustomerExistsException("Bu telefon raqami oldin ro'yhatdan o'tgan!!!");
        }

        CustomerEntity entity = new CustomerEntity();
        entity.setName(dto.getName());
        entity.setCountry(dto.getCountry());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setCreatedDate(LocalDateTime.now());
        customerRepository.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    //getById
    public CustomerDTO getById(Integer id){

        Optional<CustomerEntity> optional = customerRepository.findById(id);
        if (!optional.isPresent()) {
            throw new CustomerNotFoundException("Customer topilmadi!!!");
        }

        CustomerEntity entity = optional.get();
        return CustomerConverter.toDTO(entity);
    }

//    4. customers_without_orders
    public List<CustomerDTO> customersWithoutOrders(){
        List<CustomerEntity> list = customerRepository.customersWithoutOrders();
        return list.stream().map(CustomerConverter::toDTO).collect(Collectors.toList());
    }






}
