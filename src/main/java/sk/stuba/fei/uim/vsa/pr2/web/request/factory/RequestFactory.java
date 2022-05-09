package sk.stuba.fei.uim.vsa.pr2.web.request.factory;

public interface RequestFactory<R, T> {

    T transformToDto(R entity);

    R transformToEntity(T dto);
    
}
