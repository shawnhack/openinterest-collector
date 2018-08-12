package online.openinterest.collector.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthController {

	@GetMapping("/healthcheck")
	public ResponseEntity<String> healthCheck() {
		return ResponseEntity.ok().body("Healthy");
	}

}
