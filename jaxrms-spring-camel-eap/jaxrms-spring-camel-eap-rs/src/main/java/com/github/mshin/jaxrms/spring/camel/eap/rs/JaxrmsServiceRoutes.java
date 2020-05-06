package com.github.mshin.jaxrms.spring.camel.eap.rs;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author MunChul Shin
 *
 */
@Component
public class JaxrmsServiceRoutes extends RouteBuilder {

    @Autowired
    private JaxrmsServiceBean jaxrmsServiceBean;

    @Override
    public void configure() throws Exception {
        // @formatter:off
        from("direct-vm:getGreeting").routeId("getGreeting").bean(jaxrmsServiceBean, "getGreeting(${body})")
                .id("jaxrmsServiceBean");
        // @formatter:on

    }

}
