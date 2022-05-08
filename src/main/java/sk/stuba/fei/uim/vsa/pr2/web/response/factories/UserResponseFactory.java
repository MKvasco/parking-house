package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.User;
import sk.stuba.fei.uim.vsa.pr2.web.response.UserDto;

public class UserResponseFactory implements ResponseFactory<User, UserDto>{
    @Override
    public UserDto transformToDto(User entity) {
        return null;
    }

    @Override
    public User transformToEntity(UserDto dto) {
        return null;
    }
}
