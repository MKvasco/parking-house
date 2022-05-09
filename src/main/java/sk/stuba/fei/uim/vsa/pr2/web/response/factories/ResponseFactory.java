package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

public interface ResponseFactory<R, T> {

    T transformToDto(R entity);

    R transformToEntity(T dto);
    
}
