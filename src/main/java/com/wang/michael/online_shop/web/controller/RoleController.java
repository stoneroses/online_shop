package com.wang.michael.online_shop.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wang.michael.online_shop.exception.RoleNotFound;
import com.wang.michael.online_shop.model.Permission;
import com.wang.michael.online_shop.model.Role;
import com.wang.michael.online_shop.service.PermissionService;
import com.wang.michael.online_shop.service.RoleService;

@RestController
@RequestMapping(value = "/roles")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @ModelAttribute("allPermissions")
    public List<Permission> getAllPermissions() {
        return permissionService.findAll();
    }

    @ModelAttribute("pageTitle")
    public String defaultPageTitle() {
        return "Role";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequiresPermissions("role_create")
    public ModelAndView newRolePage() throws Exception {
        ModelAndView mav = new ModelAndView("role-new", "role", new Role());
        mav.addObject("pageTitle", "Create Role");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("role_save")
    public ModelAndView saveRole(@Valid Role role, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("role-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/roles/list");
        roleService.save(role);
        String message = "Role was successfully saved.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    @RequiresPermissions("role_list")
    public ModelAndView roleListPage(Model model) {
        ModelAndView mav = new ModelAndView("role-index");
        List<Role> roleList = roleService.findAll();
        mav.addObject("roleList", roleList);
        mav.addObject("pageTitle", "Role List");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("role_edit")
    public ModelAndView editRolePage(@PathVariable Integer id) throws RoleNotFound {
        ModelAndView mav = new ModelAndView("role-edit");
        Role role = null;
        role = roleService.findById(Long.valueOf(id));
        mav.addObject("role", role);
        mav.addObject("pageTitle", "Edit Role " + role.getName());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("role_delete")
    public ModelAndView deleteRole(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws RoleNotFound {
        ModelAndView mav = new ModelAndView("redirect:/roles/list");
        roleService.delete(Long.valueOf(id));
        String message = "Role was successfully deleted.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewRolePage(@PathVariable Integer id) throws RoleNotFound {
        ModelAndView mav = new ModelAndView("role-view");
        Role role = null;
        role = roleService.findById(Long.valueOf(id));
        mav.addObject("role", role);
        mav.addObject("pageTitle", "View Role " + role.getName());
        return mav;
    }

}
