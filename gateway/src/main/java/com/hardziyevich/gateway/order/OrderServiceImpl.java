package com.hardziyevich.gateway.order;

import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.gateway.config.EndpointUserOrderProperties;
import com.hardziyevich.gateway.config.ServiceProperties;
import com.hardziyevich.resource.dto.RequestToOrderForRegistrationOrderDto;
import com.hardziyevich.resource.dto.SaveUserDto;
import com.hardziyevich.resource.mapper.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record OrderServiceImpl(
        @Qualifier("findGroomerIdByNameAndLastName") Requester requester,
        ServiceProperties serviceProperties,
        EndpointUserOrderProperties endpointUserOrderProperties,
        Mapper<RequestToOrderForRegistrationOrderDto, OrderDto> requestToOrderForRegistrationOrderDtoOrderDtoMapper,
        Mapper<SaveUserDto, OrderDto> saveUserDtoOrderDtoMapper
) implements OrderService {

    private static final Long WRONG_RESULT = 0L;

    @Override
    public Long saveOrder(OrderDto orderDto) {
        Long result = WRONG_RESULT;
        Long userId = getUserId(orderDto);
        Long groomerId = getGroomerId(orderDto);
        if (!userId.equals(WRONG_RESULT) && !groomerId.equals(WRONG_RESULT)) {
            RequestToOrderForRegistrationOrderDto requestToOrderForRegistrationOrderDto = requestToOrderForRegistrationOrderDtoOrderDtoMapper.mapTo(orderDto);
            requestToOrderForRegistrationOrderDto.setGroomerId(groomerId);
            requestToOrderForRegistrationOrderDto.setUserId(userId);
            ResponseEntity<Long> response = requester.getRestTemplate().postForEntity(serviceProperties.orderUserUrl() + endpointUserOrderProperties.save(), requestToOrderForRegistrationOrderDto, Long.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                result = response.getBody();
            }
        }
        return result;
    }

    private Long getUserId(OrderDto orderDto) {
        SaveUserDto saveUserDto = saveUserDtoOrderDtoMapper.mapTo(orderDto);
        ResponseEntity<Long> response = requester.getRestTemplate().postForEntity(serviceProperties.userSaveUrl(), saveUserDto, Long.class);
        return checkResponseEntity(response);
    }

    private Long getGroomerId(OrderDto orderDto) {
        Field.GROOMER.setValue(orderDto.groomer());
        ResponseEntity<?> response = requester.request();
        return checkResponseEntity(response);

    }

    private Long checkResponseEntity(ResponseEntity<?> response) {
        Long result = WRONG_RESULT;
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            result = (Long) response.getBody();
        }
        return result;
    }

}
