package com.hardziyevich.gateway.day;

import com.hardziyevich.gateway.command.impl.FindGroomerIdByNameAndLastName;
import com.hardziyevich.resource.dto.WorkingDayDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(DayController.class)
public class DayControllerTest {

    private static final String REST_TEMPLATE_URL = "http://localhost:8081/day/search";
    private static final String ENDPOINT = "/day" + "/find";

    @MockBean(name = "findGroomerIdByNameAndLastName")
    private FindGroomerIdByNameAndLastName findGroomerIdByNameAndLastName;

    @Autowired
    private MockMvc mvc;

    @Mock
    private RestTemplate restTemplate;

    @Autowired
    private JacksonTester<RequestDayDto> requestDtoJacksonTester;

    @Autowired
    private JacksonTester<String[]> jacksonTester;

    private JsonContent<RequestDayDto> write;
    private WorkingDayDto workingDayDto;

    @BeforeEach
    void init() throws IOException {
        String groomer = "test test";
        String service = "";
        String groomerId = "1";
        RequestDayDto requestDayDto = new RequestDayDto(groomer, service);
        write = requestDtoJacksonTester.write(requestDayDto);
        workingDayDto = WorkingDayDto.builder()
                .groomerId(groomerId)
                .serviceType(service)
                .build();
    }

    @Test
    @DisplayName("Request is valid")
    void testServiceTypeController() throws Exception {
        //given
        String[] bodyResponse = new String[]{"test1", "test2"};
        ResponseEntity<Long> longResponseEntity = ResponseEntity.ok(1L);
        ResponseEntity<String[]> ok = ResponseEntity.ok(bodyResponse);
        when(findGroomerIdByNameAndLastName.getRestTemplate()).thenReturn(restTemplate);
        when(findGroomerIdByNameAndLastName.request()).thenReturn(longResponseEntity);
        when(restTemplate.postForEntity(REST_TEMPLATE_URL, workingDayDto, String[].class))
                .thenReturn(ok);
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(write.getJson())
        ).andReturn().getResponse();
        then(response.getStatus()).isEqualTo(200);
        then(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(jacksonTester.write(bodyResponse).getJson());
    }

    @Test
    @DisplayName("Request is not valid")
    void requestToGroomerNotValid() throws Exception {
        //given

        when(findGroomerIdByNameAndLastName.getRestTemplate()).thenReturn(restTemplate);
        when(findGroomerIdByNameAndLastName.request()).thenReturn(ResponseEntity.badRequest().build());

        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(write.getJson())
        ).andReturn().getResponse();

        then(response.getStatus()).isEqualTo(400);
    }

    @Test
    @DisplayName("With null body")
    void testWithNullBody() throws Exception {

        when(findGroomerIdByNameAndLastName.getRestTemplate()).thenReturn(restTemplate);
        when(findGroomerIdByNameAndLastName.request()).thenReturn(ResponseEntity.ok().build());
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(write.getJson())
        ).andReturn().getResponse();

        then(response.getStatus()).isEqualTo(400);
    }
}
