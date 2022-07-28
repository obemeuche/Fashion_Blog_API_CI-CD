package com.example.fashionblog.model.pageCriteria;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class CategoryPage {
    private int pageNumber = 0;
    private int pageSize = 1;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "name";
}
