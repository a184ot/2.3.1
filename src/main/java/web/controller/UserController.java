package web.controller;

import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    private String listUsers(ModelMap model) {
        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }

    @PostMapping("/admin")
    private String listUsersPost(ModelMap model) {
        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }

    @PostMapping("/update")
    private String editUserForm(@ModelAttribute("id") Long id, ModelMap model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("/updateUser")
    private String editedUsersSave(@ModelAttribute("id") Long id,
                                   @ModelAttribute("viewName") String viewName,
                                   @ModelAttribute("login") String login,
                                   @ModelAttribute("password") String password,
                                   @ModelAttribute("email") String email,
                                   @ModelAttribute("age") int age,
                                   @ModelAttribute("role") String role, ModelMap model) {
        User user = new User(id, viewName, login, password, email, age, role);
        userService.editUser(user);
        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }

    @PostMapping("/delete")
    private String deleteUser(@ModelAttribute("id") Long id, ModelMap model) {
        userService.deleteUser(id);
        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }

    @PostMapping("/create")
    private String createUserForm(ModelMap model) {
        return "create";
    }

    @PostMapping("/createUser")
    private String createNewUser(@ModelAttribute("viewName") String viewName,
                              @ModelAttribute("login") String login,
                              @ModelAttribute("password") String password,
                              @ModelAttribute("email") String email,
                              @ModelAttribute("age") int age, ModelMap model) {
        userService.add(new User(viewName, login, password, email, age, "user"));
        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }

}
