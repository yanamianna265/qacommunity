package com.example.qacommunity.common;

import lombok.Data;
import java.util.List;

@Data
public class PageResult<T> {
    private Long total;
    private Integer page;
    private Integer size;
    private List<T> list;

    public static <T> PageResult<T> of(Long total, Integer page, Integer size, List<T> list) {
        PageResult<T> result = new PageResult<>();
        result.setTotal(total);
        result.setPage(page);
        result.setSize(size);
        result.setList(list);
        return result;
    }
}
