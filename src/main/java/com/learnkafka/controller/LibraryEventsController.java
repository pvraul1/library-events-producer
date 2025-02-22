package com.learnkafka.controller;

import com.learnkafka.domain.LibraryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * LibraryEventsController
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 22/02/2025 - 19:33
 * @since 1.17
 */
@RestController
@Slf4j
public class LibraryEventsController {

    @PostMapping("/v1/libraryevent")
    public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody final LibraryEvent libraryEvent) {

        log.info("LibraryEvent: {}", libraryEvent);

        // invoke kafka producer

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }

}
