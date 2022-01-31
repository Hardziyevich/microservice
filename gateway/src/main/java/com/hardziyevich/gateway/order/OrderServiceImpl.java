package com.hardziyevich.gateway.order;

import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.resource.dto.RequestToOrderForRegistrationOrderDto;
import com.hardziyevich.resource.dto.SaveUserDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService{

    private static final Long WRONG_RESULT = 0L;
    private final Requester<Long> requester;
    private final RestTemplate restTemplate;
    private final String userUrl;
    private final String userOrderUrl;
    private final String userOrderEndpoint;

    public OrderServiceImpl(@Qualifier("findGroomerIdByNameAndLastName") Requester<Long> requester,
                            @Value("${service.saveuser.url}") String userUrl,
                            @Value("${service.userorder.order.url}")String userOrderUrl,
                            @Value("${endpoint.userorder.save}") String userOrderEndpoint) {
        this.requester = requester;
        this.restTemplate = requester.getRestTemplate();
        this.userUrl = userUrl;
        this.userOrderUrl = userOrderUrl;
        this.userOrderEndpoint = userOrderEndpoint;
    }

    @Override
    public Long saveOrder(OrderDto orderDto) {
        Long result = WRONG_RESULT;
        Long userId = getUserId(orderDto);
        Long groomerId = getGroomerId(orderDto);
        if(!userId.equals(WRONG_RESULT) && !groomerId.equals(WRONG_RESULT)) {
            RequestToOrderForRegistrationOrderDto requestToOrderForRegistrationOrderDto = new OrderDtoToRequestOrderForRegistrationDto().mapTo(orderDto);
            requestToOrderForRegistrationOrderDto.setGroomerId(groomerId);
            requestToOrderForRegistrationOrderDto.setUserId(userId);
            ResponseEntity<Long> response = restTemplate.postForEntity(userOrderUrl + userOrderEndpoint, requestToOrderForRegistrationOrderDto, Long.class);
            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                result = response.getBody();
            }
        }
        return result;
    }

    private Long getUserId(OrderDto orderDto) {
        SaveUserDto saveUserDto = new OrderDtoToSaveUserDto().mapTo(orderDto);
        ResponseEntity<Long> response = restTemplate.postForEntity(userUrl, saveUserDto, Long.class);
        return checkResponseEntity(response);
    }

    private Long getGroomerId(OrderDto orderDto) {
        Field.GROOMER.setValue(orderDto.getGroomer());
        ResponseEntity<Long> response = requester.request();
        return checkResponseEntity(response);

    }

    private Long checkResponseEntity(ResponseEntity<Long> response) {
        Long result = WRONG_RESULT;
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            result = response.getBody();
        }
        return result;
    }

}
