package sk.stuba.fei.uim.vsa.pr2.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.Car;
import sk.stuba.fei.uim.vsa.pr2.service.CarService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factories.CarResponseFactory;

import java.util.List;
import java.util.stream.Collectors;

@Path("/")
public class CarController {

    private final ObjectMapper json = new ObjectMapper();
    private final CarService service = new CarService();
    private final CarResponseFactory factory = new CarResponseFactory();

    @GET
    @Path("/cars")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarParks() {
        // TODO: PARAMETER SEARCH
        List<Car> cars = service.getCars();
        List<CarDto> carDtoList = cars.stream().map(factory::transformToDto).collect(Collectors.toList());
        if (carDtoList.isEmpty()) {
            return Response.status(Response.Status.OK).entity("").build();
        } else {
            return Response.status(Response.Status.OK).entity(carDtoList).build();
        }
    }
}

