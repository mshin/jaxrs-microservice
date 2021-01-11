package com.github.mshin.jaxrms.springboot.rs;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.mshin.jaxrms.crud.rs.api.model.PostNameRequest;
import com.github.mshin.jaxrms.crud.rs.api.model.PostNameResponse;
import com.github.mshin.jaxrms.crud.rs.api.model.PutNameRequest;
import com.github.mshin.jaxrms.crud.rs.api.model.ResponseMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JaxrmsServiceBeanTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(JaxrmsServiceBeanTest.class.getName());

    @Autowired
    JaxrmsCrudServiceBean bean;

    public static String id;

    @Test
    public void runTestsInOrder() {
        createAndGetTest();
        updateAndGetTest();
        deleteAndGetTest();
    }

    public void createAndGetTest() throws NumberFormatException {
        PostNameRequest request = new PostNameRequest();
        request.setName("Me");
        PostNameResponse response = bean.postName(request);

        id = response.getId();

        LOGGER.info("returned id: " + id);

        assertTrue(response.getResponseCode().equals(0));
        // fail test if exception thrown here.
        Integer.parseInt(id);

        ResponseMessage nameReturned = bean.getName(id);

        assertTrue("Me".equals(nameReturned.getResponse()));
    }

    public void updateAndGetTest() {
        LOGGER.info("update id: " + id);
        PutNameRequest request = new PutNameRequest();
        request.setName("You");
        ResponseMessage response = bean.putName(id, request);
        LOGGER.info("updateTest response: " + response);
        ResponseMessage nameReturned = bean.getName(id);

        assertTrue("You".equals(nameReturned.getResponse()));
    }

    public void deleteAndGetTest() {
        ResponseMessage response = bean.deleteName(id);
        LOGGER.info("deleteTest response: " + response);

        ResponseMessage nameReturned = bean.getName(id);
        LOGGER.info("nameReturned: " + nameReturned);
        assertTrue(null == nameReturned || null == nameReturned.getResponse() || "".equals(nameReturned.getResponse()));
    }
}
