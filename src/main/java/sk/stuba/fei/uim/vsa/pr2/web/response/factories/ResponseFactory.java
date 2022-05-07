package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.web.response.Dto;

public interface ResponseFactory<R, T extends Dto> {

    T transformToDto(R entity);

    R transformToEntity(R dot);
    
}
