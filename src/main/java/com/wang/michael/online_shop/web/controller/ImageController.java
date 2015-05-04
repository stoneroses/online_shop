package com.wang.michael.online_shop.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wang.michael.online_shop.exception.ImageNotFound;
import com.wang.michael.online_shop.model.Image;
import com.wang.michael.online_shop.service.ImageService;

@RestController
@RequestMapping(value = "/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @ModelAttribute("pageTitle")
    public String defaultPageTitle() {
        return "Images";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequiresPermissions("image_create")
    public ModelAndView newImagePage() throws Exception {
        ModelAndView mav = new ModelAndView("image-new", "image", new Image());
        mav.addObject("pageTitle", "Create Image");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("image_save")
    public ModelAndView saveImage(@Valid Image image, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("image-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/images/list");
        imageService.save(image);
        String message = "Image was successfully saved.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    @RequiresPermissions("image_list")
    public ModelAndView imageListPage(Model model) {
        ModelAndView mav = new ModelAndView("image-index");
        List<Image> imageList = imageService.findAll();
        mav.addObject("imageList", imageList);
        mav.addObject("pageTitle", "Image List");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("image_edit")
    public ModelAndView editImagePage(@PathVariable Integer id) throws ImageNotFound {
        ModelAndView mav = new ModelAndView("image-edit");
        Image image = null;
        image = imageService.findById(Long.valueOf(id));
        mav.addObject("image", image);
        mav.addObject("pageTitle", "Edit Image " + image.getName());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("image_delete")
    public ModelAndView deleteImage(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws ImageNotFound {
        ModelAndView mav = new ModelAndView("redirect:/images/list");
        imageService.delete(Long.valueOf(id));
        String message = "Image was successfully deleted.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewImagePage(@PathVariable Integer id) throws ImageNotFound {
        ModelAndView mav = new ModelAndView("image-view");
        Image image = null;
        image = imageService.findById(Long.valueOf(id));
        mav.addObject("image", image);
        mav.addObject("pageTitle", "View Image " + image.getName());
        return mav;
    }

}
