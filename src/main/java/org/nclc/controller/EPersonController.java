package org.nclc.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.nclc.content.EPerson;
import org.nclc.content.data.EducationalStage;
import org.nclc.content.data.Gender;
import org.nclc.content.data.LanguageCode;
import org.nclc.service.EPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EPersonController extends AbstractMVCController {

    private static final Logger logger = Logger.getLogger(EPersonController.class);

    @Autowired
    private EPersonService ePersonService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/person-list", method = RequestMethod.GET)
    public String listEPersons(ModelMap model) throws SQLException {

        List<EPerson> persons = ePersonService.findAllEPersons();
        model.addAttribute("persons", persons);
        return "persons";
    }

    @RequestMapping(value = { "/add-person" }, method = RequestMethod.GET)
    public ModelAndView newEPerson(ModelAndView model) throws SQLException {
        List<Gender> genderList = new ArrayList<>( Arrays.asList(Gender.values() ));
        List<LanguageCode> languageList = new ArrayList<>( Arrays.asList(LanguageCode.values() ));
        List<EducationalStage> stageList = new ArrayList<>( Arrays.asList(EducationalStage.values() ));
        Map<String, String> map = getActionBreadcrumbs("Add Person");

        ModelAndView modelAndView = new ModelAndView("forms/person");
        modelAndView.addObject("genderList", genderList);
        modelAndView.addObject("languageList", languageList);
        modelAndView.addObject("stageList", stageList);
        modelAndView.addObject("eperson", new EPerson());
        modelAndView.addObject("crumbs", map);
        return modelAndView;
    }

    @RequestMapping(value = { "/add-person"}, method = RequestMethod.POST)
    public ModelAndView saveEPerson(@Valid @ModelAttribute("person") final EPerson person, final BindingResult result, final ModelMap model) throws SQLException{

        List<Gender> genderList = new ArrayList<>( Arrays.asList(Gender.values() ));
        List<LanguageCode> languageList = new ArrayList<>( Arrays.asList(LanguageCode.values() ));
        List<EducationalStage> stageList = new ArrayList<>( Arrays.asList(EducationalStage.values() ));
        Map<String, String> map = getActionBreadcrumbs("Add Person");
        ModelAndView modelAndView = new ModelAndView("forms/person");
        modelAndView.addObject("genderList", genderList);
        modelAndView.addObject("languageList", languageList);
        modelAndView.addObject("stageList", stageList);
        modelAndView.addObject("crumbs", map);

        if(result.hasErrors()){
            return new ModelAndView("redirect:/add-person");
        }

        Integer personId = person.getId();
        EPerson registedEPerson = ePersonService.createEPerson(person);
        String message = null;
        int registed = -1;

        if(personId == null && registedEPerson.getId() != null){
            message = person.getEmail() + " was registered successfully";
            model.addAttribute("edit", true);
            registed = 1;
        }else if(personId == registedEPerson.getId()){
            ePersonService.updateEPerson(person);
            message = person.getEmail() + " was updated";
            model.addAttribute("edit", true);
            registed = 1;
        } else{
            message = person.getEmail() + " was already used";
            registed = 0;
        }
        modelAndView.addObject("eperson", person);
        modelAndView.addObject("message", message);
        modelAndView.addObject("registed", registed);
        return modelAndView;
    }

    @RequestMapping(value = { "/edit-{id}-person" }, method = RequestMethod.GET)
    public String editEPerson(@PathVariable Integer id, ModelMap model) throws SQLException {
        EPerson person = ePersonService.findById(id);
        model.addAttribute("person", person);
        model.addAttribute("edit", true);
        return "person";
    }

    @RequestMapping(value = { "/edit-{id}-person" }, method = RequestMethod.POST)
    public String updateEPerson(@Valid EPerson person, BindingResult result,
            ModelMap model, @PathVariable int id) throws SQLException {
 
        if (result.hasErrors()) {
            return "person";
        }

        ePersonService.updateEPerson(person);

        model.addAttribute("success", "EPerson " + person.getFullName()  + " updated successfully");
        return "success";
    }

    @RequestMapping(value = { "/delete-{id}-person" }, method = RequestMethod.GET)
    public String deleteEPerson(@PathVariable EPerson person) throws SQLException {
        ePersonService.deleteEPerson(person);
        return "redirect:/personlist";
    }

    @RequestMapping(value = { "/show-${person.id}-person" }, method = RequestMethod.GET)
    public String showEPerson(@PathVariable Integer id, ModelMap model) throws SQLException {
        EPerson person = ePersonService.findById(id);
        model.addAttribute("person", person);
        model.addAttribute("edit", true);
        return "person";
    }

    @RequestMapping(value = { "/searcheperson" }, method = RequestMethod.GET)
    public List<String> searchEPerson(@RequestParam String email) throws SQLException {
        return ePersonService.search(email);
    }

    @RequestMapping(value = "search-eperson", method = RequestMethod.GET)
    @ResponseBody
    public List<String> search(HttpServletRequest request) throws SQLException {
            return ePersonService.search(request.getParameter("term"));
    }
}
