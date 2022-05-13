package sk.stuba.fei.uim.vsa.pr2.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.ReservationService;
import sk.stuba.fei.uim.vsa.pr2.web.response.DtoToReservationsDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factories.DtoToReservationFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Path("/")
public class ReservationController {
    private final ObjectMapper json = new ObjectMapper();
    private final ReservationService service = new ReservationService();
    private final DtoToReservationFactory factory = new DtoToReservationFactory();

    @GET
    @Path("/reservations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservations(@QueryParam("user") Long userId, @QueryParam("spot") Long spotId, @QueryParam("date") Date date){
        List<Reservation> reservations = service.getReservations();
        List<DtoToReservationsDto> reservatioDtoList = reservations.stream().map(factory::transformToDto).collect(Collectors.toList());
        if(userId != null){
            List<DtoToReservationsDto> filteredReservations = new ArrayList<>();
            for(DtoToReservationsDto reservation : reservatioDtoList){
                if(Objects.equals(reservation.getCar().getOwner().getId(), userId)) filteredReservations.add(reservation);
            }
            return Response.status(Response.Status.OK).entity(filteredReservations).build();
        }else{
            return Response.status(Response.Status.OK).entity(reservatioDtoList).build();
        }
    }

    @GET
    @Path("/reservations/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservation(@PathParam("id") Long id){
        Reservation reservation = service.getReservation(id);
        if(reservation == null) return Response.status(Response.Status.NOT_FOUND).build();
        DtoToReservationsDto reservationsDto = factory.transformToDto(reservation);
        return Response.status(Response.Status.OK).entity(reservationsDto).build();
    }

    @POST
    @Path("/reservations/{id}/end")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response endReservation(@PathParam("id") Long id){
        Reservation reservation = service.getReservation(id);
        if(reservation == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.endReservation(id);
        return Response.status(Response.Status.OK).entity(reservation).build();
    }

    @POST
    @Path("/reservations")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createReservation(String body){
        try{
            DtoToReservationsDto reservationsDto = json.readValue(body, DtoToReservationsDto.class);
            Reservation reservation = factory.transformToEntity(reservationsDto);
            reservationsDto = factory.transformToDto(reservation);
            return Response.status(Response.Status.CREATED).entity(reservationsDto).build();
        }catch(Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
