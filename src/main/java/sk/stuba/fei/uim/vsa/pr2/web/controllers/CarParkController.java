package sk.stuba.fei.uim.vsa.pr2.web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.CarPark;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarParkDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factories.CarParkResponseFactory;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/carparkfloors")
public class CarParkController {

    private static final Logger LOGGER = Logger.getLogger(CarParkController.class.getName());

    private final ObjectMapper json = new ObjectMapper();
    private final CarParkService service = new CarParkService();
    private final CarParkResponseFactory factory = new CarParkResponseFactory();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarParks() {
        List<CarPark> carParks = service.getCarParks();
        List<CarParkDto> carParkDtoList = carParks.stream().map(factory::transformToDto).collect(Collectors.toList());
        if(carParkDtoList.isEmpty()){
            return Response.status(Response.Status.OK).entity("").build();
        }else{
            return Response.status(Response.Status.OK).entity(carParkDtoList).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarParks(@PathParam("id") Long id){
        CarPark carPark = service.getCarPark(id);
        if(carPark == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            CarParkDto carParkDto = factory.transformToDto(carPark);
            return Response.status(Response.Status.OK).entity(carParkDto).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCarPark(String body) {
        try{
            CarParkDto carParkDto = json.readValue(body, CarParkDto.class);
            CarPark carPark = factory.transformToEntity(carParkDto);
            service.createCarPark(carPark.getName(), carPark.getAddress(), carPark.getPricePerHour());
            carParkDto = factory.transformToDto(carPark);
            return Response.status(Response.Status.CREATED).entity(carParkDto).build();
        }catch (JsonProcessingException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

//    TODO:PUT REQUEST

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCarPark(@PathParam("id") Long id) {
        CarPark carPark = service.getCarPark(id);
        if(carPark == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            service.deleteCarPark(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
}
