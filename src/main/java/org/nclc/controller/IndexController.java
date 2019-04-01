package org.nclc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.commons.compress.utils.IOUtils;
import org.nclc.content.Category;
import org.nclc.content.Collection;
import org.nclc.content.ECase;
import org.nclc.content.EPerson;
import org.nclc.content.data.EducationalStage;
import org.nclc.content.data.Gender;
import org.nclc.content.Sample;
import org.nclc.content.TFile;
import org.nclc.content.data.LanguageCode;
import org.nclc.service.CategoryService;
import org.nclc.service.CollectionService;
import org.nclc.service.ECaseService;
import org.nclc.service.EPersonService;
import org.nclc.service.SampleService;
import org.nclc.service.TFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController extends AbstractMVCController {

    @Autowired(required = true)
    ServletContext servletContext;

    @Autowired(required = true)
    private CategoryService catService;
    @Autowired(required = true)
    private CollectionService colService;
    @Autowired(required = true)
    private EPersonService ePersonService;
    @Autowired(required = true)
    private SampleService sampleService;
    @Autowired(required = true)
    private TFileService tFileService;
    @Autowired(required = true)
    private ECaseService eCaseService ;
   
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public ModelAndView homePage() throws SQLException {
        Map<String, String> map = getBreadcrumbs();
        return new ModelAndView("index", "crumbs", map);
    }

    @RequestMapping(value = { "/auto" }, method = RequestMethod.GET)
    public ModelAndView autoIngestionPage() throws SQLException, IOException {
        Map<String, String> map = getBreadcrumbs();

        // Category 1
        Category c1 = new Category();
        c1.setTitle("Substitution");
        catService.createCategory(c1);

        Category c1c = new Category();
        c1c.setTitle("Substitution in Chinese");
        Category c2c = new Category();
        c2c.setTitle("Substitution in English");
        catService.createSubCategory(c1, c1c);
        catService.createSubCategory(c1, c2c);

        Collection cl1 = new Collection();
        cl1.setTitle("Visual");
        Collection cl2 = new Collection();
        cl2.setTitle("Meaning");
        Collection cl3 = new Collection();
        cl3.setTitle("Homograph");
        colService.createCollection(cl1, c1c);
        colService.createCollection(cl2, c1c);
        colService.createCollection(cl3, c1c);
        
        Collection cl4 = new Collection();
        cl4.setTitle("English word with general meaning");
        Collection cl5 = new Collection();
        cl5.setTitle("English word with exact meaning");
        colService.createCollection(cl4, c2c);
        colService.createCollection(cl5, c2c);

        // Category 2
        Category c2 = new Category();
        c2.setTitle("Association");
        catService.createCategory(c2);

        Collection c2l1 = new Collection();
        c2l1.setTitle("Association error based on a compound word/phrase");
        Collection c2l2 = new Collection();
        c2l2.setTitle("Association error triggered by the same character");
        Collection c2l3 = new Collection();
        c2l3.setTitle("Antonym");
        colService.createCollection(c2l1, c2);
        colService.createCollection(c2l2, c2);
        colService.createCollection(c2l3, c2);
        
        // Category 3        
        Category c3 = new Category();
        c3.setTitle("Boundary");
        catService.createCategory(c3);

        Collection c3l1 = new Collection();
        c3l1.setTitle("Single word boundary");
        Collection c3l2 = new Collection();
        c3l2.setTitle("Phrase boundary");
        Collection c3l3 = new Collection();
        c3l3.setTitle("Sentence structure boundary");
        colService.createCollection(c3l1, c3);
        colService.createCollection(c3l2, c3);
        colService.createCollection(c3l3, c3);

        // Category 4
        Category c4 = new Category();
        c4.setTitle("Pronunciation");
        catService.createCategory(c4);

        Collection c4l1 = new Collection();
        c4l1.setTitle("with meaning change");
        Collection c4l2 = new Collection();
        c4l2.setTitle("without meaning change");
        colService.createCollection(c4l1, c4);
        colService.createCollection(c4l2, c4);

        // Category 5
        Category c5 = new Category();
        c5.setTitle("Omission");
        catService.createCategory(c5);

        // Persons
        EPerson ePerson1 = new EPerson("joedon@ou.edu", "Joe", "Don", Gender.MALE, LanguageCode.EN, "Education", EducationalStage.COLLEGE, BigDecimal.valueOf(2), true);
        EPerson ePerson2 = new EPerson("james@ou.edu", "James", "Donald", Gender.MALE, LanguageCode.EN, "Math", EducationalStage.COLLEGE, BigDecimal.valueOf(1.5), false);
        EPerson ePerson3 = new EPerson("john@ou.edu", "John", "Smith", Gender.MALE, LanguageCode.EN, "History", EducationalStage.COLLEGE, BigDecimal.valueOf(1), true);
        EPerson ePerson4 = new EPerson("Joe@ou.edu", "Joe", "Doe", Gender.MALE, LanguageCode.EN, "Biology", EducationalStage.COLLEGE, BigDecimal.valueOf(1), true);
        EPerson ePerson5 = new EPerson("Lindsey@ou.edu", "lindsey", "Cox", Gender.MALE, LanguageCode.EN, "Physics", EducationalStage.COLLEGE, BigDecimal.valueOf(2), true);
        EPerson ePerson6 = new EPerson("jeniffer@ou.edu", "Jeniffer", "Johnson", Gender.FEMALE, LanguageCode.EN, "Engineering", EducationalStage.COLLEGE, BigDecimal.valueOf(2), false);
        EPerson ePerson7 = new EPerson("nick@ou.edu", "Nick", "Well", Gender.MALE, LanguageCode.EN, "Art", EducationalStage.COLLEGE, BigDecimal.valueOf(2.3), true);
        EPerson ePerson8 = new EPerson("ada@ou.edu", "Ada", "Bright", Gender.FEMALE, LanguageCode.EN, "", EducationalStage.HIGH_SCHOOL, BigDecimal.valueOf(1.5), true);
        EPerson ePerson9 = new EPerson("nails@ou.edu", "Nails", "Walton", Gender.MALE, LanguageCode.EN, "", EducationalStage.HIGH_SCHOOL, BigDecimal.valueOf(1), false);
        EPerson ePerson10 = new EPerson("luke@ou.edu", "Luke", "Sherman", Gender.MALE, LanguageCode.EN, "", EducationalStage.MIDDLE_SCHOOL, BigDecimal.valueOf(0.5), false);
        ePersonService.createEPerson(ePerson1);
        ePersonService.createEPerson(ePerson2);
        ePersonService.createEPerson(ePerson3);
        ePersonService.createEPerson(ePerson4);
        ePersonService.createEPerson(ePerson5);
        ePersonService.createEPerson(ePerson6);
        ePersonService.createEPerson(ePerson7);
        ePersonService.createEPerson(ePerson8);
        ePersonService.createEPerson(ePerson9);
        ePersonService.createEPerson(ePerson10);

        // Samples
        Sample sample1 = new Sample("刚刚", "网网 ");
        Sample sample2 = new Sample("挣点儿钱", "净点儿钱");
        Sample sample3 = new Sample("申请", "电请");
        Sample sample4 = new Sample("收件人", "叫件人 ");
        Sample sample5 = new Sample("预祝 ", "服说");
        Sample sample6 = new Sample("预祝", "视说 ");
        Sample sample7 = new Sample("五所", "五听");
        Sample sample8 = new Sample("旅游 ", "旅行");
        Sample sample9 = new Sample("日", "号");
        Sample sample10 = new Sample("星期", "It is week");
        Sample sample11 = new Sample("一封信", "a letter");
        Sample sample12 = new Sample("丽丽", "美美");
        Sample sample13 = new Sample("联系", "关系");
        Sample sample14 = new Sample("以前", "before");
        Sample sample15 = new Sample("以后", "after");
        Sample sample16 = new Sample("左边", "left side");
        Sample sample17 = new Sample("右边", "right side");
        Sample sample18 = new Sample("可以", "所以");
        Sample sample19 = new Sample("学校楼 ", "classroom building");
        Sample sample20 = new Sample("学校楼 ", "school building");
        sampleService.createSample(sample1);
        sampleService.createSample(sample2);
        sampleService.createSample(sample3);
        sampleService.createSample(sample4);
        sampleService.createSample(sample5);
        sampleService.createSample(sample6);
        sampleService.createSample(sample7);
        sampleService.createSample(sample8);
        sampleService.createSample(sample9);
        sampleService.createSample(sample10);
        sampleService.createSample(sample11);
        sampleService.createSample(sample12);
        sampleService.createSample(sample13);
        sampleService.createSample(sample14);
        sampleService.createSample(sample15);
        sampleService.createSample(sample16);
        sampleService.createSample(sample17);
        sampleService.createSample(sample18);
        sampleService.createSample(sample19);
        sampleService.createSample(sample20);
        
        // tfiles
        TFile tFile1 = new TFile(new Long(25000), "checksum", "md5", "description", 20, 35);
        tFileService.create(tFile1);
        
        // ECase
        ECase eCase1 = new ECase();
        eCase1.setEPerson(ePerson1);
        eCase1.setSample(sample1);
        ECase eCase2 = new ECase();
        eCase2.setEPerson(ePerson2);
        eCase2.setSample(sample2);
        ECase eCase3 = new ECase();
        eCase3.setEPerson(ePerson3);
        eCase3.setSample(sample3);
        ECase eCase4 = new ECase();
        eCase4.setEPerson(ePerson4);
        eCase4.setSample(sample4);
        ECase eCase5 = new ECase();
        eCase5.setEPerson(ePerson5);
        eCase5.setSample(sample5);
        ECase eCase6 = new ECase();
        eCase6.setEPerson(ePerson6);
        eCase6.setSample(sample6);
        ECase eCase7 = new ECase();
        eCase7.setEPerson(ePerson7);
        eCase7.setSample(sample7);
        ECase eCase8 = new ECase();
        eCase8.setEPerson(ePerson8);
        eCase8.setSample(sample8);
        ECase eCase9 = new ECase();
        eCase9.setEPerson(ePerson9);
        eCase9.setSample(sample9);
        ECase eCase10 = new ECase();
        eCase10.setEPerson(ePerson10);
        eCase10.setSample(sample10);
        eCaseService.createECase(eCase1, c2l1);
        eCaseService.createECase(eCase2, c2l1);
        eCaseService.createECase(eCase3, c2l2);
        eCaseService.createECase(eCase4, c2l3);
        eCaseService.createECase(eCase5, c3l1);
        eCaseService.createECase(eCase6, c3l2);
        eCaseService.createECase(eCase7, c3l3);
        eCaseService.createECase(eCase8, c4l1);
        eCaseService.createECase(eCase9, c4l2);
        eCaseService.createECase(eCase10, c2l3);

        return new ModelAndView("about", "crumbs", map);

    }

    @RequestMapping(value = { "/about" }, method = RequestMethod.GET)
    public ModelAndView aboutPage() throws SQLException {
        Map<String, String> map = getActionBreadcrumbs("About");
        return new ModelAndView("about", "crumbs", map);
    }

    @RequestMapping(value = "/image-byte-array", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageAsByteArray() throws IOException {
        final InputStream in = servletContext.getResourceAsStream("resources/logo.jpg");
        return IOUtils.toByteArray(in);
    }
    
}
