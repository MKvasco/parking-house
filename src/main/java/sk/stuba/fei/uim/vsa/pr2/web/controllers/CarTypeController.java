package sk.stuba.fei.uim.vsa.pr2.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.CarType;
import sk.stuba.fei.uim.vsa.pr2.domain.User;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.CarTypeService;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.ServiceController;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarTypeDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factories.CarTypeFactory;

import java.util.List;
import java.util.stream.Collectors;

@Path("/")
public class CarTypeController {

    private final ObjectMapper json = new ObjectMapper();
    private final CarTypeService service = new CarTypeService();
    private final CarTypeFactory factory = new CarTypeFactory();

    @GET
    @Path("/cartypes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarTypes(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) {
        User userAuth = new ServiceController().getUserAuth(authorization);
        if (userAuth == null) return Response.status(Response.Status.UNAUTHORIZED).build();
        // TODO: PARAMETER SEARCH
        List<CarType> carTypes = service.getCarTypes();
        if (carTypes.isEmpty()) {
            return Response.status(Response.Status.OK).entity("").build();
        } else {
            List<CarTypeDto> carTypeDtoList = carTypes.stream().map(factory::transformToDto).collect(Collectors.toList());
            return Response.status(Response.Status.OK).entity(carTypeDtoList).build();
        }
    }

    @GET
    @Path("/cartypes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarType(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization, @PathParam("id") Long id) {
        User userAuth = new ServiceController().getUserAuth(authorization);
        if (userAuth == null) return Response.status(Response.Status.UNAUTHORIZED).build();
        CarType carType = service.getCarType(id);
        if (carType == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            CarTypeDto carTypeDto = factory.transformToDto(carType);
            return Response.status(Response.Status.OK).entity(carTypeDto).build();
        }
    }

    // TODO: POST VNORENY
    @DELETE
    @Path("/cartypes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCarType(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization, @PathParam("id") Long id){
        User userAuth = new ServiceController().getUserAuth(authorization);
        if (userAuth == null) return Response.status(Response.Status.UNAUTHORIZED).build();
        CarType carType = service.deleteCarType(id);
        if(carType == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

}