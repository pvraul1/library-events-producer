package com.learnkafka.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Book
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 22/02/2025 - 19:15
 * @since 1.17
 */
public record Book(
        @NotNull
        Integer bookId,

        @NotBlank
        String bookName,

        @NotBlank
        String bookAuthor
) {
}
