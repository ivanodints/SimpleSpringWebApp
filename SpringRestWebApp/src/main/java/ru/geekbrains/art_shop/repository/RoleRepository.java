package ru.geekbrains.art_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.art_shop.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
