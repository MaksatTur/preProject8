package kg.max.crud.controller;

import kg.max.crud.model.Role;
import kg.max.crud.model.User;
import kg.max.crud.model.UserDTO;
import kg.max.crud.service.RoleService;
import kg.max.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "/")
    public ModelAndView allUsers() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> userList = userService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userList);
        modelAndView.addObject("email", principal.getUsername());
        modelAndView.addObject("userRoles", principal.getRoles());
        modelAndView.setViewName("list");
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView addPage(@ModelAttribute("userDTO") UserDTO userDTO) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("email", principal.getUsername());
        modelAndView.addObject("userRoles", principal.getRoles());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView addUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/");
        User user = new User(userDTO);
        Set<Role> roles = new HashSet<>();

        for (String role : userDTO.getRoles()) {
            if (role.equals("ROLE_USER")) {
                roles.add(roleService.getRoleById(1));
            }
            if (role.equals("ROLE_ADMIN")) {
                roles.add(roleService.getRoleById(2));
            }
        }
        if (user.getPassword() != null || !user.getPassword().isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        user.setRoles(roles);
        userService.insert(user);
        return modelAndView;
    }

    @GetMapping(value = "findOne")
    @ResponseBody
    public User findOne(long id){
        return userService.getUserById(id);
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");

        User user = userService.getUserById(id);


        if (user != null) {
            UserDTO userDTO = new UserDTO(user);

            for (Role role : user.getRoles()) {
                if (role.getName().equals("ROLE_ADMIN")) {
                    userDTO.setRoles(new ArrayList<>(Arrays.asList("ROLE_ADMIN")));
                }
                if (role.getName().equals("ROLE_USER")) {
                    userDTO.setRoles(new ArrayList<>(Arrays.asList("ROLE_USER")));
                }
            }
            modelAndView.addObject("userDTO", userDTO);
        }

        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public ModelAndView editUser(UserDTO userDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/");

        User user = new User(userDTO);
        if (user.getPassword().isEmpty() || user.getPassword() == null) {
            user.setPassword(userService.getUserPasswordById(user.getId()));
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        Set<Role> roles = new HashSet<>();

        for (String role : userDTO.getRoles()) {
            if (role.equals("ROLE_USER")) {
                roles.add(roleService.getRoleById(1));
            }
            if (role.equals("ROLE_ADMIN")) {
                roles.add(roleService.getRoleById(2));
            }
        }
        user.setRoles(roles);

        userService.update(user);
        return modelAndView;
    }

    /*@GetMapping(value = "/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/");
        User user = userService.getUserById(id);
        userService.delete(user);
        return modelAndView;
    }*/

    @PostMapping(value = "/delete")
    public ModelAndView deleteUser(User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/");
        userService.delete(user);
        return modelAndView;
    }

    @ModelAttribute("roles")
    public List<String> initRoles() {
        return roleService.getAllRolesName();
    }
}
