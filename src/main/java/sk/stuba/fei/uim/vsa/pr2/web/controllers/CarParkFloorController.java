package sk.stuba.fei.uim.vsa.pr2.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.CarParkFloorService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarParkFloorDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factories.CarParkFloorFactory;

import java.util.List;
import java.util.stream.Collectors;

@Path("/")
public class CarParkFloorController {

    private final ObjectMapper json = new ObjectMapper();
    private final CarParkFloorService service = new CarParkFloorService();
    private final CarParkFloorFactory factory = new CarParkFloorFactory();

    @GET
    @Path("/carparkfloors/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarParkFloor(@PathParam("id") Long id) {
        CarParkFloor carParkFloor = service.getCarParkFloor(id);
        if(carParkFloor == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            CarParkFloorDto carParkFloorDto = factory.transformToDto(carParkFloor);
            return Response.status(Response.Status.OK).entity(carParkFloorDto).build();
        }
    }

    @GET
    @Path("/carparks/{id}/floors")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarParkFloors(@PathParam("id") Long id) {
        // TODO: CHECK IF CARPARK EXISTS
        List<CarParkFloor> carParkFloors = service.getCarParkFloors(id);
        List<CarParkFloorDto> carParkFloorDtoList = carParkFloors.stream().map(factory::transformToDto).collect(Collectors.toList());
        if (carParkFloorDtoList.isEmpty()) {
            return Response.status(Response.Status.OK).entity("").build();
        } else {
            return Response.status(Response.Status.OK).entity(carParkFloorDtoList).build();
        }
    }
    @POST
    @Path("/carparks/{id}/floors")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCarParkFloor(@PathParam("id") Long id, String body){
        try{
            CarParkFloorDto carParkFloorDto = json.readValue(body, CarParkFloorDto.class);
            CarParkFloor carParkFloor = factory.transformToEntity(carParkFloorDto);
            // Testing if everything was posted
            if(carParkFloor == null) throw new Exception();
            for(ParkingSpot parkingSpot : carParkFloor.getParkingSpots()){
                if (parkingSpot == null) throw new Exception();
            }
            carParkFloorDto = factory.transformToDto(carParkFloor);
            return Response.status(Response.Status.CREATED).entity(carParkFloorDto).build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @DELETE
    @Path("/carparks/{id}/floors/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCarParkFloor(@PathParam("id") Long id, @PathParam("identifier") String identifier){
       CarParkFloor carParkFloor = service.deleteCarParkFloor(id, identifier);
       if(carParkFloor == null){
           return Response.status(Response.Status.NOT_FOUND).build();
       }else{
           return Response.status(Response.Status.NO_CONTENT).build();
       }

    }

}
