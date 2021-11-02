package ru.geekbrains.service;


import org.springframework.data.domain.Page;
import ru.geekbrains.service.DTO.ProductDTO;
import ru.geekbrains.service.DTO.ProductRest;

import java.util.List;
import java.util.Optional;

public interface ProductService {

     List<ProductDTO> showAllProducts();
     List<ProductRest> showAllRestProducts();
//     List<BasketProduct> showBasket();

//     BasketProduct addProductToBasketById(Long id);

     Optional<ProductDTO> findProductById (Long id);
     Optional<ProductRest> findRestProductById (Long id);

     void saveProduct (ProductDTO productDTO);
     void saveRestProduct (ProductRest productRest);

     void deleteProductById (Long id);
     void deleteRestProductById (Long id);
//     public void deleteBasketProductById(Long id);


     Page<ProductDTO> findWithFilter(String productTitleFilter,
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
