package com.hardziyevich.gateway.groomer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hardziyevich.gateway.command.Field;
import com.hardziyevich.gateway.command.Requester;
import com.hardziyevich.gateway.command.impl.FindAllGroomerBy;
import com.hardziyevich.gateway.command.impl.FindDay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class FindAllGroomerByTest {

    @Autowired
    private FindDay findDay;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    private Long[] testDay;

    private String[] groomer;

    private String content = "test";

    private String requestUrlDay = "/test";
    private String endpointDay = "/test";
    private String requestUrlGroomer = "/test1";
    private String endpointGroomer = "/test1";

    @BeforeAll
    public void setUp() throws Exception {
        Field.DAY.setValue(content);
        testDay = new Long[]{1L, 2L};
        String detailsString = objectMapper.writeValueAsString(testDay);
        this.server.expect(requestTo(requestUrlDay + endpointDay))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().string(content))
                .andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));

        groomer = new String[]{"first", "second"};
        String groomerTest = objectMapper.writeValueAsString(groomer);
        this.server.expect(requestTo(requestUrlGroomer + endpointGroomer))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().string(detailsString))
                .andRespond(withSuccess(groomerTest, MediaType.APPLICATION_JSON));
    }

    @Test
    void test() {
        //given
        FindAllGroomerBy findAllGroomerBy = new FindAllGroomerBy(requestUrlGroomer,endpointGroomer);
        findAllGroomerBy.setRequester(findDay);
        //when
        ResponseEntity<?> request = findAllGroomerBy.request();
        then(request.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(request.getBody()).isEqualTo(groomer);
    }
}
