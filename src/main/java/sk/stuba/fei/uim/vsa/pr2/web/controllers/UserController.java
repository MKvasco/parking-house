package sk.stuba.fei.uim.vsa.pr2.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.domain.User;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.UserService;
import sk.stuba.fei.uim.vsa.pr2.web.response.UserDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factories.UserFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("/")
public class UserController {
    private final ObjectMapper json = new ObjectMapper();
    private final UserService service = new UserService();
    private final UserFactory factory = new UserFactory();

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@QueryParam("email") String email) {
        List<User> users = service.getUsers();
        List<UserDto> userDtoList = users.stream().map(factory::transformToDto).collect(Collectors.toList());
        if(email != null){
            List<UserDto> filteredUsers = new ArrayList<>();
            for(UserDto user : userDtoList){
                if(Objects.equals(user.getEmail(), email)) filteredUsers.add(user);
            }
            return Response.status(Response.Status.OK).entity(filteredUsers).build();
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
    @POST
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(String body){
        try{
            UserDto userDto = json.readValue(body, UserDto.class);
            User user = factory.transformToEntity(userDto);
            if(user == null) throw new Exception();
            System.out.println(user);
            userDto = factory.transformToDto(user);
            return Response.status(Response.Status.CREATED).entity(userDto).build();
        }catch(Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Long id){
        User user = service.getUser(id);
        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.deleteUser(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
