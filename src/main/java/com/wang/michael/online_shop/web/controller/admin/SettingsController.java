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

import com.wang.michael.online_shop.exception.SettingNotFound;
import com.wang.michael.online_shop.model.Setting;
import com.wang.michael.online_shop.service.SettingService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = "/admin/settings")
public class SettingsController extends BaseController {

    @Autowired
    private SettingService settingService;

    @ModelAttribute("pageTitle")
    public String defaultPageTitle() {
        return "Settings";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequiresPermissions("setting_create")
    public ModelAndView newSettingPage() throws Exception {
        ModelAndView mav = new ModelAndView("setting-new", "setting", new Setting());
        mav.addObject("pageTitle", "setting.page.title.create");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("setting_save")
    public ModelAndView saveSetting(@Valid Setting setting, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("setting-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/admin/settings/list");
        settingService.save(setting);
        redirectAttributes.addFlashAttribute("message", "setting.successfully.saved");
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    @RequiresPermissions("setting_list")
    public ModelAndView settingListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("setting-index");
        Page<Setting> settingPage = settingService.getSettings(page - 1, size);
        mav.addObject("settingPage", settingPage);
        preparePaginationData(mav, "settingPage", settingPage, page, 10);
        mav.addObject("pageTitle", "setting.page.title.list");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("setting_edit")
    public ModelAndView editSettingPage(@PathVariable Integer id) throws SettingNotFound {
        ModelAndView mav = new ModelAndView("setting-edit");
        Setting setting = null;
        setting = settingService.findById(Long.valueOf(id));
        mav.addObject("setting", setting);
        mav.addObject("pageTitle", "setting.page.title.edit");
        mav.addObject("pageTitleArg", setting.getKey());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("setting_delete")
    public ModelAndView deleteSetting(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws SettingNotFound {
        ModelAndView mav = new ModelAndView("redirect:/admin/settings/list");
        settingService.delete(Long.valueOf(id));
        redirectAttributes.addFlashAttribute("message", "setting.successfully.deleted");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewSettingPage(@PathVariable Integer id) throws SettingNotFound {
        ModelAndView mav = new ModelAndView("setting-view");
        Setting setting = null;
        setting = settingService.findById(Long.valueOf(id));
        mav.addObject("setting", setting);
        mav.addObject("pageTitle", "setting.page.title.view");
        mav.addObject("pageTitleArg", setting.getKey());
        return mav;
    }

}
