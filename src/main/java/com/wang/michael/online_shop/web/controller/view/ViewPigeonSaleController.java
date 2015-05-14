package com.wang.michael.online_shop.web.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wang.michael.online_shop.exception.PigeonSaleNotFound;
import com.wang.michael.online_shop.model.PigeonSale;
import com.wang.michael.online_shop.service.PigeonSaleService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = "/pigeon_sales")
public class ViewPigeonSaleController extends BaseController {

    @Autowired
    private PigeonSaleService pigeonSaleService;

    @Value("${file.uri.root}")
    private String fileURIRoot;

    @ModelAttribute("fileURIRoot")
    public String defaultFileURIRoot() {
        return fileURIRoot;
    }

    @ModelAttribute("pageTitle")
    public String defaultPageTitle() {
        return "PigeonSales";
    }

    @ModelAttribute("currentNavButton")
    public String getCurrentNavButton() {
        return "pigeon_sales";
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    public ModelAndView pigeonSaleListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("list-pigeon_sale");
        Page<PigeonSale> pigeonSalePage = pigeonSaleService.getPigeonSales(page - 1, size);
        mav.addObject("pigeonSalePage", pigeonSalePage);
        preparePaginationData(mav, "pigeonSalePage", pigeonSalePage, page, 10);
        mav.addObject("pageTitle", "pigeon.sale.list");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewPigeonSalePage(@PathVariable Integer id) throws PigeonSaleNotFound {
        ModelAndView mav = new ModelAndView("view-pigeon_sale");
        PigeonSale pigeonSale = null;
        pigeonSale = pigeonSaleService.findById(Long.valueOf(id));
        mav.addObject("pigeonSale", pigeonSale);
        mav.addObject("pageTitle", "page.empty.page.title");
        mav.addObject("pageTitleArg", pigeonSale.getName());
        return mav;
    }

}
