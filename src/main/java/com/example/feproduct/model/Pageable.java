package com.example.feproduct.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pageable {
    private Integer page;
    private Integer limit;
    private String sortname;
    private String sortby;
    private Integer totalPage;
    private Long totalItem;

    public Pageable(Integer page, Integer limit, String sortname, String sortby) {
        this.page = page;
        this.limit = limit;
        this.sortname = sortname;
        this.sortby = sortby;
    }
}
