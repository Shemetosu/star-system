package com.spacestar.starsystem.util;

import com.spacestar.starsystem.model.dto.page.PageParamDto;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class PageUtils {

    public static Pageable page(PageParamDto param) {
        return StringUtils.isEmpty(param.sortField())
                ? buildPageable(param.pageNumber(), param.pageSize())
                : buildPageable(param.pageNumber(), param.pageSize(), param.sortOrder(), param.sortField());
    }

    public static Pageable page(int pageNumber, int pageSize) {
        return buildPageable(pageNumber, pageSize);
    }

    public static Pageable page(int pageNumber, int pageSize, String sortOrder, String sortField) {
        return buildPageable(pageNumber, pageSize, sortOrder, sortField);
    }

    //---

    private Pageable buildPageable(int pageNumber, int pageSize) {
        return PageRequest.of(
                pageNumber - 1,
                pageSize
        );
    }

    private Pageable buildPageable(int pageNumber, int pageSize, String sortOrder, String sortField) {
        return PageRequest.of(
                pageNumber - 1,
                pageSize,
                Sort.Direction.fromString(sortOrder),
                sortField);
    }
}
