package com.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProductRequest {

    @NotNull(message = "ProductId should not be null and be unique")
    private Long productId;
    private Long categoryId;
    @NotEmpty
    @Size(min = 2, message = "Equipment name should not be null")
    private String equipmentName;
    @NotEmpty
    @Size(min = 2,  max = 20 ,message = "Description should be of minmum of 2 and maximum of 20 characters")
    private String description;
    @NotEmpty
    @Size(min = 2, message = "Every product must have specification")
    private String specifications;
    @NotNull(message = "Product price should not be null")
    private Double price;
    private byte[] photo;

}
