package com.wang.michael.online_shop.web.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wang.michael.online_shop.exception.ImageNotFound;
import com.wang.michael.online_shop.exception.ImageUploadException;
import com.wang.michael.online_shop.model.Image;
import com.wang.michael.online_shop.service.ImageService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = "/admin/images")
public class ImageController extends BaseController {

    @Autowired
    private ImageService imageService;

    @ModelAttribute("currentNavButton")
    public String getCurrentNavButton() {
        return "equipments";
    }

    @Value("${file.upload.root}")
    private String fileUploadRoot;

    @ModelAttribute("pageTitle")
    public String defaultPageTitle() {
        return "Images";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequiresPermissions("image_create")
    public ModelAndView newImagePage() throws Exception {
        ModelAndView mav = new ModelAndView("image-new", "image", new Image());
        mav.addObject("pageTitle", "image.page.title.create");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("image_save")
    public ModelAndView saveImage(@Valid Image image, BindingResult bindingResult,
            @RequestParam(value = "file", required = false) MultipartFile file, final RedirectAttributes redirectAttributes)
            throws ImageUploadException {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("image-edit");
        }
        String storedFileName = null;
        if (file != null) {
            storedFileName = generateStoredFileName(file.getOriginalFilename());
            String realFilePath = generateRealFilePath(storedFileName);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    File fileObject = new File(realFilePath);
                    fileObject.getParentFile().mkdirs();
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileObject));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    throw new ImageUploadException("You failed to upload " + storedFileName + " => " + e.getMessage(), e);
                }
            } else {
                return new ModelAndView("image-edit", "warningMessage", "image.failed.upload.empty");
            }
        }
        ModelAndView mav = new ModelAndView("redirect:/admin/images/list");
        redirectAttributes.addFlashAttribute("message", "image.successfully.uploaded");
        redirectAttributes.addFlashAttribute("messageArg", storedFileName);
        image.setLocation(storedFileName);
        imageService.save(image);
        return mav;
    }

    private String generateRealFilePath(String storedFileName) {
        return fileUploadRoot + storedFileName;
    }

    private String generateStoredFileName(String fileName) {
        DateTime currentDateTime = new DateTime();
        StringBuffer result = new StringBuffer();
        result.append("/");
        result.append(currentDateTime.getYear());
        result.append("/");
        result.append(currentDateTime.getMonthOfYear());
        result.append("/");
        result.append(currentDateTime.getDayOfMonth());
        result.append("/");
        result.append(currentDateTime.getHourOfDay());
        result.append("/");
        result.append(currentDateTime.getMinuteOfHour());
        result.append("/");
        result.append(currentDateTime.getSecondOfMinute());
        result.append("_");
        result.append(currentDateTime.getMillisOfSecond());
        result.append("_");
        result.append(fileName);
        return result.toString();
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    @RequiresPermissions("image_list")
    public ModelAndView imageListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("image-index");
        Page<Image> imagePage = imageService.getImages(page - 1, size);
        mav.addObject("imagePage", imagePage);
        preparePaginationData(mav, "imagePage", imagePage, page, 10);
        mav.addObject("pageTitle", "image.page.title.list");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("image_edit")
    public ModelAndView editImagePage(@PathVariable Integer id) throws ImageNotFound {
        ModelAndView mav = new ModelAndView("image-edit");
        Image image = null;
        image = imageService.findById(Long.valueOf(id));
        mav.addObject("image", image);
        mav.addObject("pageTitle", "image.page.title.edit");
        mav.addObject("pageTitleArg", image.getName());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("image_delete")
    public ModelAndView deleteImage(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws Exception {
        ModelAndView mav = new ModelAndView("redirect:/admin/images/list");
        imageService.delete(Long.valueOf(id));
        redirectAttributes.addFlashAttribute("message", "image.successfully.deleted");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewImagePage(@PathVariable Integer id) throws ImageNotFound {
        ModelAndView mav = new ModelAndView("image-view");
        Image image = null;
        image = imageService.findById(Long.valueOf(id));
        mav.addObject("image", image);
        mav.addObject("pageTitle", "image.page.title.view");
        mav.addObject("pageTitleArg", image.getName());
        return mav;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @RequiresPermissions("image_list")
    public @ResponseBody List<Image> searchByName(@RequestParam("term") String term) {
        return imageService.findByName(term);
    }

}
