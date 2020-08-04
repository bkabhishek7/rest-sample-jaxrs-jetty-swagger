package rest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
 
@Path("/student")
@Api(value = "/student", description = "Operations about students",/* authorizations = {
  @Authorization(value = "petstore_auth",
  scopes = {
    @AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
    @AuthorizationScope(scope = "read:pets", description = "read your pets")
  })
},*/ tags = "student")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class StudentResource {
 
    @GET
    @Path("/info/{user}")
    @Produces("application/json")
    @ApiOperation(value = "Find user by ID", 
    notes = "Returns a string with user ID", 
    response = String.class
  )
  @ApiResponses(value = { @ApiResponse(code = 200, message = "message"),
		  @ApiResponse(code = 400, message = "Invalid ID supplied"),
      @ApiResponse(code = 404, message = "user not found") })
    public Response getStudentInformation(@PathParam("user") String user) {
        //this method get the information of the student
        return Response.ok("API for getting information for user " + user).build();
    }
 
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response enrollStudent() {
        //for simplicity, we just return a string
        return Response.ok("Successfully enrolled student via POST request").build();
    }
 
    @PUT
    @Produces("application/json")
    public Response addNewStudent() {
        return Response.ok("Added new Student").build();
    }
 
    @DELETE
    @Path("/{user}")
    @Produces("text/plain")
    public Response deleteStudent(@PathParam("user") String user) {
        return Response.ok("Successfully deleted student " + user).build();
    }
}