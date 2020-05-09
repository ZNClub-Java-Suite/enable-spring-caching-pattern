package spring.znevzz.reactive.bean;

import lombok.Data;

import java.time.LocalDate;
import java.util.Optional;

@Data
public class SimpleCacheResponse implements ICacheResponse<String> {

    private String payload;

    @Override
    public LocalDate getLastUpdated() {
        return LocalDate.now();
    }

    @Override
    public Optional<String> getPayload() {
        return Optional.ofNullable(payload);
    }


}
