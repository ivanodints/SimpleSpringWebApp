package ru.geekbrains.art_shop;

import org.springframework.data.jpa.domain.Specification;

public final class UserSpecification {

    public static Specification<ru.geekbrains.art_shop.User> usernameLike(String name) {
        return (root, query, cb) -> cb.like(root.get("username"), "%" + name + "%");
    }

    public static Specification<ru.geekbrains.art_shop.User> minAge(Integer minAge) {
        return (root, query, cb) -> cb.ge(root.get("age"), minAge);
    }

    public static Specification<ru.geekbrains.art_shop.User> maxAge(Integer maxAge) {
        return (root, query, cb) -> cb.le(root.get("age"), maxAge);
    }
}
