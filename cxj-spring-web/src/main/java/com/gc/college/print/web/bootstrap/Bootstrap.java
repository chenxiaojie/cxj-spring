package com.gc.college.print.web.bootstrap;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by chenxiaojie on 18/8/06.
 */
public class Bootstrap implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringConfiguration.class);

        context.addFilter("characterEncodingFilter", new CharacterEncodingFilter("UTF-8", true));
        context.addFilter("httpPutFormContentFilter", new HttpPutFormContentFilter());

        ServletRegistration.Dynamic dispatcher = context.addServlet("dispatcher", new DispatcherServlet(ctx));
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);

        ctx.setServletContext(context);
    }
}
