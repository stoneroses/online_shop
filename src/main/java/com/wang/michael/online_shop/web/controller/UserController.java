package com.wang.michael.online_shop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wang.michael.online_shop.exception.UserNotFound;
import com.wang.michael.online_shop.model.User;
import com.wang.michael.online_shop.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView newUserPage() throws Exception {
        ModelAndView mav = new ModelAndView("user-new", "user", new User());
        mav.addObject("pageTitle", "Create User");
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createNewUser(@ModelAttribute User user, final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/users/list");
        userService.create(user);
        String message = "User was successfully created.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    public ModelAndView userListPage(Model model) {
        ModelAndView mav = new ModelAndView("user-index");
        List<User> userList = userService.findAll();
        mav.addObject("userList", userList);
        mav.addObject("pageTitle", "User List");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable Integer id) throws UserNotFound {
        ModelAndView mav = new ModelAndView("user-edit");
        User user = null;
        user = userService.findById(Long.valueOf(id));
        mav.addObject("user", user);
        mav.addObject("pageTitle", "Edit User " + user.getName());
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute User user, @PathVariable Integer id, final RedirectAttributes redirectAttributes)
            throws UserNotFound {
        ModelAndView mav = new ModelAndView("redirect:/users/list");
        String message = "User was successfully updated.";
        userService.update(user);
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws UserNotFound {
        ModelAndView mav = new ModelAndView("redirect:/users/list");
        userService.delete(Long.valueOf(id));
        String message = "User was successfully deleted.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewUserPage(@PathVariable Integer id) throws UserNotFound {
        ModelAndView mav = new ModelAndView("user-view");
        User user = null;
        user = userService.findById(Long.valueOf(id));
        mav.addObject("user", user);
        mav.addObject("pageTitle", "View User " + user.getName());
        return mav;
    }

}
