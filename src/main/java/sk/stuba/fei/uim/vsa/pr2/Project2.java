package sk.stuba.fei.uim.vsa.pr2;


import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import sk.stuba.fei.uim.vsa.pr2.domain.*;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.ReservationService;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.ServiceController;

import java.net.URI;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Project2 {

    public static final Logger LOGGER = Logger.getLogger(Project2.class.getName());
    public static final String BASE_URI = "http://localhost/";
    public static final int PORT = 8080;
    public static final Class<? extends Application> APPLICATION_CLASS = Project2Application.class;

    public static void main(String[] args) {
        try {
            final HttpServer server = startServer();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("Shutting down the application...");
                    server.shutdownNow();
                    System.out.println("Exiting");
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, null, e);
                }
            }));
            System.out.println("Last steps of setting up the application...");
            postStart();
            System.out.printf("Application started.%nStop the application using CRL+C%n");
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public static HttpServer startServer() {
        final ResourceConfig config = ResourceConfig.forApplicationClass(APPLICATION_CLASS);
        URI baseUri = UriBuilder.fromUri(BASE_URI).port(PORT).build();
        LOGGER.info("Starting Grizzly2 HTTP server...");
        LOGGER.info("Server listening on " + BASE_URI + ":" + PORT);
        return GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
    }

    public static void postStart() {
        ServiceController service = new ServiceController();
        CarPark avion =  service.createCarPark("Avion", "Ivanska cesta", 2);
        CarPark aupark =  service.createCarPark("Aupark", "Petrzalka", 3);
        CarPark vivo =  service.createCarPark("Vivo", "Nove Mesto", 1);

        CarParkFloor first_floor_avion = service.createCarParkFloor("first_floor", avion,new ArrayList<>());
        CarParkFloor second_floor_avion = service.createCarParkFloor("second_floor", avion, new ArrayList<>());
        CarParkFloor third_floor_avion = service.createCarParkFloor("third_floor", avion, new ArrayList<>());
        CarParkFloor first_floor_aupark = service.createCarParkFloor("first_floor", aupark, new ArrayList<>());
        CarParkFloor second_floor_aupark = service.createCarParkFloor("second_floor", aupark, new ArrayList<>());

        CarType benzin =  service.createCarType("benzin");
        CarType nafta =  service.createCarType("nafta");
        CarType elektro =  service.createCarType("elektro");

        ParkingSpot parkingSpot1 = service.createParkingSpot(1L, "first_floor", "spot1", 9L);
        ParkingSpot parkingSpot2 = service.createParkingSpot(1L, "first_floor", "spot2", 10L);
        ParkingSpot parkingSpot3 = service.createParkingSpot(1L, "first_floor", "spot3", 9L);
        ParkingSpot parkingSpot4 = service.createParkingSpot(1L, "second_floor", "spot4", 10L);
        ParkingSpot parkingSpot5 = service.createParkingSpot(1L, "second_floor", "spot5", 10L);

        User marco = service.createUser("Marco", "Kvasnica", "ahoj@ahoj.sk");
        User klara = service.createUser("Klara", "Vizarova", "cau@cau.sk");

        Car bmw = service.createCar(17L, "BMW", "7", "Black", "SC365EV", 9L);
        Car audi = service.createCar(18L, "AUDI", "Q8", "Silver", "BL234EV", 10L);

        Reservation reservation1 = service.createReservation(parkingSpot1.getId(), bmw.getId());
        Reservation reservation4 = service.createReservation(parkingSpot2.getId(), audi.getId());
        new ReservationService().endReservation(reservation1.getId());

    }

}
