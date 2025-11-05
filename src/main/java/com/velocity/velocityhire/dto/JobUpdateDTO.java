package com.velocity.velocityhire.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobUpdateDTO {
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;
    
    @Size(min = 20, max = 5000, message = "Description must be between 20 and 5000 characters")
    private String description;
    
    private String category;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Budget must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Budget format is invalid")
    private Double budget;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Hourly rate must be greater than 0")
    @Digits(integer = 4, fraction = 2, message = "Hourly rate format is invalid")
    private Double hourlyRate;
    
    @Pattern(regexp = "OPEN|IN_PROGRESS|COMPLETED|CANCELLED", 
             message = "Status must be OPEN, IN_PROGRESS, COMPLETED, or CANCELLED")
    private String status;
    
    // No validation annotations like @NotBlank for update operations
    // Allows partial updates
}