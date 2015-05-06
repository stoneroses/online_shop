package com.wang.michael.online_shop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wang.michael.online_shop.exception.SettingNotFound;
import com.wang.michael.online_shop.service.NewsService;
import com.wang.michael.online_shop.service.SettingService;

@RestController
@RequestMapping(value = "/")
public class RootController extends BaseController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private SettingService settingService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexPage() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("pageTitle", "Home");
        return mav;
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
        mav.addObject("aboutUs", aboutUs);
        mav.addObject("pageTitle", "About Us");
        return mav;
    }

    @RequestMapping(value = "/contact_us", method = RequestMethod.GET)
    public ModelAndView contactUsPage() {
        ModelAndView mav = new ModelAndView("contact_us");
        String contactUs = null;
        try {
            contactUs = settingService.findByKey("contact_us").getValue();
        } catch (SettingNotFound e) {
            contactUs = "Default contact us message. Please add contact_us settings instead.";
        }
        mav.addObject("contactUs", contactUs);
        mav.addObject("pageTitle", "Contact Us");
        return mav;
    }
}
