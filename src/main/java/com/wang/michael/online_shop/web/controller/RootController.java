package com.wang.michael.online_shop.web.controller;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wang.michael.online_shop.exception.SettingNotFound;
import com.wang.michael.online_shop.model.ContactUsMessage;
import com.wang.michael.online_shop.service.ContactUsMessageService;
import com.wang.michael.online_shop.service.NewsService;
import com.wang.michael.online_shop.service.SettingService;

@RestController
@RequestMapping(value = "/")
public class RootController extends BaseController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private SettingService settingService;

    @Autowired
    private ReCaptcha reCaptcha;

    @Autowired
    private ContactUsMessageService contactUsMessageService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexPage() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("currentNavButton", "home");
        mav.addObject("pageTitle", "Home");
        return mav;
    }

    @ModelAttribute("captchaScript")
    public String getCaptchaScript() {
        Properties options = new Properties();
        options.put("theme", "clean");
        return reCaptcha.createRecaptchaHtml("error message", options);
    }

    @RequestMapping(value = "/about_us", method = RequestMethod.GET)
    public ModelAndView aboutUsPage() {
        ModelAndView mav = new ModelAndView("about_us");
        String aboutUs = null;
        try {
            aboutUs = settingService.findByKey("about_us").getValue();
        } catch (SettingNotFound e) {
            aboutUs = "Default about us message. Please add about_us settings instead.";
        }
        mav.addObject("currentNavButton", "about_us");
        mav.addObject("aboutUs", aboutUs);
        mav.addObject("pageTitle", "About Us");
        return mav;
    }

    @RequestMapping(value = "/contact_us", method = RequestMethod.GET)
    public ModelAndView newContactUsMessagePage() {
        ModelAndView mav = new ModelAndView("contact_us", "contactUsMessage", new ContactUsMessage());
        String contactUs = null;
        try {
            contactUs = settingService.findByKey("contact_us").getValue();
        } catch (SettingNotFound e) {
            contactUs = "Default contact us message. Please add contact_us settings instead.";
        }
        mav.addObject("currentNavButton", "contact_us");
        mav.addObject("contactUs", contactUs);
        mav.addObject("pageTitle", "Contact Us");
        return mav;
    }

    @RequestMapping(value = "/contact_us", method = RequestMethod.POST)
    public ModelAndView saveContactUsMessage(@Valid ContactUsMessage contactUsMessage, BindingResult bindingResult, HttpServletRequest request,
            final RedirectAttributes redirectAttributes) {
        ReCaptchaResponse captchaResponse = reCaptcha.checkAnswer(request.getRemoteAddr(), request.getParameter("recaptcha_challenge_field"),
                request.getParameter("recaptcha_response_field"));
        if (!captchaResponse.isValid()) {
            return new ModelAndView("contact_us", "message", "recaptcha code error!");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("contact_us");
        }
        ModelAndView mav = new ModelAndView("redirect:/contact_us");
        contactUsMessageService.save(contactUsMessage);
        String message = "Thanks for contacting us. We will get back to you as soon as possible.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/strayed_pigeons", method = RequestMethod.GET)
    public ModelAndView strayedPigeonsPage() {
        ModelAndView mav = new ModelAndView("strayed_pigeons");
        String strayedPigeons = null;
        try {
            strayedPigeons = settingService.findByKey("strayed_pigeons").getValue();
        } catch (SettingNotFound e) {
            strayedPigeons = "Default about us message. Please add strayed_pigeons settings instead.";
        }
        mav.addObject("currentNavButton", "strayed_pigeons");
        mav.addObject("strayedPigeons", strayedPigeons);
        mav.addObject("pageTitle", "Found Strayed Pigeons");
        return mav;
    }

}
