package com.wang.michael.online_shop.web.controller;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wang.michael.online_shop.exception.PermissionNotFound;
import com.wang.michael.online_shop.model.Permission;
import com.wang.michael.online_shop.service.PermissionService;

@RestController
@RequestMapping(value = "/permissions")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequiresPermissions("permission_create")
    public ModelAndView newPermissionPage() throws Exception {
        ModelAndView mav = new ModelAndView("permission-new", "permission", new Permission());
        mav.addObject("pageTitle", "Create Permission");
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    @RequiresPermissions("permission_list")
    public ModelAndView permissionListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("permission-index");
        Page<Permission> permissionPage = permissionService.getPermissions(page - 1, size);
        mav.addObject("permissionPage", permissionPage);
        mav.addObject("previousPage", page - 1 > 1 ? page - 1 : 1);
        mav.addObject("currentPage", page);
        mav.addObject("nextPage", page + 1 < permissionPage.getTotalPages() ? page + 1 : permissionPage.getTotalPages());
        mav.addObject("pageSize", 10);
        mav.addObject("pageTitle", "Permission List");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("permission_edit")
    public ModelAndView editPermissionPage(@PathVariable Integer id) throws PermissionNotFound {
        ModelAndView mav = new ModelAndView("permission-edit");
        Permission permission = null;
        permission = permissionService.findById(Long.valueOf(id));
        mav.addObject("permission", permission);
        mav.addObject("pageTitle", "Edit Permission " + permission.getName());
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("permission_save")
    public ModelAndView savePermission(@Valid Permission permission, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
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
    @RequiresPermissions("permission_delete")
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
