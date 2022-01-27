package com.hardziyevich.gateway.groomer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.impl.FindDay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(SpringExtension.class)
@RestClientTest(FindDay.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("classpath:test.properties")
public class FindDayTest {

    @Autowired
    private FindDay findDay;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    private Long[] test;

    private String content = "test";

    @BeforeAll
    public void setUp() throws Exception {
        test = new Long[]{1L, 2L};
        String detailsString = objectMapper.writeValueAsString(test);
        String requestUrl = "/test";
        String endpointGroomer = "/test";
        this.server.expect(requestTo(requestUrl + endpointGroomer))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().string(content))
                .andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
    }

    @Test
    void request() {
        Field.DAY.setValue(content);
        ResponseEntity<?> request = findDay.request();
        Long[] body = (Long[]) request.getBody();
        then(request.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(body).isEqualTo(test);
    }

}
