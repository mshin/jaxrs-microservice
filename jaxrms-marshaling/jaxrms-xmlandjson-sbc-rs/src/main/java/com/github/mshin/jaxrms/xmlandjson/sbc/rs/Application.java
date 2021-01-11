package com.github.mshin.jaxrms.xmlandjson.sbc.rs;

import java.util.Arrays;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.github.mshin.exception.response.handler.ExceptionResponseHandler;
import com.github.mshin.exception.response.handler.ExceptionResponsesHandler;
import com.github.mshin.jaxrms.xmlandjson.rs.api.JaxrmsService;

@SpringBootApplication
public class Application {

    @Autowired
    private Bus bus;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public JAXRSServerFactoryBean rsServer() {
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setBus(bus);
        factoryBean.setAddress("/");
        factoryBean.setProviders(Arrays.asList(new ExceptionResponseHandler(), new ExceptionResponsesHandler(),
                new JacksonJaxbJsonProvider()));
        factoryBean.setFeatures(Arrays.asList(swagger2Feature()));
        factoryBean.setServiceClass(JaxrmsService.class);
        return factoryBean;
    }

    @Bean
    public Swagger2Feature swagger2Feature() {
        Swagger2Feature feature = new Swagger2Feature();
        feature.setRunAsFilter(true);
        return feature;
    }
}