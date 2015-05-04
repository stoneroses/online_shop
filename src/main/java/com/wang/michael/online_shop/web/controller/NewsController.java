package com.wang.michael.online_shop.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    private NewsService newsService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequiresPermissions("news_create")
    public ModelAndView newNewsPage() {
        ModelAndView mav = new ModelAndView("news-new", "news", new News());
        mav.addObject("pageTitle", "Create News");
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    public ModelAndView newsListPage(Model model) {
        ModelAndView mav = new ModelAndView("news-index");
        List<News> newsList = newsService.findAll();
        mav.addObject("newsList", newsList);
        mav.addObject("pageTitle", "News List");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("news_save")
    public ModelAndView saveNews(@Valid News news, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("news-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/news/list");
        newsService.save(news);
        String message = "News was successfully saved.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("news_edit")
    public ModelAndView editNewsPage(@PathVariable Integer id) throws NewsNotFound {
        ModelAndView mav = new ModelAndView("news-edit");
        News news = null;
        news = newsService.findById(Long.valueOf(id));
        mav.addObject("news", news);
        mav.addObject("pageTitle", "Edit News " + news.getTitle());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("news_delete")
    public ModelAndView deleteNews(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws NewsNotFound {
        ModelAndView mav = new ModelAndView("redirect:/news/list");
        newsService.delete(Long.valueOf(id));
        String message = "News was successfully deleted.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewNewsPage(@PathVariable Integer id) throws NewsNotFound {
        ModelAndView mav = new ModelAndView("news-view");
        News news = null;
        news = newsService.findById(Long.valueOf(id));
        mav.addObject("news", news);
        mav.addObject("pageTitle", "View News " + news.getTitle());
        return mav;
    }

}
