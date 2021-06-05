package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.art_shop.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepo productRepo;


    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;

    }

    @Override
    public List<ProductRepr> showAllProducts() {

        return productRepo.findAll().stream().map(ProductRepr::new).collect(Collectors.toList());

    }

    @Transactional
    @Override
    public Optional<ProductRepr> findProductById(Long id) {

        return productRepo.findById(id).map(ProductRepr::new);
    }

    @Transactional
    @Override
    public void saveProduct(ProductRepr productRepr) {
        productRepo.save(new Product(productRepr));
    }


    @Transactional
    @Override
    public void deleteProductById(Long id) {
        productRepo.deleteById(id);
    }


    @Override
    public Page<ProductRepr> findWithFilter(String productTitleFilter,
                                            Integer minPriceFilter,
                                            Integer maxPriceFilter,
                                            Integer pageNumber,
                                            Integer tableSize,
                                            String sort) {

        Specification<Product> spec = Specification.where(null);

        if (productTitleFilter != null && !productTitleFilter.isBlank()) {
            spec = spec.and(ProductSpecification.titleLike(productTitleFilter));
        }
        if (minPriceFilter != null) {
            spec = spec.and(ProductSpecification.minPrice(minPriceFilter));
        }
        if (maxPriceFilter != null) {
            spec = spec.and(ProductSpecification.maxPrice(maxPriceFilter));
        }
        if (sort == null) {
            return productRepo.findAll(spec, PageRequest.of(pageNumber, tableSize))
                    .map(ProductRepr::new);

        } else if (sort.isEmpty()){
            return productRepo.findAll(spec, PageRequest.of(pageNumber, tableSize))
                    .map(ProductRepr::new);
        } else {
            return productRepo.findAll(spec, PageRequest.of(pageNumber, tableSize, Sort.by(sort).ascending()))
                    .map(ProductRepr::new);
        }
    }

    @Override
    public Page<ProductRest> findWithRestFilter(String productTitleFilter,
                                                Integer minPriceFilter,
                                                Integer maxPriceFilter,
                                                Integer pageNumber,
                                                Integer tableSize,
                                                String sort) {

        Specification<Product> spec = Specification.where(null);

        if (productTitleFilter != null && !productTitleFilter.isBlank()) {
            spec = spec.and(ProductSpecification.titleLike(productTitleFilter));
        }
        if (minPriceFilter != null) {
            spec = spec.and(ProductSpecification.minPrice(minPriceFilter));
        }
        if (maxPriceFilter != null) {
            spec = spec.and(ProductSpecification.maxPrice(maxPriceFilter));
        }
        if (sort == null) {
            return productRepo.findAll(spec, PageRequest.of(pageNumber, tableSize))
                    .map(ProductRest::new);

        } else if (sort.isEmpty()){
            return productRepo.findAll(spec, PageRequest.of(pageNumber, tableSize))
                    .map(ProductRest::new);
        } else {
            return productRepo.findAll(spec, PageRequest.of(pageNumber, tableSize, Sort.by(sort).ascending()))
                    .map(ProductRest::new);
        }
    }



    @Override
    public List<ProductRest> showAllRestProducts() {
        return productRepo.findAll().stream().map(ProductRest::new).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductRest> findRestProductById(Long id) {
        return productRepo.findById(id).map(ProductRest::new);
    }

    @Override
    public void saveRestProduct(ProductRest productRest) {
        Product productToSave = new Product(productRest);
        productRepo.save(productToSave);
        if (productRest.getId() == null) {
            productRest.setId(productToSave.getId());
        }
    }

    @Override
    public void deleteRestProductById(Long id) {
        productRepo.deleteById(id);
    }



}

