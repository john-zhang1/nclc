package org.nclc.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.nclc.content.Sample;
import org.nclc.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SampleController extends AbstractMVCController {

    private static final Logger logger = Logger.getLogger(SampleController.class);

    @Autowired
    private SampleService sampleService;

    @RequestMapping(value = { "/add-sample" }, method = RequestMethod.GET)
    public ModelAndView newSample() throws SQLException {
        Map<String, String> map = getActionBreadcrumbs("Add Sample");
        ModelAndView modelAndView = new ModelAndView("forms/sample");
        modelAndView.addObject("sample", new Sample());
        modelAndView.addObject("crumbs", map);
        return modelAndView;
    }

    @RequestMapping(value = { "/add-sample"}, method = RequestMethod.POST)
    public String saveSample(@Valid @ModelAttribute("sample") final Sample sample, final BindingResult result, final ModelMap model) throws SQLException{
        if(result.hasErrors()){
            return "forms/sample";
        }
        sampleService.createSample(sample);

        model.addAttribute("success", "Sample " + sample.getText() + " registered successfully");
        return "success";
    }

    @RequestMapping(value = "/sample-list", method = RequestMethod.GET)
    public String listSamples(ModelMap model) throws SQLException {
        Map<String, String> map = getActionBreadcrumbs("Samples");
        List<Sample> samples = sampleService.findAllSamples();

        model.addAttribute("samples", samples);
        model.addAttribute("crumbs", map);
        return "sample-list";
    }

}
