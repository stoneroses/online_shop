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

import com.wang.michael.online_shop.exception.TestimonialNotFound;
import com.wang.michael.online_shop.model.Testimonial;
import com.wang.michael.online_shop.service.TestimonialService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = "/admin/testimonials")
public class TestimonialController extends BaseController {

    @Autowired
    private TestimonialService testimonialService;

    @ModelAttribute("currentNavButton")
    public String getCurrentNavButton() {
        return "testimonials";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequiresPermissions("testimonial_create")
    public ModelAndView newTestimonialPage() {
        ModelAndView mav = new ModelAndView("testimonial-new", "testimonial", new Testimonial());
        mav.addObject("pageTitle", "testimonial.page.title.create");
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    public ModelAndView testimonialListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("testimonial-index");
        Page<Testimonial> testimonialPage = testimonialService.getTestimonial(page - 1, size);
        mav.addObject("testimonialPage", testimonialPage);
        preparePaginationData(mav, "testimonialPage", testimonialPage, page, 10);
        mav.addObject("pageTitle", "testimonial.page.title.list");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("testimonial_save")
    public ModelAndView saveTestimonial(@Valid Testimonial testimonial, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("testimonial-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/admin/testimonials/list");
        testimonialService.save(testimonial);
        redirectAttributes.addFlashAttribute("message", "testimonial.successfully.saved");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("testimonial_edit")
    public ModelAndView editTestimonialPage(@PathVariable Integer id) throws TestimonialNotFound {
        ModelAndView mav = new ModelAndView("testimonial-edit");
        Testimonial testimonial = null;
        testimonial = testimonialService.findById(Long.valueOf(id));
        mav.addObject("testimonial", testimonial);
        mav.addObject("pageTitle", "testimonial.page.title.edit");
        mav.addObject("pageTitleArg", testimonial.getTitle());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("testimonial_delete")
    public ModelAndView deleteTestimonial(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws TestimonialNotFound {
        ModelAndView mav = new ModelAndView("redirect:/admin/testimonials/list");
        testimonialService.delete(Long.valueOf(id));
        redirectAttributes.addFlashAttribute("message", "testimonial.successfully.deleted");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewTestimonialPage(@PathVariable Integer id) throws TestimonialNotFound {
        ModelAndView mav = new ModelAndView("testimonial-view");
        Testimonial testimonial = null;
        testimonial = testimonialService.findById(Long.valueOf(id));
        mav.addObject("testimonial", testimonial);
        mav.addObject("pageTitle", "testimonial.page.title.view");
        mav.addObject("pageTitleArg", testimonial.getTitle());
        return mav;
    }

}
