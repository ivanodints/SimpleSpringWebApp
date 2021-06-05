package ru.geekbrains.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.art_shop.BasketProduct;
import ru.geekbrains.service.BasketService;
import ru.geekbrains.service.ProductService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/product/basket")
public class BasketResource {


    private final BasketService basketService;

    @Autowired
    public BasketResource(BasketService basketService){
        this.basketService = basketService;
    }

    //    --------------------------------------------Basket------------------------------------------------------------

    @GetMapping(path = "/all" +
            "", produces = "application/json")
    public List<BasketProduct> showAllBasketProducts() {
        return basketService.showBasket();
    }

    @DeleteMapping("/remove/{id}")
    public void deleteByBasketId(@PathVariable("id") Long id){

        basketService.deleteBasketProductById(id);

    }

    @PostMapping("/add/{id}")
    public BasketProduct putProductToBasket (@PathVariable("id") Long id){

        return basketService.addProductToBasketById(id);
    }










}
