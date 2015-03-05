package org.beatific.daram.web.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	private static final String BASE_PACKAGE = "basePackage";
	private static final String CONFIG_LOCATION = "configFileLocation";

    public void onStartup(final ServletContext context) throws ServletException {

        final AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();

        root.scan("org.beatific.daram.web");
        context.addListener(new ContextLoaderListener(root));
        
        context.setInitParameter(BASE_PACKAGE, "org.beatific.daram");
        context.setInitParameter(CONFIG_LOCATION, "daram-context-spring.xml");

        final ServletRegistration.Dynamic appServlet = context.addServlet("appServlet",new DispatcherServlet(new GenericWebApplicationContext()));
        appServlet.setAsyncSupported(true);
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/*");
    }
}
