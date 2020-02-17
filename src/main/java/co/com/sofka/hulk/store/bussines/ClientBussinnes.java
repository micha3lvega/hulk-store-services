package co.com.sofka.hulk.store.bussines;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sofka.hulk.store.commons.dto.ClientDTO;
import co.com.sofka.hulk.store.commons.exception.InvalidValueException;
import co.com.sofka.hulk.store.commons.exception.NotFoundException;
import co.com.sofka.hulk.store.repository.ClientRepository;
import co.com.sofka.hulk.store.services.data.Client;
import co.com.sofka.hulk.store.services.data.Product;

@Service
public class ClientBussinnes {
	
	@Autowired
	private ClientRepository repository;

	/**
	 * return all products in stock
	 * 
	 * @return Returns all entities
	 */
	public List<ClientDTO> findAll() {
		return repository.findAll().stream().map(Client::castDto).collect(Collectors.toList());
	}

	/**
	 * Retrieves an entity by its id
	 * 
	 * @param id must not be null
	 * @throws InvalidValueException NotFoundException
	 */
	public ClientDTO findById(Long id) throws NotFoundException, InvalidValueException {

		if (id == null) {
			throw new InvalidValueException("id");
		}

		Optional<Client> client = repository.findById(id);

		if (!client.isPresent()) {
			throw new NotFoundException(Product.class);
		}

		return Client.castDto(client.get());
	}

	/**
	 * Saves a given entity
	 * 
	 * @param dto
	 * @return the saved entity; is not null.
	 * @throws InvalidValueException
	 */
	public ClientDTO insert(ClientDTO dto) throws InvalidValueException {

		if (dto == null) {
			throw new InvalidValueException();
		}

		return Client.castDto(repository.save(Client.castObj(dto)));

	}

	/**
	 * Saves a given entity
	 * 
	 * @param dto; id must not be null
	 * @return the saved entity; is not null.
	 */
	public ClientDTO update(Long id, ClientDTO dto) throws NotFoundException, InvalidValueException {

		if (id == null) {
			throw new InvalidValueException("id");
		}
		
		if (dto == null) {
			throw new InvalidValueException();
		}

		// Validate if exist
		findById(id);
		dto.setId(id);

		return Client.castDto(repository.save(Client.castObj(dto)));
	}

	/**
	 * Deletes the entity with the given id.
	 *
	 * @param id must not be null
	 * @throws IllegalArgumentException in case the given {@literal id} is
	 *                                  {@literal null}
	 */
	public void delele(Long id) throws NotFoundException, InvalidValueException {

		// Validate if exist
		findById(id);

		repository.deleteById(id);

	}

}
