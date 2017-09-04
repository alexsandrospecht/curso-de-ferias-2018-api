package matera.systems.cursoferias2018.api.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/health")
public class HealthCheckRS {

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "text/plain"
    )
    public ResponseEntity<String> check() {
        return ResponseEntity.status(200).body("I' ok");
    }

}
