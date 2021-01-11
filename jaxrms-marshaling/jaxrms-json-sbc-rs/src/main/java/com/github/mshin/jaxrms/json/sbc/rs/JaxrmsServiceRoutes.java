package com.github.mshin.jaxrms.json.sbc.rs;

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
        from("cxfrs:bean:rsServer?bindingStyle=SimpleConsumer").routeId("jaxrmsGreetingRoute")
            .log("headers: ${in.headers}").log("body: ${body}")
            .choice()
                .when(header("operationName").isEqualTo("getGreeting"))
                    .bean(jaxrmsServiceBean, "getGreeting(${body})")
                //.to("bean:jaxrmsServiceBean?method=getGreeting(${body})")
                .when(header("operationName").isEqualTo("ping"))
                    .bean(jaxrmsServiceBean, "ping(${body})");
        // @formatter:on

    }

}