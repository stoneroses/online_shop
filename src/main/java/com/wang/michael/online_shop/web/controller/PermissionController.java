package com.wang.michael.online_shop.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wang.michael.online_shop.exception.PermissionNotFound;
import com.wang.michael.online_shop.model.Permission;
import com.wang.michael.online_shop.service.PermissionService;

@RestController
@RequestMapping(value = "/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView newPermissionPage() throws Exception {
        ModelAndView mav = new ModelAndView("permission-new", "permission", new Permission());
        mav.addObject("pageTitle", "Create Permission");
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    public ModelAndView permissionListPage(Model model) {
        ModelAndView mav = new ModelAndView("permission-index");
        List<Permission> permissionList = permissionService.findAll();
        mav.addObject("permissionList", permissionList);
        mav.addObject("pageTitle", "Permission List");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editPermissionPage(@PathVariable Integer id) throws PermissionNotFound {
        ModelAndView mav = new ModelAndView("permission-edit");
        Permission permission = null;
        permission = permissionService.findById(Long.valueOf(id));
        mav.addObject("permission", permission);
        mav.addObject("pageTitle", "Edit Permission " + permission.getName());
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView davePermission(@Valid Permission permission, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("permission-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/permissions/list");
        permissionService.save(permission);
        String message = "Permission was successfully saved.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deletePermission(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws PermissionNotFound {
        ModelAndView mav = new ModelAndView("redirect:/permissions/list");
        permissionService.delete(Long.valueOf(id));
        String message = "Permission was successfully deleted.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewPermissionPage(@PathVariable Integer id) throws PermissionNotFound {
        ModelAndView mav = new ModelAndView("permission-view");
        Permission permission = null;
        permission = permissionService.findById(Long.valueOf(id));
        mav.addObject("permission", permission);
        mav.addObject("pageTitle", "View Permission " + permission.getName());
        return mav;
    }

}