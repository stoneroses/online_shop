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

import com.wang.michael.online_shop.exception.CustomerNotFound;
import com.wang.michael.online_shop.model.Customer;
import com.wang.michael.online_shop.model.CustomerGroup;
import com.wang.michael.online_shop.service.CustomerGroupService;
import com.wang.michael.online_shop.service.CustomerService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = "/admin/customers")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerGroupService customerGroupService;

    @ModelAttribute("allCustomerGroups")
    public List<CustomerGroup> getAllPermissions() {
        return customerGroupService.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView newCustomerPage() throws Exception {
        Customer customer = new Customer();
        ModelAndView mav = new ModelAndView("customer-new", "customer", customer);
        mav.addObject("pageTitle", "customer.page.title.create");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("customer_save")
    public ModelAndView saveCustomer(@Valid Customer customer, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("customer-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/admin/customers/list");
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "customer.successfully.saved");
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    @RequiresPermissions("customer_list")
    public ModelAndView customerListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("customer-index");
        Page<Customer> customerPage = customerService.getCustomers(page - 1, size);
        mav.addObject("customerPage", customerPage);
        preparePaginationData(mav, "customerPage", customerPage, page, 10);
        mav.addObject("pageTitle", "customer.page.title.list");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("customer_edit")
    public ModelAndView editCustomerPage(@PathVariable Integer id) throws CustomerNotFound {
        ModelAndView mav = new ModelAndView("customer-edit");
        Customer customer = null;
        customer = customerService.findById(Long.valueOf(id));
        mav.addObject("customer", customer);
        mav.addObject("pageTitle", "customer.page.title.edit");
        mav.addObject("pageTitleArg", customer.getName());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("customer_delete")
    public ModelAndView deleteCustomer(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws CustomerNotFound {
        ModelAndView mav = new ModelAndView("redirect:/admin/customers/list");
        customerService.delete(Long.valueOf(id));
        redirectAttributes.addFlashAttribute("message", "customer.successfully.deleted");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewCustomerPage(@PathVariable Integer id) throws CustomerNotFound {
        ModelAndView mav = new ModelAndView("customer-view");
        Customer customer = null;
        customer = customerService.findById(Long.valueOf(id));
        mav.addObject("customer", customer);
        mav.addObject("pageTitle", "customer.page.title.view");
        mav.addObject("pageTitleArg", customer.getName());
        return mav;
    }

}
