package ru.geekbrains.rest;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.BadRequestException;
import ru.geekbrains.controller.NotFoundException;
import ru.geekbrains.service.DTO.UserDTO;
import ru.geekbrains.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Tag(name = "User resource API", description = "API to manipulate User resource ...")
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/v1/user")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<UserDTO> findAll() {
        return userService.findAll().stream()
                .peek(u -> u.setPassword(null))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public UserDTO findById(@PathVariable("id") Long id) {
        UserDTO userDTO = userService.findById(id)
                .orElseThrow(NotFoundException::new);
        userDTO.setPassword(null);
        return userDTO;
    }

    @GetMapping("filter")
    public Page<UserDTO> listPage(
                           @RequestParam("usernameFilter") Optional<String> usernameFilter,
                           @RequestParam("ageMinFilter") Optional<Integer> ageMinFilter,
                           @RequestParam("ageMaxFilter") Optional<Integer> ageMaxFilter,
                           @Parameter(example = "1") @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sortField") Optional<String> sortField) {

        return userService.findWithFilter(
                usernameFilter.orElse(null),
                ageMinFilter.orElse(null),
                ageMaxFilter.orElse(null),
                page.orElse(1) - 1,
                size.orElse(3),
                sortField.orElse(null)
        );
    }

    @PostMapping(consumes = "application/json")
    public UserDTO create(@RequestBody UserDTO userDTO) {
        if (userDTO.getId() != null) {
            throw new BadRequestException();
        }
        userService.save(userDTO);
        return userDTO;
    }

    @PutMapping(consumes = "application/json")
    public void update(@RequestBody UserDTO userDTO) {
        if (userDTO.getId() == null) {
            throw new BadRequestException();
        }
        userService.save(userDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundException(NotFoundException ex) {
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> badRequestException(BadRequestException ex) {
        return new ResponseEntity<>("Bad request", HttpStatus.NOT_FOUND);
    }
}
