package com.wang.michael.online_shop.web.controller.admin;

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

import com.wang.michael.online_shop.exception.PigeonSaleNotFound;
import com.wang.michael.online_shop.model.PigeonSale;
import com.wang.michael.online_shop.service.PigeonSaleService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = "/admin/pigeon_sales")
public class PigeonSaleController extends BaseController {

    @Autowired
    private PigeonSaleService pigeonSaleService;

    @ModelAttribute("pageTitle")
    public String defaultPageTitle() {
        return "PigeonSales";
    }

    @ModelAttribute("currentNavButton")
    public String getCurrentNavButton() {
        return "pigeon_sales";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequiresPermissions("pigeon_sale_create")
    public ModelAndView newPigeonSalePage() throws Exception {
        ModelAndView mav = new ModelAndView("pigeon_sale-new", "pigeonSale", new PigeonSale());
        mav.addObject("pageTitle", "pigeon.sale.page.title.create");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("pigeon_sale_save")
    public ModelAndView savePigeonSale(@Valid PigeonSale pigeonSale, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("pigeon_sale-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/admin/pigeon_sales/list");
        pigeonSaleService.save(pigeonSale);
        redirectAttributes.addFlashAttribute("message", "pigeon.sale.successfully.saved");
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    @RequiresPermissions("pigeon_sale_list")
    public ModelAndView pigeonSaleListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("pigeon_sale-index");
        Page<PigeonSale> pigeonSalePage = pigeonSaleService.getPigeonSales(page - 1, size);
        mav.addObject("pigeonSalePage", pigeonSalePage);
        preparePaginationData(mav, "pigeonSalePage", pigeonSalePage, page, 10);
        mav.addObject("pageTitle", "pigeon.sale.page.title.list");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("pigeon_sale_edit")
    public ModelAndView editPigeonSalePage(@PathVariable Integer id) throws PigeonSaleNotFound {
        ModelAndView mav = new ModelAndView("pigeon_sale-edit");
        PigeonSale pigeonSale = null;
        pigeonSale = pigeonSaleService.findById(Long.valueOf(id));
        mav.addObject("pigeonSale", pigeonSale);
        mav.addObject("pageTitle", "pigeon.sale.page.title.edit");
        mav.addObject("pageTitleArg", pigeonSale.getName());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("pigeon_sale_delete")
    public ModelAndView deletePigeonSale(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws PigeonSaleNotFound {
        ModelAndView mav = new ModelAndView("redirect:/admin/pigeon_sales/list");
        pigeonSaleService.delete(Long.valueOf(id));
        redirectAttributes.addFlashAttribute("message", "pigeon.sale.successfully.deleted");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewPigeonSalePage(@PathVariable Integer id) throws PigeonSaleNotFound {
        ModelAndView mav = new ModelAndView("pigeon_sale-view");
        PigeonSale pigeonSale = null;
        pigeonSale = pigeonSaleService.findById(Long.valueOf(id));
        mav.addObject("pigeonSale", pigeonSale);
        mav.addObject("pageTitle", "pigeon.sale.page.title.view");
        mav.addObject("pageTitleArg", pigeonSale.getName());
        return mav;
    }

}
