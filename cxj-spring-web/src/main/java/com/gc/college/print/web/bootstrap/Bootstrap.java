package com.gc.college.print.web.bootstrap;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * Created by chenxiaojie on 18/8/06.
 */
public class Bootstrap implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringMVCConfig.class);

        FilterRegistration.Dynamic characterEncodingFilter = context.addFilter("characterEncodingFilter", new CharacterEncodingFilter("UTF-8", true));
        characterEncodingFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), true, "/*");

        FilterRegistration.Dynamic httpPutFormContentFilter = context.addFilter("httpPutFormContentFilter", new HttpPutFormContentFilter());
        httpPutFormContentFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), true, "/*");

        ServletRegistration.Dynamic dispatcher = context.addServlet("dispatcher", new DispatcherServlet(ctx));
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);

        ctx.setServletContext(context);
    }
}
