package co.com.example.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import co.com.example.services.jaw.rs.AuthorResource;
import co.com.example.services.jaw.rs.BookResource;
import co.com.example.services.jaw.rs.CategoryResource;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "co.com.example.services.jaw.rs" })
public class AppConfig {

	@Autowired
	private AuthorResource peopleRestService;

	@Autowired
	private CategoryResource categoryService;
	
	@Autowired
	private BookResource bookResource;

	@Bean(destroyMethod = "shutdown")
	public SpringBus cxf() {
		return new SpringBus();
	}

	@Bean(destroyMethod = "destroy")
	@DependsOn("cxf")
	public Server jaxRsServer() {
		final JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();

		//Sets the single resource bean.
		//factory.setServiceBean(peopleRestService);
		
		//Sets the resource beans.
		//factory.setServiceBeans(Arrays.<Object>asList(peopleRestService, categoryService));

		//Get the beans (Components) from the spring app context.
		setFactoryBeans(factory);

		factory.setProvider(new JacksonJsonProvider());
		factory.setBus(cxf());
		factory.setAddress("/");

		//SWAGGER CONFIG PLUGIN-APACHE
		factory.getFeatures().add(swaggerConfiguration());
		
		return factory.create();
	}

	private Swagger2Feature swaggerConfiguration() {
		Swagger2Feature feature = new Swagger2Feature();
	    feature.setBasePath("/rest-api");
	    feature.setTitle("Apache CXF JAX-RS & Spring Boot Testing");
	    feature.setVersion("1.0.0");
	    feature.setDescription("Simple test of the combination of several technologies");
		return feature;
	}

	private void setFactoryBeans(JAXRSServerFactoryBean factory) {
		List<Object> beanList = new ArrayList<>();
		beanList.add(peopleRestService);
		beanList.add(categoryService);
		beanList.add(bookResource);
		factory.setServiceBeans(beanList);
	}

	/**
	 * Register a new CXF servlet for the rs request.
	 * 
	 * @return ServletRegistrationBean
	 */
	// @Bean
	// public ServletRegistrationBean cxfServlet() {
	// final ServletRegistrationBean servletRegistrationBean =
	// new ServletRegistrationBean(new CXFServlet());
	// servletRegistrationBean.setLoadOnStartup(1);
	// return servletRegistrationBean;
	// }
}
