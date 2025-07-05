package com.kumar.books_rest_api.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
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
public class AuthorDetailsDTO {
    private Long id;

    @NotBlank(message = "Author name must not be blank")
    private String name;

    @Size(max = 5000, message = "Biography must not exceed 5000 characters")
    private String bio;
    
    private List<BooksSummaryDTO> books;

}
