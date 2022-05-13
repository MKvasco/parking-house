package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.Car;
import sk.stuba.fei.uim.vsa.pr2.domain.User;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.UserService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarWithUsersDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.UserWithCarsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserWithCarsFactory implements Factory<User, UserWithCarsDto> {
    @Override
    public UserWithCarsDto transformToDto(User entity) {
        UserWithCarsDto userWithCarsDto = new UserWithCarsDto();
        userWithCarsDto.setId(entity.getId());
        userWithCarsDto.setFirstname(entity.getName());
        userWithCarsDto.setLastname(entity.getSurname());
        userWithCarsDto.setEmail(entity.getEmail());
        List<Car> cars = entity.getCars();
        if(cars == null) cars = new ArrayList<>();
        if(!cars.isEmpty()){
            List<Long> carIdList = new ArrayList<>();
            for(Car car : cars) {
                carIdList.add(car.getId());
            }
            userWithCarsDto.setCars(carIdList);
        }else{
            userWithCarsDto.setCars(new ArrayList<>());
        }
        return userWithCarsDto;
    }

    @Override
    public User transformToEntity(UserWithCarsDto dto) {
        User user = new User();
        if(new UserService().getUser(dto.getEmail()) == null) user = new UserService().createUser(dto.getFirstname(), dto.getLastname(), dto.getEmail());
        if(new UserService().getUser(dto.getEmail()) != null) user = new UserService().getUser(dto.getEmail());
        return user;
    }
}
