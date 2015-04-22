package com.wang.michael.online_shop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wang.michael.online_shop.service.NewsService;

@RestController
@RequestMapping(value = "/")
public class RootController {

  @Autowired
  private NewsService newsService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView indexPage() {
    ModelAndView mav = new ModelAndView("index");
    mav.addObject("pageTitle", "Home");
    return mav;
  }

  @RequestMapping(value = "/about_us", method = RequestMethod.GET)
  public ModelAndView aboutUsPage() {
    ModelAndView mav = new ModelAndView("about_us");
    mav.addObject("pageTitle", "About Us");
    return mav;
  }

  @RequestMapping(value = "/contact_us", method = RequestMethod.GET)
  public ModelAndView contactUsPage() {
    ModelAndView mav = new ModelAndView("contact_us");
    mav.addObject("pageTitle", "Contact Us");
    return mav;
  }
}
