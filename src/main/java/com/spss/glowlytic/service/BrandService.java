package com.spss.glowlytic.service;

import com.spss.glowlytic.dto.request.CreateBrandRequest;
import com.spss.glowlytic.dto.request.UpdateBrandRequest;
import com.spss.glowlytic.dto.response.BrandResponse;
import com.spss.glowlytic.dto.response.general.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandService {
    BrandResponse createBrand(CreateBrandRequest request);
    BrandResponse updateBrand(Long id, UpdateBrandRequest request);
    void deleteBrand(Long id);
    BrandResponse getBrandById(Long id);
    PageResponse<BrandResponse> getAllBrands(int page, int size);
}