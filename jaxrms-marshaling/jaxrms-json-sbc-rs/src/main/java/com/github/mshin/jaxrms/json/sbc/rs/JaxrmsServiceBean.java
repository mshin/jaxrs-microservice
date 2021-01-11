package com.github.mshin.jaxrms.json.sbc.rs;

import org.springframework.stereotype.Component;

import com.github.mshin.exception.response.model.ExceptionResponses;
import com.github.mshin.jaxrms.json.rs.api.JaxrmsService;
import com.github.mshin.jaxrms.json.rs.api.model.GetGreetingRequest;
import com.github.mshin.jaxrms.json.rs.api.model.GetGreetingResponse;

@Component
public class JaxrmsServiceBean implements JaxrmsService {

    public GetGreetingResponse getGreeting(GetGreetingRequest getGreetingRequest) throws ExceptionResponses {
        GetGreetingResponse response = new GetGreetingResponse();
        response.setResponseCode(0);
        response.setGreeting("Hi " + getGreetingRequest.getName() + "!");
        return response;
    }

}