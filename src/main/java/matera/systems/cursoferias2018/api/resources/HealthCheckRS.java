package matera.systems.cursoferias2018.api.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/health")
public class HealthCheckRS {

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("I am ok");
    }

}
