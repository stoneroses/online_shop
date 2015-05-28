package com.wang.michael.online_shop.web.controller.admin;

import java.util.List;

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

import com.wang.michael.online_shop.exception.EmailJobNotFound;
import com.wang.michael.online_shop.model.CustomerGroup;
import com.wang.michael.online_shop.model.EmailJob;
import com.wang.michael.online_shop.service.CustomerGroupService;
import com.wang.michael.online_shop.service.EmailJobService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = "/admin/email_jobs")
public class EmailJobController extends BaseController {

    @Autowired
    private EmailJobService emailJobService;

    @Autowired
    private CustomerGroupService customerGroupService;

    @ModelAttribute("allCustomerGroups")
    public List<CustomerGroup> getAllPermissions() {
        return customerGroupService.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView newEmailJobPage() throws Exception {
        EmailJob emailJob = new EmailJob();
        ModelAndView mav = new ModelAndView("email-job-new", "emailJob", emailJob);
        mav.addObject("pageTitle", "email.job.page.title.create");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("email_job_save")
    public ModelAndView saveEmailJob(@Valid EmailJob emailJob, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("email-job-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/admin/email_jobs/list");
        emailJobService.save(emailJob);
        redirectAttributes.addFlashAttribute("message", "email.job.successfully.saved");
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    @RequiresPermissions("email_job_list")
    public ModelAndView emailJobListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("email-job-index");
        Page<EmailJob> emailJobPage = emailJobService.getEmailJobs(page - 1, size);
        mav.addObject("emailJobPage", emailJobPage);
        preparePaginationData(mav, "emailJobPage", emailJobPage, page, 10);
        mav.addObject("pageTitle", "email.job.page.title.list");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("email_job_edit")
    public ModelAndView editEmailJobPage(@PathVariable Integer id) throws EmailJobNotFound {
        ModelAndView mav = new ModelAndView("email-job-edit");
        EmailJob emailJob = emailJobService.findById(Long.valueOf(id));
        mav.addObject("emailJob", emailJob);
        mav.addObject("pageTitle", "email.job.page.title.edit");
        mav.addObject("pageTitleArg", emailJob.getTitle());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("email_job_delete")
    public ModelAndView deleteEmailJob(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws EmailJobNotFound {
        ModelAndView mav = new ModelAndView("redirect:/admin/email_jobs/list");
        emailJobService.delete(Long.valueOf(id));
        redirectAttributes.addFlashAttribute("message", "email.job.successfully.deleted");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewEmailJobPage(@PathVariable Integer id) throws EmailJobNotFound {
        ModelAndView mav = new ModelAndView("email-job-view");
        EmailJob emailJob = emailJobService.findById(Long.valueOf(id));
        mav.addObject("emailJob", emailJob);
        mav.addObject("pageTitle", "email.job.page.title.view");
        mav.addObject("pageTitleArg", emailJob.getTitle());
        return mav;
    }
}
