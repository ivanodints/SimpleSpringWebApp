package ru.geekbrains.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.art_shop.BasketProduct;
import ru.geekbrains.art_shop.Product;
import ru.geekbrains.controller.BadRequestException;
import ru.geekbrains.controller.NotFoundException;
import ru.geekbrains.service.ProductRepr;
import ru.geekbrains.service.ProductRest;
import ru.geekbrains.service.ProductService;

import java.util.List;
import java.util.Optional;

//--------------------------------------------SWAGGER-------------------------------------------------------------------
//
//http://localhost:8095/spring-hibernate/swagger-ui/index.html#/
//http://localhost:8095/spring-hibernate/v3/api-docs
//
//----------------------------------------------------------------------------------------------------------------------


@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(path = "/all" +
            "", produces = "application/json")
    public List<ProductRest> showAllRestProducts() {
        return productService.showAllRestProducts();
    }

    @GetMapping(path = "/{id}")
    public ProductRest findByRestID(@PathVariable("id") Long id){
        return productService.findRestProductById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping("/filter")
    public Page<ProductRest> startPage(Model model,
                            @RequestParam("productTitleFilter") Optional<String> productTitleFilter,
                            @RequestParam("minPriceFilter") Optional<Integer> minPriceFilter,
                            @RequestParam("maxPriceFilter") Optional<Integer> maxPriceFilter,
                            @RequestParam("pageNumber") Optional<Integer> pageNumber,
                            @RequestParam("tableSize") Optional<Integer> tableSize,
                            @RequestParam("sort") Optional<String> sortBy) {



        return productService.findWithRestFilter(
                productTitleFilter.orElse(null),
                minPriceFilter.orElse(null),
                maxPriceFilter.orElse(null),
                pageNumber.orElse(1) - 1,
                tableSize.orElse(5),
                sortBy.orElse(null)
        );
    }

    @PostMapping(consumes = "application/json")
    public ProductRest createNewProduct(@RequestBody ProductRest productRest){
        if (productRest.getId() != null ){
            throw new BadRequestException();
        } else {
            productService.saveRestProduct(productRest);
            return productRest;
        }
    }


    @PutMapping(consumes = "application/json")
    public void update(@RequestBody ProductRest productRest){
        if (productRest.getId() == null){
            throw new BadRequestException();
        }
        productService.saveRestProduct(productRest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteByRestId(@PathVariable("id") Long id){
        productService.deleteRestProductById(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundException(NotFoundException ex) {
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> badRequestException(BadRequestException ex) {
        return new ResponseEntity<>("Bad Request", HttpStatus.NOT_FOUND);
    }

////    --------------------------------------------Basket------------------------------------------------------------
//
//    @GetMapping(path = "/basketProducts" +
//            "", produces = "application/json")
//    public List<BasketProduct> showAllBasketProducts() {
//        return productService.showBasket();
//    }
//
//    @DeleteMapping("/basket/remove/{id}")
//    public void deleteByBasketId(@PathVariable("id") Long id){
//        productService.deleteBasketProductById(id);
//    }
//
//    @PostMapping("/basket/add/{id}")
//    public BasketProduct putProductToBasket (@PathVariable("id") Long id){
//
//         return productService.addProductToBasketById(id);
//    }


}
