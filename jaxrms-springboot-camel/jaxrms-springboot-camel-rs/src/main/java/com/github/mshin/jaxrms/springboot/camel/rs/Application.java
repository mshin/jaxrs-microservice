package com.github.mshin.jaxrms.springboot.camel.rs;

import java.util.Arrays;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.github.mshin.jaxrms.rs.api.JaxrmsService;

@SpringBootApplication
public class Application {

    @Autowired
    JaxrmsServiceBean jaxrmsServiceBean;
    
    @Autowired
    private Bus bus;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public JAXRSServerFactoryBean rsServer() {
//        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
//        factoryBean.setBus(bus);
//        factoryBean.setAddress("/");
//        factoryBean.setProviders(Arrays.asList(jacksonJaxbJsonProvider()));
//        factoryBean.setFeatures(Arrays.asList(swagger2Feature()));
//        factoryBean.setServiceClass(JaxrmsService.class);
//        return factoryBean;
//    }

    @Bean
    public Server server() {
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setBus(bus);
        factoryBean.setAddress("/api2");
        factoryBean.setProviders(Arrays.asList(new JacksonJsonProvider()));
        factoryBean.setFeatures(Arrays.asList(swagger2Feature()));
        factoryBean.setServiceBeans(Arrays.asList(jaxrmsServiceBean));
        //factoryBean.setServiceClass(JaxrmsService.class);
        return factoryBean.create();
    }
    
    @Bean
    public JAXRSServerFactoryBean rsServer() {
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setBus(bus);
        factoryBean.setAddress("/api");
        factoryBean.setProviders(Arrays.asList(new JacksonJsonProvider()));
        factoryBean.setFeatures(Arrays.asList(swagger2Feature()));
        //factoryBean.setServiceBeans(Arrays.asList(jaxrmsServiceBean));
        
        factoryBean.setServiceClass(JaxrmsService.class);
        //factoryBean.create();
        return factoryBean;
    }
    
    @Bean
    public JacksonJaxbJsonProvider jacksonJaxbJsonProvider() {
        return new JacksonJaxbJsonProvider();
    }
    
    @Bean
    public Swagger2Feature swagger2Feature() {
        Swagger2Feature feature = new Swagger2Feature();
        feature.setRunAsFilter(true);
        return feature;
    }
}