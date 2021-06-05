package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.art_shop.BasketProduct;
import ru.geekbrains.art_shop.BasketRepo;
import ru.geekbrains.art_shop.Product;
import ru.geekbrains.art_shop.ProductRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl implements BasketService {

    private final ProductRepo productRepo;
    private final BasketRepo basketRepo;

    @Autowired
    public BasketServiceImpl(ProductRepo productRepo, BasketRepo basketRepo) {
        this.productRepo = productRepo;
        this.basketRepo = basketRepo;
    }


    @Override
    public List<BasketProduct> showBasket() {

        return basketRepo.findAll().stream().map(BasketProduct::new).collect(Collectors.toList());

    }

    @Transactional
    @Override
    public BasketProduct addProductToBasketById(Long id) {


        Product product = productRepo.findById(id).get();

        BasketProduct basketProduct = basketRepo.save(new BasketProduct(product));
        return basketProduct;

    }

    @Override
    public void deleteBasketProductById(Long id) {
        basketRepo.deleteById(id);
    }

    @Override
    public Optional<BasketProduct> findProductInBasketById(Long id) {
        return basketRepo.findById(id).map(BasketProduct::new);
    }
}
