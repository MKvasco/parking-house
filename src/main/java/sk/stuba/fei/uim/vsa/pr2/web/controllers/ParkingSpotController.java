package sk.stuba.fei.uim.vsa.pr2.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.ParkingSpotService;
import sk.stuba.fei.uim.vsa.pr2.web.response.ParkingSpotDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factories.ParkingSpotFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/")
public class ParkingSpotController {
    private final ObjectMapper json = new ObjectMapper();
    private final ParkingSpotService service = new ParkingSpotService();
    private final ParkingSpotFactory factory = new ParkingSpotFactory();

    @GET
    @Path("/carparks/{id}/spots")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParkingSpots(@PathParam("id") Long id, @QueryParam("free") Boolean free) {
        List<ParkingSpot> parkingSpots = service.getParkingSpots(id);
        // For testing purposes
//        for(ParkingSpot parkingSpot : parkingSpots){
//            if(parkingSpot.getId() == 14 || parkingSpot.getId() == 15) parkingSpot.setAvailable(false);
//        }
        List<ParkingSpotDto> parkingSpotDtoList = parkingSpots.stream().map(factory::transformToDto).collect(Collectors.toList());
        if(free != null){
            List<ParkingSpotDto> availableParkingSpotDtoList = new ArrayList<>();
            for(ParkingSpotDto parkingSpot : parkingSpotDtoList){
                if(parkingSpot.getFree() == free) availableParkingSpotDtoList.add(parkingSpot);
            }
            return Response.status(Response.Status.OK).entity(availableParkingSpotDtoList).build();
        }else{
            if(parkingSpotDtoList.isEmpty()){
                return Response.status(Response.Status.OK).entity("").build();
            }else{
                return Response.status(Response.Status.OK).entity(parkingSpotDtoList).build();
            }
        }
    }

    @GET
    @Path("/carparks/{id}/floors/{identifier}/spots")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParkingSpots(@PathParam("id") Long id, @PathParam("identifier") String identifier){
        List<ParkingSpot> parkingSpots = service.getParkingSpots(id, identifier);
        List<ParkingSpotDto> parkingSpotDtoList = parkingSpots.stream().map(factory::transformToDto).collect(Collectors.toList());
        if(parkingSpotDtoList.isEmpty()){
            return Response.status(Response.Status.OK).entity("").build();
        }else{
            return Response.status(Response.Status.OK).entity(parkingSpotDtoList).build();
        }
    }

    @GET
    @Path("/parkingspots/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParkingSpot(@PathParam("id") Long id){
        ParkingSpot parkingSpot = service.getParkingSpot(id);
        ParkingSpotDto parkingSpotDto = factory.transformToDto(parkingSpot);
        if(parkingSpotDto == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.status(Response.Status.OK).entity(parkingSpotDto).build();
        }
    }

    @POST
    @Path("/carparks/{id}/floors/{identifier}/spots")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createParkingSpot(@PathParam("id") Long carParkId,@PathParam("identifier") String identifier, String body){
        try{
            ParkingSpotDto parkingSpotDto = json.readValue(body, ParkingSpotDto.class);
            parkingSpotDto.setCarPark(carParkId);
            parkingSpotDto.setCarParkFloor(identifier);
            ParkingSpot parkingSpot = factory.transformToEntity(parkingSpotDto);
            parkingSpotDto = factory.transformToDto(parkingSpot);
            return Response.status(Response.Status.CREATED).entity(parkingSpotDto).build();
        }catch(Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/parkingspots/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteParkingSpot(@PathParam("id") Long id){
        ParkingSpot parkingSpot = service.deleteParkingSpot(id);
        if(parkingSpot == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
}
