package sk.stuba.fei.uim.vsa.pr2.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.CarPark;
import sk.stuba.fei.uim.vsa.pr2.domain.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarParkDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factories.CarParkFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/")
public class CarParkController {

    private final ObjectMapper json = new ObjectMapper();
    private final CarParkService service = new CarParkService();
    private final CarParkFactory factory = new CarParkFactory();

    @GET
    @Path("/carparks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarParks(@QueryParam("name") String name) {
        if(name != null){
            CarPark carPark = service.getCarPark(name);
            if(carPark == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }else{
                CarParkDto carParkDto = factory.transformToDto(carPark);
                return Response.status(Response.Status.OK).entity(carParkDto).build();
            }
        }else{
            List<CarPark> carParks = service.getCarParks();
            List<CarParkDto> carParkDtoList = carParks.stream().map(factory::transformToDto).collect(Collectors.toList());
            if(carParkDtoList.isEmpty()){
                return Response.status(Response.Status.OK).entity("").build();
            }else{
                return Response.status(Response.Status.OK).entity(carParkDtoList).build();
            }
        }
    }

    @GET
    @Path("/carparks/{id}")
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
    @Path("/carparks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCarPark(String body) {
        try{
            CarParkDto carParkDto = json.readValue(body, CarParkDto.class);

            if(carParkDto.getFloors() == null){
                carParkDto.setFloors(new ArrayList<>());
            }

            CarPark carPark = factory.transformToEntity(carParkDto);

            // Testing if every child was made
            if(carPark == null) throw new Exception();
            for(CarParkFloor carParkFloor : carPark.getCarParkFloors()){
                if(carParkFloor == null) throw new Exception();
            }
            for(CarParkFloor floor : carPark.getCarParkFloors()){
                for(ParkingSpot parkingSpot : floor.getParkingSpots()){
                    if(parkingSpot == null) throw new Exception();
                }
            }

            carParkDto = factory.transformToDto(carPark);
            return Response.status(Response.Status.CREATED).entity(carParkDto).build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/carparks/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCarPark(@PathParam("id") Long id) {
        CarPark carParkToDelete =  service.deleteCarPark(id);
        if(carParkToDelete == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
}
