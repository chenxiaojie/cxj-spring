package com.chenxiaojie.college.print;


import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

public class TomcatServer {

    private static final String PROJECT = "cxj-spring-web";

    private static final String WEBAPP = PROJECT + "/src/main/webapp";
    private static final String WEBAPP_PATH;

    private static final String CLASS = PROJECT + "/target/classes";
    private static final String CLASS_PATH;

    private static final String WEB_APP_MOUNT = "/WEB-INF/classes";

    static {
        WEBAPP_PATH = new File(WEBAPP).getAbsolutePath();
        CLASS_PATH = new File(CLASS).getAbsolutePath();
    }

    public static void main(String[] args) throws ServletException, LifecycleException {

        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8081";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", WEBAPP_PATH);
        System.out.println("configuring app with basedir: " + WEBAPP_PATH);

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, WEB_APP_MOUNT, CLASS_PATH, "/"));
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }

}
