package com.example.feproduct.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class RestPageResponse<T> extends PageImpl<T> {

    private static final long serialVersionUID = 5835593096562217592L;

    private Sort sort;

    public RestPageResponse() {
        super(new ArrayList<T>());
    }

    public RestPageResponse(List<T> content) {
        super(content);
    }

    public RestPageResponse(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    /*
     * https://stackoverflow.com/questions/34647303/spring-resttemplate-with-paginated-api
     */
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RestPageResponse(//
                            @JsonProperty("content") List<T> content, // PageImpl
                            @JsonProperty("number") int number, // PageImpl
                            @JsonProperty("size") int size, // PageImpl
                            @JsonProperty("totalElements") long totalElements, // PageImpl
                            @JsonProperty("pageable") JsonNode pageable, //
                            @JsonProperty("sort") JsonNode sort, //
                            @JsonProperty("totalPages") int totalPages, // computed
                            @JsonProperty("first") boolean first, // computed
                            @JsonProperty("last") boolean last, // computed
                            @JsonProperty("empty") boolean empty, // computed
                            @JsonProperty("numberOfElements") int numberOfElements // computed
    ) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

}