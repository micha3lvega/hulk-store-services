package co.com.sofka.hulk.store.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.sofka.hulk.store.bussines.ClientBussinnes;
import co.com.sofka.hulk.store.commons.dto.ClientDTO;
import co.com.sofka.hulk.store.commons.exception.InvalidValueException;
import co.com.sofka.hulk.store.commons.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

	@Autowired
	private ClientBussinnes bussines;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(bussines.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {

		try {
			return ResponseEntity.ok(bussines.findById(id));
		} catch (NotFoundException e) {
			log.error("Id " + id + " is not existed");
			return ResponseEntity.notFound().build();
		} catch (InvalidValueException e) {
			log.error("(ClientController) " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}

	}

	@PostMapping
	public ResponseEntity<?> insert(@Valid @RequestBody ClientDTO client) {

		try {
			return ResponseEntity.ok(bussines.insert(client));
		} catch (InvalidValueException e) {
			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ClientDTO client) {

		try {
			return ResponseEntity.ok(bussines.update(id, client));
		} catch (NotFoundException e) {
			log.error("Id " + id + " is not existed");
			return ResponseEntity.notFound().build();
		} catch (InvalidValueException e) {
			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			bussines.delele(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (NotFoundException e) {
			log.error("Id " + id + " is not existed");
			return ResponseEntity.notFound().build();
		} catch (InvalidValueException e) {
			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}
	}

}
