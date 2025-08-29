package com.example.fruithub.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class ProductDto {
    private String imageURL; // photo table
    private UUID id;
    private String name;
    private Integer stockQuantity;
    private Double price;
    private String unit;
}
