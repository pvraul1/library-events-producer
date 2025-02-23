package com.learnkafka.intg.controller;

import com.learnkafka.domain.LibraryEvent;
import com.learnkafka.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibraryEventsControllerIT {

    public static final String URL = "/v1/libraryevent";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void postLibraryEvent() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON.toString());
        var httpEntity = new HttpEntity<>(TestUtil.libraryEventRecord(), headers);

        var responseEntity = restTemplate.exchange(URL, HttpMethod.POST, httpEntity, LibraryEvent.class);

        assert responseEntity.getStatusCode().equals(HttpStatus.CREATED);
    }
}