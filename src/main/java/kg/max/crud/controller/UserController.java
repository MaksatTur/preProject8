package kg.max.crud.controller;

import kg.max.crud.model.Role;
import kg.max.crud.model.User;
import kg.max.crud.model.UserDTO;
import kg.max.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public ModelAndView aboutMe() {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserById(principal.getId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");

        modelAndView.addObject("user", user);
        modelAndView.addObject("email", principal.getUsername());
        modelAndView.addObject("userRoles", principal.getRoles());
        return modelAndView;
    }
}
