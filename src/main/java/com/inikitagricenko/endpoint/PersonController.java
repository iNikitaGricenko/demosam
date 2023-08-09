package com.inikitagricenko.endpoint;

import com.inikitagricenko.model.PersonRequest;
import com.inikitagricenko.service.PersistenceGateway;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

	private final PersistenceGateway persistenceGateway;

	public PersonController(PersistenceGateway persistenceGateway) {
		this.persistenceGateway = persistenceGateway;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonRequest> getPerson() {
		return persistenceGateway.getAll();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonRequest getPerson(@PathVariable Long id) {
		return persistenceGateway.get(id);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void savePerson(PersonRequest personRequest) {
		persistenceGateway.save(personRequest);
	}

}
