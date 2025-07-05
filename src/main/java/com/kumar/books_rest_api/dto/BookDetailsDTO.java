package com.kumar.books_rest_api.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BookDetailsDTO {
    private Long id;

    @NotBlank(message = "Title must not be blank")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotBlank(message = "ISBN must not be blank")
    private String isbn;
    
    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be positive")
    private Double price;
    private List<AuthorsSummaryDTO> authors;

}
