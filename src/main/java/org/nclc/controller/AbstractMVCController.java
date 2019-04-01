package org.nclc.controller;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.nclc.content.Category;
import org.nclc.content.Collection;
import org.nclc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@ComponentScan("org.nclc")
public abstract class AbstractMVCController {

    private final static String HOME = "Home";

    @Autowired
    private CategoryService categoryService;

    protected Map<String, String> getBreadcrumbs() throws SQLException {
        return getActionBreadcrumbs(null);
    }

    protected Map<String, String> getActionBreadcrumbs(String action) throws SQLException {
        return getBreadcrumbs(null, action);
    }

    protected Map<String, String> getCategoryBreadcrumbs(Category category) throws SQLException {
        return getBreadcrumbs(category, null);
    }

    protected Map<String, String> getBreadcrumbs(Category category, String action) throws SQLException {
        Map<String, String> categoryChain = new LinkedHashMap<>();
        categoryChain.put("index", HOME);
        if(category != null){
            categoryChain.putAll(categoryService.getCategoryPath(category));
            if(action != null && category.getTitle().equals(action)){
                action = null;
            }
        }
        if(action != null){
            categoryChain.put(StringUtils.EMPTY, action);
        }
        return categoryChain;
    }

    protected Map<String, String> getBreadcrumbs(Category category, Collection collection, String action) throws SQLException {
        Map<String, String> chain = new LinkedHashMap<>();
        chain.put("index", HOME);
        if(category != null){
            chain.putAll(categoryService.getCategoryPath(category));
        }
        if(collection != null){
            chain.put("show-" + collection.getId() + "-collection", collection.getTitle());            
            if(action != null && collection.getTitle().equals(action)){
                action = null;
            }
        }
        if(action != null){
            chain.put(StringUtils.EMPTY, action);
        }
        return chain;
    }

//
//    private static List<String> getBreadcrumbs(String ... crumb) {
//        List<String> crumbs = new ArrayList<>();
//        crumbs.add(HOME);
//        for(String c : crumb){
//            crumbs.add(c);
//        }
//        return crumbs;
//    }
//    private static List<String> getBreadcrumbs_copy(String ... crumb) {
//        List<String> crumbs = new ArrayList<>();
//        crumbs.add(HOME);
//        for(String c : crumb){
//            crumbs.add(c);
//        }
//        return crumbs;
//    }

//    private static Map<String, String> getBreadcrumbs_adv(String ... crumb) {
//        Map<String, String> crumbs = new HashMap<>();
//        crumbs.put("/", HOME);
//        
//
//        List<String> crumbs1 = new ArrayList<>();
//
//        for(String c : crumb){
//            crumbs.add(c);
//        }
//        return crumbs;
//    }
//
//    protected List<String> getBreadcrumbs_copy(Category category, String action) throws SQLException {
//        List<String> list = new ArrayList<>();
//        if(category != null){
//            List<Category> lc = categoryService.getAllParents(category);
//            Collections.reverse(lc);
//            lc.forEach((cat) -> {
//                list.add(cat.getTitle());
//            });
//            list.add(category.getTitle());
//        }
//        if(!action.isEmpty()){
//            list.add(action);            
//        }
//        String[] arr = list.toArray(new String[list.size()]);
//        return getBreadcrumbs(arr);
//    }
}
