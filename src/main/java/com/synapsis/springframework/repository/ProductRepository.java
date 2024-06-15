package com.synapsis.springframework.repository;

import com.synapsis.springframework.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

  @Query("SELECT PR FROM Product PR "
      + "WHERE (:category = '' OR PR.category.id = :category) AND "
      + "(:name = '' OR PR.category.categoryName = :name)")
  List<Product> findByCategoryOrName(String category, String name);

  List<Product> findByIdIn(List<String> id);
}
