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

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String listUsers(ModelMap model) {
        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }

    @PostMapping("/admin")
    public String listUsersPost(ModelMap model) {
        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }

    @PostMapping("/update")
    public String editUsers(@RequestParam String id, ModelMap model) {
        Long idLong = Long.parseLong(id);
        User user = userService.getUserById(idLong);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("/updateUser")
    public String editUsers2(@RequestParam String id, @RequestParam String viewName,
                           @RequestParam String login, @RequestParam String password,
                           @RequestParam String email, @RequestParam String age,
                           @RequestParam String role, ModelMap model) {
        Long idLong = Long.parseLong(id);
        int ageInt = Integer.parseInt(age);
        User user = new User(idLong, viewName, login, password, email, ageInt, role);
        userService.editUser(user);
        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam String id, ModelMap model) {
        Long idLong = Long.parseLong(id);
        userService.deleteUser(idLong);
        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }

    @PostMapping("/create")
    public String createUser(ModelMap model) {
        return "create";
    }

    @PostMapping("/createUser")
    public String createNewUser(@RequestParam(value = "viewName", required = true) String viewName,
                              @RequestParam(value = "login", required = true) String login,
                              @RequestParam(value = "password", required = true) String password,
                              @RequestParam(value = "email", required = true) String email,
                              @RequestParam(value = "age", required = true) String age, ModelMap model) {

        int ageInt = Integer.parseInt(age);
        userService.add(new User(viewName, login, password, email, ageInt, "user"));
        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }

}
