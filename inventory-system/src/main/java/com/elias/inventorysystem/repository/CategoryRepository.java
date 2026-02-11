package com.elias.inventorysystem.repository;

import com.elias.inventorysystem.model.dto.response.CategoryResponse;
import com.elias.inventorysystem.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query("""
           SELECT new com.elias.inventorysystem.model.dto.response.CategoryResponse(
               c.id, c.name, c.description, COUNT(p)
           )
           FROM Category c
           LEFT JOIN c.products p
           GROUP BY c.id, c.name, c.description
           """)
    List<CategoryResponse> findAllCategories();

}
