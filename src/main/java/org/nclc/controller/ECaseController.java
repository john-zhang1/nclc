package org.nclc.controller;

import java.sql.SQLException;
import java.util.Map;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.nclc.content.Category;
import org.nclc.content.Collection;
import org.nclc.content.ECase;
import org.nclc.content.EPerson;
import org.nclc.content.Sample;
import org.nclc.content.data.ECaseData;
import org.nclc.service.CategoryService;
import org.nclc.service.CollectionService;
import org.nclc.service.ECaseService;
import org.nclc.service.EPersonService;
import org.nclc.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ECaseController extends AbstractMVCController {

    private static final Logger logger = Logger.getLogger(ECaseController.class);

    @Autowired
    private ECaseService eCaseService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SampleService sampleService;
    @Autowired
    private EPersonService ePersonService;
    @Autowired
    private CollectionService collectionService;



    @RequestMapping(value = { "/add-ecase" }, method = RequestMethod.GET)
    public ModelAndView newECase() throws SQLException {
        Map<String, String> map = getActionBreadcrumbs("Add ECase");
        ModelAndView modelAndView = new ModelAndView("forms/ecase");
        modelAndView.addObject("sample", new Sample());
        modelAndView.addObject("crumbs", map);
        return modelAndView;
    }


    @RequestMapping(value = { "/add-{categoryId}-{collectionId}-ecase" }, method = RequestMethod.GET)
    public ModelAndView addECase(@PathVariable Integer categoryId, @PathVariable Integer collectionId) throws SQLException {
        Category category = categoryService.findCategoryById(categoryId);
        Collection collection = collectionService.findCollectionById(collectionId);
        Map<String, String> map = getBreadcrumbs(category, collection, "Add ECase");

        ModelAndView modelAndView = new ModelAndView("forms/ecase");
        modelAndView.addObject("eCaseData", new ECaseData());
        modelAndView.addObject("crumbs", map);
        return modelAndView;
    }

    @RequestMapping(value = { "/add-{categoryId}-{collectionId}-ecase"}, method = RequestMethod.POST)
    public ModelAndView saveECase(@Valid @ModelAttribute("eCaseData") final ECaseData eCaseData, @PathVariable Integer categoryId, @PathVariable Integer collectionId, final BindingResult result) throws SQLException{
//
        Category category = categoryService.findCategoryById(categoryId);
        Collection collection = collectionService.findCollectionById(collectionId);
        Map<String, String> map = getBreadcrumbs(category, collection, "Add ECase");
        ModelAndView modelAndView = new ModelAndView("forms/ecase");
        modelAndView.addObject("eCaseData", new ECaseData());
        modelAndView.addObject("crumbs", map);

        if(result.hasErrors()){
            return new ModelAndView("redirect:/add-"+categoryId+"-"+collectionId+"-ecase");
        }
        Sample sample = new Sample(eCaseData.getText(), eCaseData.getErrorText());
        sampleService.createSample(sample);
        EPerson ePerson = ePersonService.findByEmail(eCaseData.getEmail());
        ECase eCase = new ECase();
        eCase.setSample(sample);
        eCase.setEPerson(ePerson);
        ECase e = eCaseService.createECase(eCase, collection);
        
        String message = null;
        int registed = -1;
        if(e != null){
             message = "Case was added successfully";
             modelAndView.addObject("edit", true);
             modelAndView.addObject("registed", 1);
        }else{
            modelAndView.addObject("registed", 0);
        }
        return new ModelAndView("redirect:/add-"+categoryId+"-"+collectionId+"-ecase");
    }
}
