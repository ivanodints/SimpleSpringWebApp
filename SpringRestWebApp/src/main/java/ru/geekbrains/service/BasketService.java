package ru.geekbrains.service;

import ru.geekbrains.art_shop.BasketProduct;

import java.util.List;
import java.util.Optional;


public interface BasketService {

    List<BasketProduct> showBasket();

    BasketProduct addProductToBasketById(Long id);

    void deleteBasketProductById(Long id);

    Optional<BasketProduct> findProductInBasketById (Long id);



}
