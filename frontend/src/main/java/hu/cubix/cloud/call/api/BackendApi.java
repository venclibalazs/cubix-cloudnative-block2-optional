package hu.cubix.cloud.call.api;

import hu.cubix.cloud.call.model.BackendResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "backend", url = "${backend.url:http://localhost}", path = "/backend")
public interface BackendApi {

    @GetMapping
    public BackendResponse backend();
}
