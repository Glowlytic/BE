package com.spss.glowlytic.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BrandResponse extends BaseResponse {

    private Long id;

    private String name;

    private String logoUrl;

    private String description;
}
