package ru.geekbrains.art_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.art_shop.BasketProduct;
import ru.geekbrains.art_shop.Product;


@Repository
public interface BasketRepo extends JpaRepository<BasketProduct, Long>, JpaSpecificationExecutor<Product> {


}