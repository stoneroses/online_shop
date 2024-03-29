package com.wang.michael.online_shop.web.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wang.michael.online_shop.exception.RoleNotFound;
import com.wang.michael.online_shop.model.Permission;
import com.wang.michael.online_shop.model.Role;
import com.wang.michael.online_shop.service.PermissionService;
import com.wang.michael.online_shop.service.RoleService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = "/admin/roles")
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
        mav.addObject("pageTitle", "role.page.title.create");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("role_save")
    public ModelAndView saveRole(@Valid Role role, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("role-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/admin/roles/list");
        roleService.save(role);
        redirectAttributes.addFlashAttribute("message", "role.successfully.saved");
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    @RequiresPermissions("role_list")
    public ModelAndView roleListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("role-index");
        Page<Role> rolePage = roleService.getRoles(page - 1, size);
        mav.addObject("rolePage", rolePage);
        preparePaginationData(mav, "rolePage", rolePage, page, 10);
        mav.addObject("pageTitle", "role.page.title.list");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("role_edit")
    public ModelAndView editRolePage(@PathVariable Integer id) throws RoleNotFound {
        ModelAndView mav = new ModelAndView("role-edit");
        Role role = null;
        role = roleService.findById(Long.valueOf(id));
        mav.addObject("role", role);
        mav.addObject("pageTitle", "role.page.title.edit");
        mav.addObject("pageTitleArg", role.getName());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("role_delete")
    public ModelAndView deleteRole(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws RoleNotFound {
        ModelAndView mav = new ModelAndView("redirect:/admin/roles/list");
        roleService.delete(Long.valueOf(id));
        redirectAttributes.addFlashAttribute("message", "role.successfully.deleted");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewRolePage(@PathVariable Integer id) throws RoleNotFound {
        ModelAndView mav = new ModelAndView("role-view");
        Role role = null;
        role = roleService.findById(Long.valueOf(id));
        mav.addObject("role", role);
        mav.addObject("pageTitle", "role.page.title.view");
        mav.addObject("pageTitleArg", role.getName());
        return mav;
    }

}
