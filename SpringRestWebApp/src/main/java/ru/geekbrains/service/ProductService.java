package ru.geekbrains.service;


import org.springframework.data.domain.Page;
import ru.geekbrains.art_shop.BasketProduct;

import java.util.List;
import java.util.Optional;

public interface ProductService {

     List<ProductRepr> showAllProducts();
     List<ProductRest> showAllRestProducts();
//     List<BasketProduct> showBasket();

//     BasketProduct addProductToBasketById(Long id);

     Optional<ProductRepr> findProductById (Long id);
     Optional<ProductRest> findRestProductById (Long id);

     void saveProduct (ProductRepr productRepr);
     void saveRestProduct (ProductRest productRest);

     void deleteProductById (Long id);
     void deleteRestProductById (Long id);
//     public void deleteBasketProductById(Long id);


     Page<ProductRepr> findWithFilter(String productTitleFilter,
                                      Integer minPriceFiler,
                                      Integer maxPriceFilter,
                                      Integer pageNumber,
                                      Integer tableSize,
                                      String sort);

     Page<ProductRest> findWithRestFilter(String productTitleFilter,
                                      Integer minPriceFiler,
                                      Integer maxPriceFilter,
                                      Integer pageNumber,
                                      Integer tableSize,
                                      String sort);



}
