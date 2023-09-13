package com.project.electronic.store.services;

import com.project.electronic.store.dto.CategoryDto;
import com.project.electronic.store.dto.PageableResponse;

public interface CategoryService {

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto, String categoryId);

    void delete(String categoryId);

    PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    CategoryDto get(String categoryId);

}
