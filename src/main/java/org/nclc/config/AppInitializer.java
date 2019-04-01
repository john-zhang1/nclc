/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nclc.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan("org.nclc");
        container.addListener(new ContextLoaderListener(context));

        context.register(WebConfig.class);
        context.setServletContext(container);

        ServletRegistration.Dynamic dispatcher = container.addServlet("mvc", new DispatcherServlet(context));        
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }
    
}
