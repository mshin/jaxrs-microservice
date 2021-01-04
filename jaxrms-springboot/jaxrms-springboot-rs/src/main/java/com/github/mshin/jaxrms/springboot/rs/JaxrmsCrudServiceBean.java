package com.github.mshin.jaxrms.springboot.rs;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.mshin.exception.response.model.ExceptionResponse;
import com.github.mshin.exception.response.model.ExceptionResponses;
import com.github.mshin.jaxrms.crud.rs.api.JaxrmsCrudService;
import com.github.mshin.jaxrms.crud.rs.api.model.PostNameRequest;
import com.github.mshin.jaxrms.crud.rs.api.model.PostNameResponse;
import com.github.mshin.jaxrms.crud.rs.api.model.PutNameRequest;

@Component
public class JaxrmsCrudServiceBean implements JaxrmsCrudService {

    public static final Logger LOGGER = LoggerFactory.getLogger(JaxrmsCrudServiceBean.class.getName());

    public static Map<Integer, String> inMemoryNameDb = new HashMap<>();
    public static Integer idCount = 0;

    public static Integer getId() {
        return idCount++;
    }

    @Override
    public PostNameResponse postName(PostNameRequest postNameRequest) throws ExceptionResponses {
        if (null == postNameRequest) {
            throw new ExceptionResponses(new ExceptionResponse(Response.Status.BAD_REQUEST, "null name request"));
        }

        Integer recordId = getId();
        LOGGER.info("Created recordId: " + recordId);
        inMemoryNameDb.put(recordId, postNameRequest.getName());

        PostNameResponse response = new PostNameResponse();
        response.setId(recordId.toString());
        response.setResponseCode(0);
        return response;
    }

    @Override
    public String putName(String id, PutNameRequest putNameRequest) throws ExceptionResponses {
        if (null == putNameRequest) {
            throw new ExceptionResponses(
                    new ExceptionResponse(Response.Status.BAD_REQUEST, "invalidRequest", "null name request"));
        }
        if (null == id) {
            throw new ExceptionResponses(
                    new ExceptionResponse(Response.Status.BAD_REQUEST, "invalidRequest", "null id"));
        }
        Integer idInt = null;
        try {
            idInt = Integer.parseInt(id);
        } catch (NumberFormatException nfe) {
            throw new ExceptionResponses(
                    new ExceptionResponse(Response.Status.BAD_REQUEST, "invalidRequest", "non numeric id: " + id, nfe));
        }
        String previousVal = inMemoryNameDb.put(idInt, putNameRequest.getName());

        return "Previous value: " + previousVal + " new value: " + putNameRequest.getName();
    }

    @Override
    public String getName(String id) {
        Integer idInt = null;
        try {
            idInt = Integer.parseInt(id);
        } catch (NumberFormatException nfe) {
            throw new ExceptionResponses(
                    new ExceptionResponse(Response.Status.BAD_REQUEST, "invalidRequest", "non numeric id: " + id, nfe));
        }
        String name = inMemoryNameDb.get(idInt);
        return name;
    }

    @Override
    public String deleteName(String id) {
        Integer idInt = null;
        try {
            idInt = Integer.parseInt(id);
        } catch (NumberFormatException nfe) {
            throw new ExceptionResponses(
                    new ExceptionResponse(Response.Status.BAD_REQUEST, "invalidRequest", "non numeric id: " + id, nfe));
        }
        String nameRemoved = inMemoryNameDb.remove(idInt);
        return "Removed value: " + nameRemoved;
    }

}