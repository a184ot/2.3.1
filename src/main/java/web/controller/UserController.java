package web.controller;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

    UserService userService = context.getBean(UserService.class);
    UserService userServiceImp = new UserServiceImp();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }

    @RequestMapping(value = "admin", method = RequestMethod.POST)
    public String listUsersPost(ModelMap model) {
        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String editUsers(@RequestParam String id, ModelMap model) {
        Long idLong = Long.parseLong(id);
        User user = userService.getUserById(idLong);

        model.addAttribute("user", user);
        return "update";
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
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

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteUser(@RequestParam String id, ModelMap model) {
        Long idLong = Long.parseLong(id);
        userService.deleteUser(idLong);

        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createUser(ModelMap model) {
        return "create";
    }

    @RequestMapping(value = "createUser", method = RequestMethod.POST)
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
