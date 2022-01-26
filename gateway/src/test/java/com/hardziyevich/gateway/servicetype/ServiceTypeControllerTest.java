package com.hardziyevich.gateway.servicetype;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.gateway.groomer.impl.FindDay;
import com.hardziyevich.gateway.groomer.impl.FindGroomerIdByNameAndLastName;
import com.hardziyevich.resource.dto.ServiceDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(ServiceTypeController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServiceTypeControllerTest {

    @MockBean(name = "findGroomerIdByNameAndLastName")
    private FindGroomerIdByNameAndLastName findGroomerIdByNameAndLastName;

    @Autowired
    private MockMvc mvc;

    @Mock
    private RestTemplate restTemplate;

    @Autowired
    private JacksonTester<RequestServiceDto> requestDtoJacksonTester;


    @Test
    void testServiceTypeController() throws Exception {
        //given
        String groomer = "test test";
        String groomerId = "1";
        String day = "";
        ResponseEntity<Long> longResponseEntity = ResponseEntity.ok(1L);
        ServiceDto build = ServiceDto.builder()
                .groomerId(groomerId)
                .day(day)
                .build();
        String[] bodyResponse = new String[]{"test1","test2"};

        when(findGroomerIdByNameAndLastName.getRestTemplate()).thenReturn(restTemplate);
        when(findGroomerIdByNameAndLastName.request()).thenReturn((ResponseEntity) longResponseEntity);
        when(restTemplate.postForEntity("http://localhost:8081/service/search",build,String[].class))
                .thenReturn(ResponseEntity.ok(bodyResponse));

        RequestServiceDto requestServiceDto = new RequestServiceDto(groomer, day);
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.post("/service" + "/find").contentType(MediaType.APPLICATION_JSON)
                        .content(requestDtoJacksonTester.write(requestServiceDto).getJson())
        ).andReturn().getResponse();

        System.out.println(response.getStatus());
        System.out.println(response.getContentAsString());
    }
}
