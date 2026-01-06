package com.spss.glowlytic.service.impl;

import com.spss.glowlytic.dto.request.CreateBrandRequest;
import com.spss.glowlytic.dto.request.UpdateBrandRequest;
import com.spss.glowlytic.dto.response.BrandResponse;
import com.spss.glowlytic.dto.response.general.PageResponse;
import com.spss.glowlytic.entity.Brand;
import com.spss.glowlytic.enums.ErrorCode;
import com.spss.glowlytic.exception.AppException;
import com.spss.glowlytic.mapper.BrandMapper;
import com.spss.glowlytic.mapper.PageMapper;
import com.spss.glowlytic.repository.BrandRepository;
import com.spss.glowlytic.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final PageMapper pageMapper;

    @Override
    @Transactional
    public BrandResponse createBrand(CreateBrandRequest request) {
        Brand brand = brandMapper.toBrand(request);
        brandRepository.save(brand);
        return brandMapper.toBrandResponse(brand);
    }

    @Override
    @Transactional
    public BrandResponse updateBrand(Long id, UpdateBrandRequest request) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUD));
        brandMapper.updateBrand(brand, request);
        return brandMapper.toBrandResponse(brandRepository.save(brand));
    }

    @Override
    @Transactional
    public void deleteBrand(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new AppException(ErrorCode.BRAND_NOT_FOUD);
        }
        brandRepository.deleteById(id);
    }

    @Override
    public BrandResponse getBrandById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUD));
        return brandMapper.toBrandResponse(brand);
    }

    @Override
    public PageResponse<BrandResponse> getAllBrands(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdAt").descending());
        Page<Brand> brandPage = brandRepository.findAll(pageable);
        Page<BrandResponse> responsePage = brandPage.map(brandMapper::toBrandResponse);
        return pageMapper.toPageResponse(responsePage);
    }
}