package sk.stuba.fei.uim.vsa.pr2;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.*;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class Project2Application extends Application {
    static final Set<Class<?>> appClasses = new HashSet<>();

    static {
        appClasses.add(CarParkController.class);
        appClasses.add(CarParkFloorController.class);
        appClasses.add(CarController.class);
        appClasses.add(CarTypeController.class);
        appClasses.add(UserController.class);
        appClasses.add(ReservationController.class);
        appClasses.add(ParkingSpotController.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return appClasses;
    }
}
