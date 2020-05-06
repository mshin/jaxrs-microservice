package com.github.mshin.jaxrms.spring.camel.eap.rs;

import org.springframework.stereotype.Component;

import com.github.mshin.exception.response.model.ExceptionResponses;
import com.github.mshin.jaxrms.rs.api.model.GetGreetingRequest;
import com.github.mshin.jaxrms.rs.api.model.GetGreetingResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author MunChul Shin
 *
 */
@Component
public class JaxrmsServiceBean {

    private static final String CLASS_NAME = JaxrmsServiceBean.class.getName();
    private static final Logger LOGGER = LoggerFactory.getLogger(CLASS_NAME);

    public GetGreetingResponse getGreeting(GetGreetingRequest request) throws ExceptionResponses {
        LOGGER.debug("invoked service implementation for getGreeting ");

        String name = (null == request) ? "" : request.getName();

        GetGreetingResponse response = new GetGreetingResponse("Hi " + name + "!", 0);

        return response;
    }

}