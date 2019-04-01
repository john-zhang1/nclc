package org.nclc.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import org.nclc.content.TFile;
import org.nclc.content.data.TFileData;
import org.nclc.service.TFileService;
import org.nclc.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TFileController extends AbstractMVCController {

    @Autowired
    private ServletContext context;
    @Autowired
    private TFileService tFileService;

    @RequestMapping(value = "/add-file", method = RequestMethod.GET)
        public ModelAndView fileUploadPage() throws SQLException {
        Map<String, String> map = getActionBreadcrumbs("Upload File");
        ModelAndView modelAndView = new ModelAndView("forms/tfile");
        modelAndView.addObject("crumbs", map);
        modelAndView.addObject("tFileData", new TFileData());

        return modelAndView;
    }

    @RequestMapping(value="/add-file", method = RequestMethod.POST)
    public ModelAndView fileUpload(@Valid @ModelAttribute("tFileData") final TFileData tFileData) throws IOException, SQLException {
        MultipartFile file = tFileData.getFile();
        String uploadPath = context.getRealPath("") + File.separator + "upload" + File.separator;
        FileCopyUtils.copy(file.getBytes(), new File(uploadPath+file.getOriginalFilename()));

        File f = new File( file.getOriginalFilename());
        file.transferTo(f);
        Map<String, Integer> counts = CommonUtils.count(f);
        TFile tFile = new TFile(file.getSize(), null, "md5", tFileData.getDescription(), counts.get("word"), counts.get("character"));
        tFileService.create(tFile);

        Map<String, String> map = getActionBreadcrumbs("File");
        ModelAndView modelAndView = new ModelAndView("show/tfile");
        modelAndView.addObject("crumbs", map);
        modelAndView.addObject("tFileData", tFileData);
        return modelAndView;
    }}
