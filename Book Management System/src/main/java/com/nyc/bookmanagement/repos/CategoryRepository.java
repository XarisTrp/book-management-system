package com.nyc.bookmanagement.repos;

import com.nyc.bookmanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}