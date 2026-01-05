package com.spss.glowlytic.mapper;

import com.spss.glowlytic.dto.response.general.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * This method is used to inject into controller to convert page entity to page response
 * instead of extending from base controller, we inject this class.
 */
@Component
public class PageMapper {
    public <T> PageResponse<T> toPageResponse(Page<T> page) {
        return PageResponse.<T>builder()
                .currentPage(page.getNumber() + 1)
                .totalPages(page.getTotalPages())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .data(page.getContent())
                .build();
    }
}