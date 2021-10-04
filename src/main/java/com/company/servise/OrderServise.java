package com.company.servise;

import com.company.converter.CustomerConverter;
import com.company.converter.OrderConverter;
import com.company.dto.CountCountry;
import com.company.dto.GetCustomersLastOrders;
import com.company.dto.GetOrderDetails;
import com.company.dto.OrderDTO;
import com.company.entity.CustomerEntity;
import com.company.entity.DetailEntity;
import com.company.entity.OrderEntity;
import com.company.enums.OrderDetailStatus;
import com.company.enums.OrderStatus;
import com.company.exceptions.CustomerNotFoundException;
import com.company.exceptions.DetailsNotFoundException;
import com.company.exceptions.OrderNotFoundException;
import com.company.repository.CustomerRepository;
import com.company.repository.DetailRepository;
import com.company.repository.OrderRepository;
import com.company.repository.ProductRepository;
import org.hibernate.hql.internal.ast.DetailedSemanticException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServise {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private ProductRepository productRepository;

//    create
    public OrderDTO create(OrderDTO dto){

        Optional<CustomerEntity> optional = customerRepository.findById(dto.getCustomerId());
        if (!optional.isPresent()) {
            throw new CustomerNotFoundException("Customer topilmadi!!!");
        }

        OrderEntity entity = new OrderEntity();

        entity.setCustomerId(dto.getCustomerId());
        entity.setDate(LocalDateTime.now());
        entity.setDetailStatus(OrderDetailStatus.NO);
        entity.setOrderStatus(OrderStatus.FAILED);

        orderRepository.save(entity);
         dto.setId(entity.getId());
         dto.setCustomer(optional.get());
         dto.setDate(entity.getDate());

         return dto;
    }


//    3. orders_without_details
    public List<OrderDTO> ordersWithoutDetails(OrderDetailStatus status){

        LocalDateTime dateTime = LocalDateTime.of(2016, Month.SEPTEMBER,16,0,0,0,0);

        List<OrderEntity> list = orderRepository.findAllByDateBeforeAndDetailStatus(dateTime,status);

        if (list.isEmpty()){
            throw new OrderNotFoundException("Order not exists!!!");
        }

        return list.stream().map(OrderConverter::toDTO).collect(Collectors.toList());

    }


//    GET order/details?order_id={order_id}
    public GetOrderDetails getOrderDetails(Integer orderId){

        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderId);
        if (!orderEntityOptional.isPresent()){
            throw new OrderNotFoundException("Order not found!!!");
        }

        List<DetailEntity> detailEntityList = detailRepository.findByOrderId(orderId);
        if (detailEntityList.isEmpty()) {
            throw new DetailsNotFoundException("Order details not exists!!!");
        }

        OrderEntity entity = orderEntityOptional.get();

        GetOrderDetails details = new GetOrderDetails();
        details.setOrdId(orderId);
        details.setCustomerId(entity.getCustomerId());

        List<String> nameList = productRepository.productsName(orderId);
        details.setProductName(nameList);

        return details;
    }


//    5. /customers_last_orders
    public List<GetCustomersLastOrders> customersLastOrders(){
        List<Integer> customerIdList = orderRepository.customerIdList();

        List<GetCustomersLastOrders> list = new LinkedList<>();

        for (Integer customerId : customerIdList) {
            Optional<OrderEntity> optional = orderRepository.customersLastOrders(customerId);
            OrderEntity orderEntity = optional.get();

            String name = customerRepository.name(customerId);
            GetCustomersLastOrders lastOrders = new GetCustomersLastOrders();
            lastOrders.setCustomerId(orderEntity.getCustomerId());
            lastOrders.setCustomerName(name);
            lastOrders.setOrderTime(orderEntity.getDate());
            list.add(lastOrders);
        }
        return list;
    }


//    9. number_of_products_in_year
    public List<Object> countryCountOrders(){
        List<Object> list = orderRepository.countryCountOrders();
        return list;
    }


//    10. orders_without_invoices
    public List<Object> ordersWithoutInvoices(){
        List<Object> list = orderRepository.ordersWithoutInvoices();
        return list;
    }



}
