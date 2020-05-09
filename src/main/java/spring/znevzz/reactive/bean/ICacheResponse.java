package spring.znevzz.reactive.bean;

import java.time.LocalDate;
import java.util.Optional;

public interface ICacheResponse<R> {
    LocalDate getLastUpdated();
    Optional<R> getPayload();
}
