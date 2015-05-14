package com.wang.michael.online_shop.web.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wang.michael.online_shop.exception.TestimonialNotFound;
import com.wang.michael.online_shop.model.Testimonial;
import com.wang.michael.online_shop.service.TestimonialService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = "/testimonials")
public class ViewTestimonialController extends BaseController {

    @Autowired
    private TestimonialService testimonialService;

    @ModelAttribute("currentNavButton")
    public String getCurrentNavButton() {
        return "testimonials";
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    public ModelAndView testimonialListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("list-testimonial");
        Page<Testimonial> testimonialPage = testimonialService.getTestimonial(page - 1, size);
        mav.addObject("testimonialPage", testimonialPage);
        preparePaginationData(mav, "testimonialPage", testimonialPage, page, 10);
        mav.addObject("pageTitle", "testimonial.page.title.list");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewTestimonialPage(@PathVariable Integer id) throws TestimonialNotFound {
        ModelAndView mav = new ModelAndView("view-testimonial");
        Testimonial testimonial = null;
        testimonial = testimonialService.findById(Long.valueOf(id));
        mav.addObject("testimonial", testimonial);
        mav.addObject("pageTitle", "page.empty.page.title");
        mav.addObject("pageTitleArg", testimonial.getTitle());
        return mav;
    }

}
