package com.microservice.orderservice.mapper;

import com.microservice.orderservice.dto.OrderLineItemsDto;
import com.microservice.orderservice.dto.OrderRequest;
import com.microservice.orderservice.model.OrderLineItems;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {


    public List<OrderLineItems> entityListToDto(List<OrderLineItemsDto> orderLineItemsDtoList) {
    List<OrderLineItems> orderLineItems = new ArrayList<>();
    OrderLineItems orderLine;
    for (OrderLineItemsDto dto: orderLineItemsDtoList){
            orderLine = new OrderLineItems();
            orderLine.setPrice(dto.getPrice());
            orderLine.setQuantity(dto.getQuantity());
            orderLine.setSkuCode(dto.getSkuCode());
            orderLineItems.add(orderLine);
    }
    return orderLineItems;
    }
}
