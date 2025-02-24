package com.learnkafka.junit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.controller.LibraryEventsController;
import com.learnkafka.domain.LibraryEvent;
import com.learnkafka.producer.LibraryEventsProducer;
import com.learnkafka.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * LibraryEventsControllerUnitTest
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 24/02/2025 - 07:47
 * @since 1.17
 */
@WebMvcTest(LibraryEventsController.class)
public class LibraryEventsControllerUnitTest {

    public static final String URL = "/v1/libraryevent";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    LibraryEventsProducer libraryEventsProducer;

    @Test
    void postLibraryEvent() throws Exception {
        var json = objectMapper.writeValueAsString(TestUtil.libraryEventRecord());

        when(libraryEventsProducer.sendLibraryEvent_approach3(isA(LibraryEvent.class)))
                .thenReturn(null);

        mockMvc.perform(post(URL)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    void postLibraryEvent_invalidValues() throws Exception {
        var json = objectMapper.writeValueAsString(TestUtil.libraryEventRecordWithInvalidBook());

        when(libraryEventsProducer.sendLibraryEvent_approach3(isA(LibraryEvent.class)))
                .thenReturn(null);

        mockMvc.perform(post(URL)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }
}
