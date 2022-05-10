package sk.stuba.fei.uim.vsa.pr2.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.service.ParkingSpotService;
import sk.stuba.fei.uim.vsa.pr2.web.response.ParkingSpotDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factories.ParkingSpotResponseFactory;

import java.util.List;
import java.util.stream.Collectors;

@Path("/")
public class ParkingSpotController {
    private final ObjectMapper json = new ObjectMapper();
    private final ParkingSpotService service = new ParkingSpotService();
    private final ParkingSpotResponseFactory factory = new ParkingSpotResponseFactory();

    @GET
    @Path("/carparks/{id}/spots")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParkingSpots(@PathParam("id") Long id) {
        // TODO: PARAMETER SEARCH
        List<ParkingSpot> parkingSpots = service.getParkingSpots(id);
        List<ParkingSpotDto> parkingSpotDtoList = parkingSpots.stream().map(factory::transformToDto).collect(Collectors.toList());
        if(parkingSpotDtoList.isEmpty()){
            return Response.status(Response.Status.OK).entity("").build();
        }else{
            return Response.status(Response.Status.OK).entity(parkingSpotDtoList).build();
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
    // TODO: PUT
    // TODO: POST

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
