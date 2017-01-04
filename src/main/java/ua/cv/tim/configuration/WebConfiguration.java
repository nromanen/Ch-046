package ua.cv.tim.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by vyach on 28.12.2016.
 */

@EnableWebMvc
@Configuration
@ComponentScan({"ua.cv.tim"})
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		if (!registry.hasMappingForPattern("/webjars/**")) {
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
		if (!registry.hasMappingForPattern("/images/**")) {
			registry.addResourceHandler("/images/**").addResourceLocations("classpath:/images/");
		}
		if (!registry.hasMappingForPattern("/views/**")) {
			registry.addResourceHandler("/views/**").addResourceLocations("classpath:/views/");
		}
		if (!registry.hasMappingForPattern("/css/**")) {
			registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
		}
		if (!registry.hasMappingForPattern("/js/**")) {
			registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
		}
		if (!registry.hasMappingForPattern("/app/**")) {
			registry.addResourceHandler("/app/**").addResourceLocations("classpath:/app/");
		}
		if (!registry.hasMappingForPattern("/node_modules/**")) {
			registry.addResourceHandler("/node_modules/**").addResourceLocations("classpath:/node_modules/");
		}
	}

	public WebConfiguration() {
		super();
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		//viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}


}
