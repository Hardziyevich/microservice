package com.hardziyevich.gateway.groomer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hardziyevich.gateway.groomer.impl.FindAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(SpringExtension.class)
@RestClientTest(FindAll.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("classpath:test.properties")
public class FindAllTest {

    @Autowired
    private FindAll findAll;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    private String[] test;

    @BeforeAll
    public void setUp() throws Exception {
        test = new String[]{"test1", "test2"};
        String detailsString =
                objectMapper.writeValueAsString(test);

        String requestUrl = "/test";
        String endpointGroomer = "/test";
        this.server.expect(requestTo(requestUrl + endpointGroomer))
                .andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
    }

    @Test
    void request() {
        ResponseEntity<?> request = findAll.request();
        String[] body = (String[]) request.getBody();
        then(request.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(body).isEqualTo(test);
    }
}
