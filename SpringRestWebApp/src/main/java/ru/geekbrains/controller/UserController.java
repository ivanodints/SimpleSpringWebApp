package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.art_shop.Role;
import ru.geekbrains.art_shop.repository.RoleRepository;
import ru.geekbrains.service.DTO.UserDTO;
import ru.geekbrains.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Secured({"GUEST"})
    @GetMapping
    public String listPage(Model model,
                           @RequestParam("usernameFilter") Optional<String> usernameFilter,
                           @RequestParam("ageMinFilter") Optional<Integer> ageMinFilter,
                           @RequestParam("ageMaxFilter") Optional<Integer> ageMaxFilter,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sortField") Optional<String> sortField) {
        logger.info("List page requested");

        Page<UserDTO> users = userService.findWithFilter(
                usernameFilter.orElse(null),
                ageMinFilter.orElse(null),
                ageMaxFilter.orElse(null),
                page.orElse(1) - 1,
                size.orElse(5),
                sortField.orElse(null)
        );
        model.addAttribute("users", users);
        return "user";
    }

    @Secured({"GUEST"})
    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        logger.info("Edit page for id {} requested", id);

        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("user", userService.findById(id)
                .orElseThrow(NotFoundException::new));
        return "user_form";
    }

    @Secured({"ADMIN"})
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("user") UserDTO user, BindingResult result, Model model) {
        logger.info("Update endpoint requested");

        model.addAttribute("roles", roleRepository.findAll());
        if (result.hasErrors()) {
            return "user_form";
        }
        if (!user.getPassword().equals(user.getMatchingPassword())) {
            result.rejectValue("password", "", "Password not matching");
            return "user_form";
        }

        logger.info("Updating user with id {}", user.getId());
        userService.save(user);
        return "redirect:/user";
    }

    @Secured({"SUPER_ADMIN"})
    @GetMapping("/new")
    public String create(Model model) {
        logger.info("Create new user request");

        if(!roleRepository.existsById(1L)) {
            model.addAttribute("roles", roleRepository.save(new Role("ROLE_SUPER_ADMIN")));
            model.addAttribute("roles", roleRepository.save(new Role("ROLE_ADMIN")));
            model.addAttribute("roles", roleRepository.save(new Role("ROLE_GUEST")));
        }

        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("user", new UserDTO());
        return "user_form";
    }

    @Secured({"SUPER_ADMIN"})
    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        logger.info("User delete request");

        userService.delete(id);
        return "redirect:/user";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView mav = new ModelAndView("not_found");
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }
}
