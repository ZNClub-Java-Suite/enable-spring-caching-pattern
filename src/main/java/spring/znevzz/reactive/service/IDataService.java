package spring.znevzz.reactive.service;

public interface IDataService<T, R> {

    T generate(R r);

    default String performanceDetails(String status,
                                      long milliseconds){
        return String.format("COMPLETED: STATUS=%s, TIME_MS=%d"
        , status, milliseconds);
    }
}
