package com.matera.cursoferias.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matera.cursoferias.requests.HelloRequest;
import com.matera.cursoferias.responses.HelloResponse;

@RestController
public class HelloApi {

	@GetMapping(
		path = "/aula/hello/v1",
		produces = { 
			"text/plain" 
		}
	)
	public ResponseEntity<String> sayHello(
			@RequestParam(
					required = false, 
					defaultValue = "World"
			) String who
	) {
		return ResponseEntity.status(200).body("Hello " + who + "!");
	}
	
	@PostMapping(
			path = "aula/hello/v2", 
			consumes = {
				"application/json"
			},
			produces = {
				"application/json"
			})
	public ResponseEntity<HelloResponse> sayHello(@RequestBody HelloRequest request) {
		
		HelloResponse response = new HelloResponse();
		response.setName(request.getWho());
		response.setMessage(request.getMessage());
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(
			path = "aula/hello/v2", 
			produces = {
				"application/json"
			},
			method = RequestMethod.GET
	)
	public ResponseEntity<HelloResponse> sayHello(
			@RequestParam String who, 
			@RequestParam String message
	) {
		
		HelloResponse response = new HelloResponse();
		response.setName(who);
		response.setMessage(message);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(path = "redirect")
	public ResponseEntity<?> redirectTest(@RequestParam String target) {
		
		return ResponseEntity
				.status(307)
				.header("Location", target)
				.build();
	}
	
}



