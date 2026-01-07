package com.spss.glowlytic.dto.request.brand;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBrandRequest {

    @NotBlank(message = "BRAND_NAME_REQUIRED")
    private String name;

    private String logoUrl;

    @NotBlank(message = "BRAND_DESCRIPTION_REQUIRED")
    private String description;
}
