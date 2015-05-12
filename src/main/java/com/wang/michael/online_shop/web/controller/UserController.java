package com.wang.michael.online_shop.web.controller;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wang.michael.online_shop.exception.UserNotFound;
import com.wang.michael.online_shop.model.Role;
import com.wang.michael.online_shop.model.User;
import com.wang.michael.online_shop.service.RoleService;
import com.wang.michael.online_shop.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ModelAttribute("allRoles")
    public List<Role> getAllPermissions() {
        return roleService.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView newUserPage() throws Exception {
        User user = new User();
        String randomPassword = generateRandomPassword(8);
        user.setPassword(randomPassword);
        user.setConfirmPassword(randomPassword);
        ModelAndView mav = new ModelAndView("user-new", "user", user);
        mav.addObject("randomPassword", "randomPassword");
        mav.addObject("pageTitle", "Create User");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("user_save")
    public ModelAndView saveUser(@Valid User user, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("user-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/users/list");
        userService.save(user);
        String message = "User was successfully saved.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    @RequiresPermissions("user_list")
    public ModelAndView userListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("user-index");
        Page<User> userPage = userService.getUsers(page - 1, size);
        mav.addObject("userPage", userPage);
        preparePaginationData(mav, "userPage", userPage, page, 10);
        mav.addObject("pageTitle", "User List");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("user_edit")
    public ModelAndView editUserPage(@PathVariable Integer id) throws UserNotFound {
        ModelAndView mav = new ModelAndView("user-edit");
        User user = null;
        user = userService.findById(Long.valueOf(id));
        mav.addObject("user", user);
        mav.addObject("pageTitle", "Edit User " + user.getName());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("user_delete")
    public ModelAndView deleteUser(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws UserNotFound {
        ModelAndView mav = new ModelAndView("redirect:/users/list");
        userService.delete(Long.valueOf(id));
        String message = "User was successfully deleted.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/{id}/change_password", method = RequestMethod.GET)
    @RequiresPermissions("user_save")
    public ModelAndView changePasswordByAdmin(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws UserNotFound {
        ModelAndView mav = new ModelAndView("user-change-password");
        User user = userService.findById(Long.valueOf(id));
        mav.addObject("user", user);
        mav.addObject("pageTitle", "Change Password");
        return mav;
    }

    @RequestMapping(value = "/{id}/change_password", method = RequestMethod.POST)
    @RequiresPermissions("user_save")
    public ModelAndView savePasswordByAdmin(@RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "confirmPassword", required = true) String confirmPassword, final RedirectAttributes redirectAttributes)
            throws UserNotFound {

        if (!StringUtils.equals(password, confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "user.confirm.password.not.match");
            return new ModelAndView("redirect:/users/" + id + "/change_password");
        }
        ModelAndView mav = new ModelAndView("user-view");
        User user = userService.savePassword(Long.valueOf(id), password);
        mav.addObject("user", user);
        mav.addObject("pageTitle", "Change Password");
        mav.addObject("message", "Password updated successfully.");
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

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile() throws UserNotFound {
        ModelAndView mav = new ModelAndView("user-view");
        Subject currentUser = SecurityUtils.getSubject();
        User user = null;
        user = userService.getByEmail(currentUser.getPrincipal().toString());
        mav.addObject("user", user);
        mav.addObject("pageTitle", "View User " + user.getName());
        return mav;
    }

    @RequestMapping(value = "/change_password", method = RequestMethod.GET)
    public ModelAndView changePassword() throws UserNotFound {
        ModelAndView mav = new ModelAndView("user-change-password");
        Subject currentUser = SecurityUtils.getSubject();
        User user = userService.getByEmail(currentUser.getPrincipal().toString());
        user.setPassword("");
        mav.addObject("user", user);
        mav.addObject("pageTitle", "Change Password");
        return mav;
    }

    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public ModelAndView savePassword(@RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "confirmPassword", required = true) String confirmPassword, BindingResult result) throws UserNotFound {
        if (StringUtils.equals(password, confirmPassword)) {
            result.addError(new ObjectError("conformPassword", "{user.confirm.password.not.match}"));
            return new ModelAndView("redirect:/users/change_password");
        }
        ModelAndView mav = new ModelAndView("user-view");
        Subject currentUser = SecurityUtils.getSubject();
        User user = userService.savePassword(currentUser.getPrincipal().toString(), password);
        mav.addObject("user", user);
        mav.addObject("pageTitle", "Change Password");
        mav.addObject("message", "Password updated successfully.");
        return mav;
    }

    private String generateRandomPassword(int passwordLength) {
        Random random = new SecureRandom();
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789`~!@#$%^&*()-_+=";

        String pw = "";
        for (int i = 0; i < passwordLength; i++) {
            int index = (int) (random.nextDouble() * letters.length());
            pw += letters.substring(index, index + 1);
        }
        return pw;
    }
}
