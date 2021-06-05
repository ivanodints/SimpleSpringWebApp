package ru.geekbrains.art_shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface BasketRepo extends JpaRepository<BasketProduct, Long>, JpaSpecificationExecutor<Product> {


}