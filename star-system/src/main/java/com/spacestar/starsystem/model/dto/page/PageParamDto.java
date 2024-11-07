package com.spacestar.starsystem.model.dto.page;

public record PageParamDto(
        int pageNumber,
        int pageSize,
        String sortOrder,
        String sortField
) {
}
