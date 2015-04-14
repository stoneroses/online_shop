package com.wang.michael.online_shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wang.michael.online_shop.exception.NewsNotFound;
import com.wang.michael.online_shop.model.News;
import com.wang.michael.online_shop.service.NewsService;

@RestController
@RequestMapping(value = "/news")
public class NewsController {

  @Autowired
  private NewsService newsServvice;

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public ModelAndView newNewsPage() {
    ModelAndView mav = new ModelAndView("news/news-new", "news", new News());
    return mav;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ModelAndView createNewNews(@ModelAttribute News news, final RedirectAttributes redirectAttributes) {
    ModelAndView mav = new ModelAndView();
    newsServvice.create(news);
    mav.setViewName("redirect:/index.jsp");
    String message = "News was successfully created.";
    redirectAttributes.addFlashAttribute("message", message);
    return mav;
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public ModelAndView newsListPage() {
    ModelAndView mav = new ModelAndView("news/news-list");
    List<News> newsList = newsServvice.findAll();
    mav.addObject("newsList", newsList);
    return mav;
  }

  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public ModelAndView editNewsPage(@PathVariable Integer id) {
    ModelAndView mav = new ModelAndView("news/news-list");
    News news = null;
    try {
      news = newsServvice.findById(id);
    } catch (NewsNotFound e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    mav.addObject("news", news);
    return mav;
  }

  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public ModelAndView editNews(@ModelAttribute News news, @PathVariable Integer id,
      final RedirectAttributes redirectAttributes) {
    ModelAndView mav = new ModelAndView("redirect:news/index.jsp");
    String message = "News was successfully updated.";
    try {
      newsServvice.update(news);
    } catch (NewsNotFound e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    redirectAttributes.addFlashAttribute("message", message);
    return mav;
  }

  @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
  public ModelAndView deleteNews(@PathVariable Integer id, final RedirectAttributes redirectAttributes) {
    ModelAndView mav = new ModelAndView("redirect:news/index.jsp");
    try {
      newsServvice.delete(id);
    } catch (NewsNotFound e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    String message = "News was successfully deleted.";
    redirectAttributes.addFlashAttribute("message", message);
    return mav;

  }

  @RequestMapping(value = "/news", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
  public List<News> getNewsListViaJson() {
    List<News> result = listNews();
    return result;
  }

  @RequestMapping(value = "/news", method = RequestMethod.GET, produces = { "text/html" })
  public ModelAndView getNewsList() {
    ModelAndView mav = new ModelAndView("news/news-list");
    List<News> result = listNews();
    mav.addObject("newsList", result);
    return mav;
  }

  private List<News> listNews() {
    List<News> result = new ArrayList<News>();
    result.add(new News(1, "test title 1", "test content 1"));
    result.add(new News(2, "test title 2", "test content 2"));
    result.add(new News(3, "test title 3", "test content 3"));
    return result;
  }
}
