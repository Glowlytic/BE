package com.spss.glowlytic.controller;

import com.spss.glowlytic.dto.request.CreateBrandRequest;
import com.spss.glowlytic.dto.request.UpdateBrandRequest;
import com.spss.glowlytic.dto.response.BrandResponse;
import com.spss.glowlytic.dto.response.general.ApiResponse;
import com.spss.glowlytic.dto.response.general.PageResponse;
import com.spss.glowlytic.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public ApiResponse<BrandResponse> createBrand(@RequestBody @Valid CreateBrandRequest request) {
        return ApiResponse.success(brandService.createBrand(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<BrandResponse> updateBrand(@PathVariable Long id, @RequestBody @Valid UpdateBrandRequest request) {
        return ApiResponse.success(brandService.updateBrand(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ApiResponse.success("Brand deleted successfully");
    }

    @GetMapping("/{id}")
    public ApiResponse<BrandResponse> getBrand(@PathVariable Long id) {
        return ApiResponse.success(brandService.getBrandById(id));
    }

    @GetMapping
    public ApiResponse<PageResponse<BrandResponse>> getAllBrands(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.success(brandService.getAllBrands(page, size));
    }
}