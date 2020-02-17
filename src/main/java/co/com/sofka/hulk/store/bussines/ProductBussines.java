package co.com.sofka.hulk.store.bussines;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sofka.hulk.store.commons.dto.ProductDTO;
import co.com.sofka.hulk.store.commons.exception.InvalidValueException;
import co.com.sofka.hulk.store.commons.exception.NotFoundException;
import co.com.sofka.hulk.store.repository.ProductRepository;
import co.com.sofka.hulk.store.services.data.Product;

@Service
public class ProductBussines {

	@Autowired
	private ProductRepository repository;

	/**
	 * return all products in stock
	 * 
	 * @return Returns all entities
	 */
	public List<ProductDTO> findAll() {
		return repository.findAll().stream().map(Product::castDto).collect(Collectors.toList());
	}

	/**
	 * Retrieves an entity by its id
	 * 
	 * @param id must not be null
	 * @throws InvalidValueException NotFoundException
	 */
	public ProductDTO findById(Long id) throws NotFoundException, InvalidValueException {

		if (id == null) {
			throw new InvalidValueException("id");
		}

		Optional<Product> product = repository.findById(id);

		if (!product.isPresent()) {
			throw new NotFoundException(Product.class);
		}

		return Product.castDto(product.get());
	}

	/**
	 * Saves a given entity
	 * 
	 * @param dto
	 * @return the saved entity; is not null.
	 * @throws InvalidValueException
	 */
	public ProductDTO insert(ProductDTO dto) throws InvalidValueException {

		if (dto == null) {
			throw new InvalidValueException();
		}

		return Product.castDto(repository.save(Product.castObj(dto)));

	}

	/**
	 * Saves a given entity
	 * 
	 * @param dto; id must not be null
	 * @return the saved entity; is not null.
	 */
	public ProductDTO update(Long id, ProductDTO dto) throws NotFoundException, InvalidValueException {

		if (id == null) {
			throw new InvalidValueException("id");
		}
		
		if (dto == null) {
			throw new InvalidValueException();
		}

		// Validate if exist
		findById(id);
		dto.setId(id);

		return Product.castDto(repository.save(Product.castObj(dto)));
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
