package org.nclc.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.nclc.content.Category;
import org.nclc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController extends AbstractMVCController {

    private static final Logger logger = Logger.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = { "/add-category" }, method = RequestMethod.GET)
    public ModelAndView newCategory() throws SQLException {
        Map<String, String> map = getActionBreadcrumbs("Add Category");
        ModelAndView modelAndView = new ModelAndView("forms/category");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("crumbs", map);
        return modelAndView;
    }

    @RequestMapping(value = { "/add-category"}, method = RequestMethod.POST)
    public String saveCategory(@Valid @ModelAttribute("category") final Category category, final BindingResult result, final ModelMap model) throws SQLException{
        if(result.hasErrors()){
            return "category";
        }
        categoryService.createCategory(category);

        model.addAttribute("success", "Category " + category.getTitle() + " registered successfully");
        return "success";
    }

    @RequestMapping(value = { "/add-{id}-category" }, method = RequestMethod.GET)
    public ModelAndView newSubCategory(@PathVariable Integer id) throws SQLException {
        Category category = categoryService.findCategoryById(id);
        Map<String, String> map = getBreadcrumbs(category, "Add Sub-Category");
        ModelAndView modelAndView = new ModelAndView("forms/category");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("crumbs", map);
        return modelAndView;
    }

    @RequestMapping(value = { "/add-{id}-category"}, method = RequestMethod.POST)
    public String saveSubCategory(@Valid @ModelAttribute("category") final Category category, @PathVariable Integer id, final BindingResult result, final ModelMap model) throws SQLException{
        if(result.hasErrors()){
            return "category";
        }
        Category c = categoryService.findCategoryById(id);
        categoryService.createSubCategory(c, category);

        model.addAttribute("success", "Category " + category.getTitle() + " registered successfully");
        return "success";
    }

    @RequestMapping(value = "/category-list", method = RequestMethod.GET)
    public String listCategories(ModelMap model) throws SQLException {
        Map<String, String> map = getActionBreadcrumbs("Categories and Collections");

        List<Category> tops = categoryService.findAllTop();

        model.addAttribute("categories", tops);
        model.addAttribute("crumbs", map);
        return "forms/category-list";
    }

    @RequestMapping(value = "/show-category-list", method = RequestMethod.GET)
    public String showCategories(ModelMap model) throws SQLException {
        Map<String, String> map = getActionBreadcrumbs("Categories and Collections");

        List<Category> tops = categoryService.findAllTop();

        model.addAttribute("categories", tops);
        model.addAttribute("crumbs", map);
        return "show/category-list";
    }

    @RequestMapping(value = { "/edit-{id}-category" }, method = RequestMethod.GET)
    public ModelAndView editCategory(@PathVariable Integer id) throws SQLException {
        Category category = categoryService.findCategoryById(id);
        ModelAndView modelAndView = new ModelAndView("forms/category");
        modelAndView.addObject("category", category);
        Map<String, String> map = getBreadcrumbs(category, "Edit Category");
        modelAndView.addObject("crumbs", map);
        modelAndView.addObject("edit", true);
        return modelAndView;
    }

    @RequestMapping(value = { "/edit-{id}-category" }, method = RequestMethod.POST)
    public String updateCategory(@Valid Category category, BindingResult result,
            ModelMap model, @PathVariable int id) throws SQLException {
 
        if (result.hasErrors()) {
            return "category";
        }

        categoryService.updateCategory(category);

        model.addAttribute("success", "Category " + category.getTitle()  + " updated successfully");
        return "success";
    }

    @RequestMapping(value = { "/show-{id}-category" }, method = RequestMethod.GET)
    public ModelAndView showCategory(@PathVariable Integer id) throws SQLException {
        Category category = categoryService.findCategoryById(id);
        ModelAndView modelAndView = new ModelAndView("forms/category");
        modelAndView.addObject("category", category);
        Map<String, String> map = getBreadcrumbs(category, category.getTitle());
        modelAndView.addObject("crumbs", map);
        modelAndView.addObject("edit", true);
        return modelAndView;
    }
}