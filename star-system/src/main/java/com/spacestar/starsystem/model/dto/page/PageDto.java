package com.spacestar.starsystem.model.dto.page;

public record PageDto(
        int pageNumber,
        int pageSize,
        int totalPages,
        long totalElements
) {
}

//@AllArgsConstructor
//@Getter
//public class PageDto {
//
//    private int pageNumber;
//    private int pageSize;
//    private int totalPages;
//    private int totalElements;
//}
