package com.test.Repositary;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.Model.Category;


@Repository
public interface CategoryRepo extends CrudRepository<Category, Integer> {

}
