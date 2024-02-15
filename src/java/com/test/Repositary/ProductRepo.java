package com.test.Repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.test.Model.Product;

public interface ProductRepo extends JpaRepository<Product,Integer>  {
    List<Product> findAllByCategory_Id(int id);
}
