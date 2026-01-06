package com.spss.glowlytic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BrandResponse {

    private Long id;

    private String name;

    private String logoUrl;

    private String description;
}
