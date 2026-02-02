package com.example.buoi4.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotNull(message = "Giá không được để trống")
    @Min(value = 1, message = "Giá tối thiểu là 1")
    @Max(value = 9999999, message = "Giá tối đa là 9,999,999")
    private Double price;

    @Size(max = 200, message = "Tên hình ảnh không quá 200 ký tự")
    private String image;

    @NotBlank(message = "Vui lòng chọn danh mục")
    private String category;
}
