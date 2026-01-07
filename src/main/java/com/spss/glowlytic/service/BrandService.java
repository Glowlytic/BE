package com.spss.glowlytic.service;

import com.spss.glowlytic.dto.request.brand.CreateBrandRequest;
import com.spss.glowlytic.dto.request.brand.UpdateBrandRequest;
import com.spss.glowlytic.dto.response.brand.BrandResponse;
import com.spss.glowlytic.dto.response.general.PageResponse;

public interface BrandService {
    BrandResponse createBrand(CreateBrandRequest request);
    BrandResponse updateBrand(Long id, UpdateBrandRequest request);
    void deleteBrand(Long id);
    BrandResponse getBrandById(Long id);
    PageResponse<BrandResponse> getAllBrands(int page, int size);
}