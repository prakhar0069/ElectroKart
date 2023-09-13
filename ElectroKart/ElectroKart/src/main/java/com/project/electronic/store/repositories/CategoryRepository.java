package com.project.electronic.store.repositories;

import com.project.electronic.store.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {



}
