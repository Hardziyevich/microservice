package com.hardziyevich.gateway.groomer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.groomer.impl.FindGroomerIdByNameAndLastName;
import com.hardziyevich.gateway.groomer.impl.FindService;
import com.hardziyevich.resource.dto.PersonalDataGroomerDto;
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
@RestClientTest(FindGroomerIdByNameAndLastName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("classpath:test.properties")
public class FindGroomerIdByNameAndLastNameTest {

    @Autowired
    private FindGroomerIdByNameAndLastName findGroomerIdByNameAndLastName;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    private Long test;

    @BeforeAll
    public void setUp() throws Exception {
        String firstName = "test";
        String lastName = "test";
        PersonalDataGroomerDto personalDataGroomerDto = new PersonalDataGroomerDto(firstName,lastName);
        Field.GROOMER.setValue(firstName + " " + lastName);
        test = 1L;
        String detailsString = objectMapper.writeValueAsString(test);
        String expect = objectMapper.writeValueAsString(personalDataGroomerDto);
        String requestUrl = "/test";
        String endpointGroomer = "/test";
        this.server.expect(requestTo(requestUrl + endpointGroomer))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().string(expect))
                .andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
    }

    @Test
    void request() {
        ResponseEntity<?> request = findGroomerIdByNameAndLastName.request();
        Long body = (Long) request.getBody();
        then(request.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(body).isEqualTo(test);
    }

}
