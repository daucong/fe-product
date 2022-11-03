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

}
