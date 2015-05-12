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

import com.wang.michael.online_shop.exception.ContactUsMessageNotFound;
import com.wang.michael.online_shop.model.ContactUsMessage;
import com.wang.michael.online_shop.service.ContactUsMessageService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = "/admin/contact_us_messages")
public class ContactUsMessageController extends BaseController {

    @Autowired
    private ContactUsMessageService contactUsMessageService;

    @ModelAttribute("pageTitle")
    public String defaultPageTitle() {
        return "ContactUsMessages";
    }

    @ModelAttribute("currentNavButton")
    public String getCurrentNavButton() {
        return "contact_us";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequiresPermissions("contact_us_message_create")
    public ModelAndView newContactUsMessagePage() throws Exception {
        ModelAndView mav = new ModelAndView("contact_us_message-new", "contactUsMessage", new ContactUsMessage());
        mav.addObject("pageTitle", "Create ContactUsMessage");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    // @RequiresPermissions("contact_us_message_save")
    public ModelAndView saveContactUsMessage(@Valid ContactUsMessage contactUsMessage, BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("contact_us_message-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/admin/contact_us_messages/list");
        contactUsMessageService.save(contactUsMessage);
        redirectAttributes.addFlashAttribute("message", "contact.us.message.successfully.saved");
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    @RequiresPermissions("contact_us_message_list")
    public ModelAndView contactUsMessageListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("contact_us_message-index");
        Page<ContactUsMessage> contactUsMessagePage = contactUsMessageService.getContactUsMessagePages(page - 1, size);
        preparePaginationData(mav, "contactUsMessagePage", contactUsMessagePage, page, 10);
        mav.addObject("pageTitle", "ContactUsMessage List");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("contact_us_message_edit")
    public ModelAndView editContactUsMessagePage(@PathVariable Integer id) throws ContactUsMessageNotFound {
        ModelAndView mav = new ModelAndView("contact_us_message-edit");
        ContactUsMessage contactUsMessage = null;
        contactUsMessage = contactUsMessageService.findById(Long.valueOf(id));
        mav.addObject("contactUsMessage", contactUsMessage);
        mav.addObject("pageTitle", "Edit ContactUsMessage " + contactUsMessage.getSubject());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("contact_us_message_delete")
    public ModelAndView deleteContactUsMessage(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws ContactUsMessageNotFound {
        ModelAndView mav = new ModelAndView("redirect:/admin/contact_us_messages/list");
        contactUsMessageService.delete(Long.valueOf(id));
        redirectAttributes.addFlashAttribute("message", "contact.us.message.successfully.deleted");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @RequiresPermissions("contact_us_message_view")
    public ModelAndView viewContactUsMessagePage(@PathVariable Integer id) throws ContactUsMessageNotFound {
        ModelAndView mav = new ModelAndView("contact_us_message-view");
        ContactUsMessage contactUsMessage = null;
        contactUsMessage = contactUsMessageService.findById(Long.valueOf(id));
        mav.addObject("contactUsMessage", contactUsMessage);
        mav.addObject("pageTitle", "View ContactUsMessage " + contactUsMessage.getSubject());
        return mav;
    }

}
