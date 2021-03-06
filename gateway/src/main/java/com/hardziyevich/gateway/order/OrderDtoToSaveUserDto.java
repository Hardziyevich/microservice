package com.hardziyevich.gateway.order;

import com.hardziyevich.resource.dto.SaveUserDto;
import com.hardziyevich.resource.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoToSaveUserDto implements Mapper<SaveUserDto,OrderDto> {

    @Override
    public SaveUserDto mapTo(OrderDto orderDto) {
        return SaveUserDto.builder()
                .firstName(orderDto.firstName())
                .lastName(orderDto.lastName())
                .email(orderDto.email())
                .build();
    }
}
