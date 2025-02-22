package com.learnkafka.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Book
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 22/02/2025 - 19:15
 * @since 1.17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    Integer bookId;
    String bookName;
    String bookAuthor;

}
