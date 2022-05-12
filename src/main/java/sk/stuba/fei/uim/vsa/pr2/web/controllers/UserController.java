package sk.stuba.fei.uim.vsa.pr2.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.User;
import sk.stuba.fei.uim.vsa.pr2.service.UserService;
import sk.stuba.fei.uim.vsa.pr2.web.response.UserDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factories.UserFactory;

import java.util.List;
import java.util.stream.Collectors;

@Path("/")
public class UserController {
    private final ObjectMapper json = new ObjectMapper();
    private final UserService service = new UserService();
    private final UserFactory factory = new UserFactory();

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        //TODO: PARAMETER SEARCH
        List<User> users = service.getUsers();
        List<UserDto> userDtoList = users.stream().map(factory::transformToDto).collect(Collectors.toList());
        if(users.isEmpty()){
            return Response.status(Response.Status.OK).entity("").build();
        }else{
            return Response.status(Response.Status.OK).entity(userDtoList).build();
        }
    }

    @GET
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") Long id){
        User user = service.getUser(id);
        UserDto userDto = factory.transformToDto(user);
        if(userDto == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.status(Response.Status.OK).entity(userDto).build();
        }
    }
    // TODO: POST
    // TODO: PUT
    // TODO: DELETE
}
