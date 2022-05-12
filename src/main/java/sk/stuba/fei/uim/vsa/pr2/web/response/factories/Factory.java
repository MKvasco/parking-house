package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

public interface Factory<R, T> {

    T transformToDto(R entity);

    R transformToEntity(T dto);
    
}
