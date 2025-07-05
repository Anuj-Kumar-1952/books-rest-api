package com.kumar.books_rest_api.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BookRequestDTO {
    @NotBlank(message = "Title must not be blank")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotBlank(message = "ISBN must not be blank")
    private String isbn;

    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotEmpty(message = "At least one author ID must be provided")
    private List<Long> authorIds;
}
