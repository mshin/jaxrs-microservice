package com.github.mshin.jaxrms.crud.rs.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.github.mshin.exception.response.model.ExceptionResponses;
import com.github.mshin.jaxrms.crud.rs.api.model.PostNameRequest;
import com.github.mshin.jaxrms.crud.rs.api.model.PostNameResponse;
import com.github.mshin.jaxrms.crud.rs.api.model.PutNameRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author MunChul Shin
 */
@Api
@Path("/")
public interface JaxrmsCrudService {

    @POST
    @Path("post")
    @ApiOperation(value = "This API method will be used to create a name record.")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = PostNameResponse.class) })
    public PostNameResponse postName(
            @ApiParam(required = true, name = "PostNameRequest", value = "The request to create a name record.") PostNameRequest postNameRequest)
            throws ExceptionResponses;

    @PUT
    @Path("put/{id}")
    @ApiOperation(value = "This API method will be used to modify a name record.")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String putName(@PathParam("id") String id, PutNameRequest putNameRequest) throws ExceptionResponses;

    @GET
    @Path("/get")
    @ApiOperation(value = "This API method will be used to return a name record.")
    @Produces(MediaType.TEXT_PLAIN)
    public String getName(@QueryParam("id") String id);

    @DELETE
    @Path("/delete/{id}")
    @ApiOperation(value = "This API method will be used to delete a name record.")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteName();

    @GET
    @Path("/ping")
    @ApiOperation(value = "This API method will be used to ping the service to verify it is working.")
    @Produces(MediaType.TEXT_PLAIN)
    default public String ping() {
        return "ping";
    }

}
