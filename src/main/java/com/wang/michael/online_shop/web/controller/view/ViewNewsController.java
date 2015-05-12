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

import com.wang.michael.online_shop.exception.NewsNotFound;
import com.wang.michael.online_shop.model.News;
import com.wang.michael.online_shop.service.NewsService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = "/news")
public class ViewNewsController extends BaseController {

    @Autowired
    private NewsService newsService;

    @ModelAttribute("currentNavButton")
    public String getCurrentNavButton() {
        return "news";
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    public ModelAndView newsListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("list-news");
        Page<News> newsPage = newsService.getNews(page - 1, size);
        mav.addObject("newsPage", newsPage);
        preparePaginationData(mav, "newsPage", newsPage, page, 10);
        mav.addObject("pageTitle", "News");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewNewsPage(@PathVariable Integer id) throws NewsNotFound {
        ModelAndView mav = new ModelAndView("view-news");
        News news = null;
        news = newsService.findById(Long.valueOf(id));
        mav.addObject("news", news);
        mav.addObject("pageTitle", news.getTitle());
        return mav;
    }

}
