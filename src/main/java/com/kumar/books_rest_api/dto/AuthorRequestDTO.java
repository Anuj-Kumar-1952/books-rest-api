package com.kumar.books_rest_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorRequestDTO {
    @NotBlank(message = "Author name must not be blank")
    @Size(max = 255, message = "Author name must not exceed 255 characters")
    private String name;

    @Size(max = 5000, message = "Biography must not exceed 5000 characters")
    private String bio;
}
