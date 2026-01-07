package com.spss.glowlytic.mapper;

import com.spss.glowlytic.dto.request.brand.CreateBrandRequest;
import com.spss.glowlytic.dto.request.brand.UpdateBrandRequest;
import com.spss.glowlytic.dto.response.brand.BrandResponse;
import com.spss.glowlytic.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    // Map from CreateRequest to Entity
    Brand toBrand(CreateBrandRequest request);

    // Map from Entity to Response
    BrandResponse toBrandResponse(Brand brand);

    void updateBrand(@MappingTarget Brand brand, UpdateBrandRequest request);
}