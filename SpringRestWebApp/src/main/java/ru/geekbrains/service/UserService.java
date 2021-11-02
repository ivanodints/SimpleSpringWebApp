package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.service.DTO.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAll();

    Page<UserDTO> findWithFilter(String usernameFilter, Integer minAge, Integer maxAge,
                                 Integer page, Integer size, String sortField);

    Optional<UserDTO> findById(long id);

    void save(UserDTO user);

    void delete(long id);
}
