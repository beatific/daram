package org.beatific.daram.web.config;

import net.sf.json.spring.web.servlet.view.JsonView;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("org.beatific.daram.web")
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(
        final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Bean  
    public UrlBasedViewResolver setupViewResolver() {  
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();  
        resolver.setPrefix("/views/");  
        resolver.setSuffix(".jsp");  
        resolver.setViewClass(JstlView.class);  
        return resolver;  
    }
    
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("/app/scripts/");
		registry.addResourceHandler("/lib/**").addResourceLocations("/app/vendor/");
		registry.addResourceHandler("/views/**").addResourceLocations("/app/views/");
		registry.addResourceHandler("/css/**").addResourceLocations("/app/styles/");
	}
	
	@Bean
	public JsonView jsonView() {
		JsonView jsonView = new JsonView();
		jsonView.setContentType("application/json;charset=UTF-8");
		return jsonView;
	}
	
	@Bean
	public BeanNameViewResolver viewResolver() {
		BeanNameViewResolver resolver = new BeanNameViewResolver();
		resolver.setOrder(1);
		return resolver;
	}
}