package matera.systems.cursoferias2018.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/health")
public class HealthCheckRS {

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("I am ok");
    }

}
