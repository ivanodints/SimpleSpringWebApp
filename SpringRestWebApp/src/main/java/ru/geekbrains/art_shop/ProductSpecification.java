package ru.geekbrains.art_shop;


import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.service.ProductRest;

public class ProductSpecification {

public static Specification<Product> titleLike(String title) {
    return (root, query, cb) -> cb.like(root.get("title"),  title);
}
    public static Specification<ProductRest> titleLikeRest(String title) {
        return (root, query, cb) -> cb.like(root.get("title"),  title);
    }

    public static Specification<Product> minPrice(Integer minPrice) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }
    public static Specification<ProductRest> minPriceRest(Integer minPrice) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> maxPrice(Integer maxPrice) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
    public static Specification<ProductRest> maxPriceRest(Integer maxPrice) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

}
