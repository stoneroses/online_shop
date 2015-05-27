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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wang.michael.online_shop.exception.CustomerGroupNotFound;
import com.wang.michael.online_shop.model.CustomerGroup;
import com.wang.michael.online_shop.service.CustomerGroupService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = "/admin/customer_groups")
public class CustomerGroupController extends BaseController {

    @Autowired
    private CustomerGroupService customerGroupService;

    @ModelAttribute("pageTitle")
    public String defaultPageTitle() {
        return "CustomerGroups";
    }

    @ModelAttribute("currentNavButton")
    public String getCurrentNavButton() {
        return "home";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequiresPermissions("customer_group_create")
    public ModelAndView newCustomerGroupPage() throws Exception {
        ModelAndView mav = new ModelAndView("customer-group-new", "customerGroup", new CustomerGroup());
        mav.addObject("pageTitle", "customer.group.page.title.create");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("customer_group_save")
    public ModelAndView saveCustomerGroup(@Valid CustomerGroup customerGroup, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("customer-group-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/admin/customer_groups/list");
        customerGroupService.save(customerGroup);
        redirectAttributes.addFlashAttribute("message", "customer.group.successfully.saved");
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    @RequiresPermissions("customer_group_list")
    public ModelAndView customerGroupListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("customer-group-index");
        Page<CustomerGroup> customerGroupPage = customerGroupService.getCustomerGroups(page - 1, size);
        mav.addObject("customerGroupPage", customerGroupPage);
        preparePaginationData(mav, "customerGroupPage", customerGroupPage, page, 10);
        mav.addObject("pageTitle", "customer.group.page.title.list");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("customer_group_edit")
    public ModelAndView editCustomerGroupPage(@PathVariable Integer id) throws CustomerGroupNotFound {
        ModelAndView mav = new ModelAndView("customer-group-edit");
        CustomerGroup customerGroup = null;
        customerGroup = customerGroupService.findById(Long.valueOf(id));
        mav.addObject("customerGroup", customerGroup);
        mav.addObject("pageTitle", "customer.group.page.title.edit");
        mav.addObject("pageTitleArg", customerGroup.getName());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("customer_group_delete")
    public ModelAndView deleteCustomerGroup(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws CustomerGroupNotFound {
        ModelAndView mav = new ModelAndView("redirect:/admin/customer_groups/list");
        customerGroupService.delete(Long.valueOf(id));
        redirectAttributes.addFlashAttribute("message", "customer.group.successfully.deleted");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewCustomerGroupPage(@PathVariable Integer id) throws CustomerGroupNotFound {
        ModelAndView mav = new ModelAndView("customer-group-view");
        CustomerGroup customerGroup = null;
        customerGroup = customerGroupService.findById(Long.valueOf(id));
        mav.addObject("customerGroup", customerGroup);
        mav.addObject("pageTitle", "customer.group.page.title.view");
        mav.addObject("pageTitleArg", customerGroup.getName());
        return mav;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @RequiresPermissions("customer_group_list")
    public @ResponseBody List<CustomerGroup> searchByName(@RequestParam("term") String term) {
        return customerGroupService.findByName(term);
    }

}
