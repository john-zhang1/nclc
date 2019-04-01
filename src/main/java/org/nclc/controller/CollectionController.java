package org.nclc.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.nclc.content.Category;
import org.nclc.content.Collection;
import org.nclc.service.CategoryService;
import org.nclc.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CollectionController extends AbstractMVCController {

    private static final Logger logger = Logger.getLogger(CollectionController.class);

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CollectionService collectionService;

    @RequestMapping(value = { "/add-{id}-collection" }, method = RequestMethod.GET)
    public ModelAndView newCollection(@PathVariable Integer id) throws SQLException {
        Category c = categoryService.findCategoryById(id);
        Map<String, String> map = getBreadcrumbs(c, "Add Collection");
        ModelAndView modelAndView = new ModelAndView("forms/collection");
        modelAndView.addObject("collection", new Collection());
        modelAndView.addObject("crumbs", map);
        return modelAndView;
    }

    @RequestMapping(value = { "/add-{id}-collection"}, method = RequestMethod.POST)
    public String saveCollection(@Valid @ModelAttribute("collection") final Collection collection, @PathVariable Integer id, final BindingResult result, final ModelMap model) throws SQLException{
        if(result.hasErrors()){
            return "collection";
        }
        Category category = categoryService.findCategoryById(id);
        collectionService.createCollection(collection, category);

        model.addAttribute("success", "Collection " + collection.getTitle() + " registered successfully");
        return "success";
    }

    @RequestMapping(value = "/collection-list", method = RequestMethod.GET)
    public String listCollections(ModelMap model) throws SQLException {

        List<Collection> collections = collectionService.findAllCollections();
        model.addAttribute("collections", collections);
        return "collections";
    }

    @RequestMapping(value = { "/show-{categoryId}-{collectionId}-collection" }, method = RequestMethod.GET)
    public ModelAndView showCollection(@PathVariable Integer categoryId, @PathVariable Integer collectionId) throws SQLException {
        Collection collection = collectionService.findCollectionById(collectionId);
        Category category = categoryService.findCategoryById(categoryId);
        ModelAndView modelAndView = new ModelAndView("show/collection");
        modelAndView.addObject("collection", collection);
        Map<String, String> map = getBreadcrumbs(category, collection, collection.getTitle());
        modelAndView.addObject("crumbs", map);
        return modelAndView;
    }

}