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

import com.wang.michael.online_shop.exception.LinkNotFound;
import com.wang.michael.online_shop.model.Link;
import com.wang.michael.online_shop.service.LinkService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = "/admin/links")
public class LinkController extends BaseController {

    @Autowired
    private LinkService linkService;

    @ModelAttribute("pageTitle")
    public String defaultPageTitle() {
        return "Links";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequiresPermissions("link_create")
    public ModelAndView newLinkPage() throws Exception {
        ModelAndView mav = new ModelAndView("link-new", "link", new Link());
        mav.addObject("pageTitle", "link.page.title.create");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("link_save")
    public ModelAndView saveLink(@Valid Link link, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("link-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/admin/links/list");
        linkService.save(link);
        redirectAttributes.addFlashAttribute("message", "link.successfully.saved");
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    @RequiresPermissions("link_list")
    public ModelAndView linkListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("link-index");
        Page<Link> linkPage = linkService.getLinks(page - 1, size);
        mav.addObject("linkPage", linkPage);
        preparePaginationData(mav, "linkPage", linkPage, page, 10);
        mav.addObject("pageTitle", "link.page.title.list");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("link_edit")
    public ModelAndView editLinkPage(@PathVariable Integer id) throws LinkNotFound {
        ModelAndView mav = new ModelAndView("link-edit");
        Link link = null;
        link = linkService.findById(Long.valueOf(id));
        mav.addObject("link", link);
        mav.addObject("pageTitle", "link.page.title.edit");
        mav.addObject("pageTitleArg", link.getName());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("link_delete")
    public ModelAndView deleteLink(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws LinkNotFound {
        ModelAndView mav = new ModelAndView("redirect:/admin/links/list");
        linkService.delete(Long.valueOf(id));
        redirectAttributes.addFlashAttribute("message", "link.successfully.deleted");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewLinkPage(@PathVariable Integer id) throws LinkNotFound {
        ModelAndView mav = new ModelAndView("link-view");
        Link link = null;
        link = linkService.findById(Long.valueOf(id));
        mav.addObject("link", link);
        mav.addObject("pageTitle", "link.page.title.view");
        mav.addObject("pageTitleArg", link.getName());
        return mav;
    }

}
