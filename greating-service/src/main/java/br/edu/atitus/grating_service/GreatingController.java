package br.edu.atitus.grating_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.grating_service.configs.GreatingConfig;

@RestController
@RequestMapping ("greatin-service")
public class GreatingController {
//	@Value("${greating-service.greating}")
//	private String greating;
//	@Value("${greating-service.default-name}")
//	private String defaultName;
	
	private final GreatingConfig greatingConfig;
	
	
	public GreatingController(GreatingConfig greatingConfig) {
		super();
		this.greatingConfig = greatingConfig;
	}




	@GetMapping({"","/","/{namePath}"})
	public ResponseEntity<String> getGreatin(
			@PathVariable(required = false) String namePath,
			@RequestParam(required = false) String name 
			) {
		if (name == null)
			name = namePath != null ? namePath : greatingConfig.getDefaultName();
		
		String returnText = String.format("%s %s!",greatingConfig.getGreating(), name);
		return ResponseEntity.ok(returnText);
	}

}
