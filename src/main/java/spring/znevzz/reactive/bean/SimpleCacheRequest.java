package spring.znevzz.reactive.bean;

import lombok.Data;

@Data
public class SimpleCacheRequest implements ICacheRequest<String> {

    private String user;
    private String payload;

    @Override
    public String getUser() {
        return "";
    }

    @Override
    public String getPayload() {
        return this.payload;
    }
}
