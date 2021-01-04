package com.github.mshin.jaxrms.springboot.camel.rs;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.mshin.jaxrms.rs.api.model.GetGreetingRequest;
import com.github.mshin.jaxrms.rs.api.model.GetGreetingResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JaxrmsServiceBeanTest {

    @Autowired
    JaxrmsServiceBean bean;

    @Test
    public void getGreetingTest() {
        GetGreetingRequest request = new GetGreetingRequest();
        request.setName("Me");
        GetGreetingResponse response = bean.getGreeting(request);
        
        assertTrue(response.getResponseCode().equals(0));
        assertTrue(response.getGreeting().equals("Hi Me!"));
    }
}
