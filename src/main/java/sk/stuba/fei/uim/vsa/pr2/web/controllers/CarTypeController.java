package sk.stuba.fei.uim.vsa.pr2.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.CarType;
import sk.stuba.fei.uim.vsa.pr2.service.CarTypeService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarTypeDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factories.CarTypeResponseFactory;

import java.util.List;
import java.util.stream.Collectors;

@Path("/")
public class CarTypeController {

    private final ObjectMapper json = new ObjectMapper();
    private final CarTypeService service = new CarTypeService();
    private final CarTypeResponseFactory factory = new CarTypeResponseFactory();

    @GET
    @Path("/cartypes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarTypes() {
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
    public Response getCarType(@PathParam("id") Long id) {
        CarType carType = service.getCarType(id);
        if (carType == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            CarTypeDto carTypeDto = factory.transformToDto(carType);
            return Response.status(Response.Status.OK).entity(carTypeDto).build();
        }
    }

    // TODO: POST
    // TODO: POST VNORENY
    // TODO: PUT
    @DELETE
    @Path("/cartypes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCarType(@PathParam("id") Long id){
        CarType carType = service.deleteCarType(id);
        if(carType == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

}