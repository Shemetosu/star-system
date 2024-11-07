package com.spacestar.starsystem.model.dto.page;


import java.util.List;

public record PageContentDto<E>(
        PageDto page,
        List<E> content
) {
}

//@AllArgsConstructor
//@Getter
//public class PageContentDto<E> {
//
//    private PageDto page;
//    private List<E> content;
//}
