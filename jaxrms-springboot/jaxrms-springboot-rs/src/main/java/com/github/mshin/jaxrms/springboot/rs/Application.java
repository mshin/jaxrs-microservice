package com.github.mshin.jaxrms.springboot.rs;

import java.util.Arrays;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.github.mshin.exception.response.handler.ExceptionResponseHandler;
import com.github.mshin.exception.response.handler.ExceptionResponsesHandler;

@SpringBootApplication
public class Application {

    @Autowired
    JaxrmsCrudServiceBean jaxrmsCrudServiceBean;

    @Autowired
    private Bus bus;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Server server() {
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setBus(bus);
        factoryBean.setAddress("/");
        factoryBean.setProviders(Arrays.asList(new ExceptionResponseHandler(), new ExceptionResponsesHandler(),
                new JacksonJsonProvider()));
        factoryBean.setFeatures(Arrays.asList(new Swagger2Feature()));
        factoryBean.setServiceBeans(Arrays.asList(jaxrmsCrudServiceBean));
        return factoryBean.create();
    }

}