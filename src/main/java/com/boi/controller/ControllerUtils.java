package com.boi.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class ControllerUtils {

    public static PageRequest preparePageRequest(Integer page, Integer size,
                                                 Optional<String> sortBy, Optional<Boolean> isDesc) {

        PageRequest pageRequest = PageRequest.of(page, size);;
        Sort sort = getSort(sortBy, isDesc);
        if(sort != null) {
            pageRequest = pageRequest.withSort(sort);
        }
        return pageRequest;
    }

    public static Sort getSort(Optional<String> sortBy, Optional<Boolean> isDesc) {
        if(sortBy.isPresent()) {
            Sort sort = Sort.by(sortBy.get());
            if (isDesc.isPresent()) {
                if (isDesc.get()) {
                    sort = sort.descending();
                } else {
                    sort = sort.ascending();
                }
            }
            return sort;
        }
        return null;
    }
}
