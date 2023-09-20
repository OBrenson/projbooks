package com.boi.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class ControllerUtils {

    public static PageRequest preparePageRequest(int page, int size,
                                                 Optional<String> sortBy, Optional<Boolean> isDesc) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if(sortBy.isPresent()) {
            Sort sort = Sort.by(sortBy.get());
            if(isDesc.isPresent()) {
                if(isDesc.get()) {
                    sort = sort.descending();
                } else {
                    sort = sort.ascending();
                }
            }
            pageRequest = pageRequest.withSort(sort);
        }
        return pageRequest;
    }
}
