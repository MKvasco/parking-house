package sk.stuba.fei.uim.vsa.pr2.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.Car;
import sk.stuba.fei.uim.vsa.pr2.domain.User;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.CarService;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.ServiceController;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factories.CarFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Path("/")
public class CarController {

    private final ObjectMapper json = new ObjectMapper();
    private final CarService service = new CarService();
    private final CarFactory factory = new CarFactory();

    @GET
    @Path("/cars")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCars(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization, @QueryParam("vrp") String vrp, @QueryParam("user") Long id) {
        User userAuth = new ServiceController().getUserAuth(authorization);
        if (userAuth == null) return Response.status(Response.Status.UNAUTHORIZED).build();
        List<Car> cars = service.getCars();
        List<CarDto> carDtoList = cars.stream().map(factory::transformToDto).collect(Collectors.toList());
        if(vrp != null && id != null){
            List<CarDto> filteredCars = new ArrayList<>();
            for(CarDto carDto : carDtoList){
                if(Objects.equals(carDto.getOwner(), id) && Objects.equals(carDto.getVrp(), vrp)) filteredCars.add(carDto);
            }
            return Response.status(Response.Status.OK).entity(filteredCars).build();
        }else {
            if (vrp != null) {
                List<CarDto> filteredCars = new ArrayList<>();
                for (CarDto carDto : carDtoList) {
                    if (Objects.equals(carDto.getVrp(), vrp)) filteredCars.add(carDto);
                }
                return Response.status(Response.Status.OK).entity(filteredCars).build();
            }
            if (id != null) {
                List<CarDto> filteredCars = new ArrayList<>();
                for (CarDto carDto : carDtoList) {
                    if (Objects.equals(carDto.getOwner(), id)) filteredCars.add(carDto);
                }
                return Response.status(Response.Status.OK).entity(filteredCars).build();
            }
            return Response.status(Response.Status.OK).entity(carDtoList).build();
        }
    }

    @GET
    @Path("/cars/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCar(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization, @PathParam("id") Long id){
        User userAuth = new ServiceController().getUserAuth(authorization);
        if (userAuth == null) return Response.status(Response.Status.UNAUTHORIZED).build();
        Car car = service.getCar(id);
        if(car == null) return Response.status(Response.Status.NOT_FOUND).build();
        CarDto carDto = factory.transformToDto(car);
        return Response.status(Response.Status.OK).entity(carDto).build();
    }

    @POST
    @Path("/cars")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCar(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization, String body){
        User userAuth = new ServiceController().getUserAuth(authorization);
        if (userAuth == null) return Response.status(Response.Status.UNAUTHORIZED).build();
        try{
            CarDto carDto = json.readValue(body, CarDto.class);
            Car car = factory.transformToEntity(carDto);
            if(car == null) throw new Exception();
            carDto = factory.transformToDto(car);
            return Response.status(Response.Status.CREATED).entity(carDto).build();
        }catch(Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/cars/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCar(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization,@PathParam("id") Long id){
        User userAuth = new ServiceController().getUserAuth(authorization);
        if (userAuth == null) return Response.status(Response.Status.UNAUTHORIZED).build();
        Car car = service.getCar(id);
        if(car == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.deleteCar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

