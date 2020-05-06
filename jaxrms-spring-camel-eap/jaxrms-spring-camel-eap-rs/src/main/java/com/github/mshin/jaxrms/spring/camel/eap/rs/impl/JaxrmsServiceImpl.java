package com.github.mshin.jaxrms.spring.camel.eap.rs.impl;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.mshin.exception.response.factory.ExceptionResponseFactory;
import com.github.mshin.exception.response.model.ExceptionResponse;
import com.github.mshin.exception.response.model.ExceptionResponses;
import com.github.mshin.jaxrms.rs.api.JaxrmsService;
import com.github.mshin.jaxrms.rs.api.model.GetGreetingRequest;
import com.github.mshin.jaxrms.rs.api.model.GetGreetingResponse;

/**
 * 
 * @author MunChul Shin
 *
 */
@Component
public class JaxrmsServiceImpl implements JaxrmsService {

    private static final String CLASS_NAME = JaxrmsServiceImpl.class.getName();
    private static final Logger LOGGER = LoggerFactory.getLogger(CLASS_NAME);

    @EndpointInject
    private ProducerTemplate producer;

    @Override
    public GetGreetingResponse getGreeting(GetGreetingRequest request) throws ExceptionResponses {
        LOGGER.debug("Request for getGreeting : {} ", request);
        try {
            final Object result = producer.requestBody("direct-vm:getGreeting", request);
            if (result instanceof GetGreetingResponse) {
                return (GetGreetingResponse) result;
            }
        } catch (CamelExecutionException ex) {
            handleCamelException(ex);
        } catch (Exception exception) {
            LOGGER.error("Unexpected error occurred: ", exception);
            throw new ExceptionResponses(ExceptionResponseFactory.createInternalServerErrorExceptionResponse(
                    exception.getClass().getName() + " " + exception.getMessage()));
        }
        return null;
    }

    private void handleCamelException(CamelExecutionException ex) {

        Exception exception = ex.getExchange().getException();

        if (exception instanceof ExceptionResponse) {
            LOGGER.error("Error along camel route: ", exception);
            throw new ExceptionResponses((ExceptionResponse) exception);
        } else if (exception instanceof ExceptionResponses) {
            LOGGER.error("Error along camel route: ", exception);
            throw (ExceptionResponses) exception;
        } else {
            LOGGER.error("Error along camel route: Unexpected error occurred: ", exception);
            throw new ExceptionResponses(ExceptionResponseFactory.createInternalServerErrorExceptionResponse(
                    exception.getClass().getName() + " " + exception.getMessage()));
        }
    }

}
