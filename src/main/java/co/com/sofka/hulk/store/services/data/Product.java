package co.com.sofka.hulk.store.services.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import co.com.sofka.hulk.store.commons.dto.ProductDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private MeasurementUnit measurementUnit;

	@ManyToOne(fetch = FetchType.LAZY)
	private Provider provider;

	@NotBlank
	@Column(name = "unite_price")
	private String unitPrice;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;
	
	@PrePersist
	public void prePersist() {
		
		if (this.createdAt == null) {
			this.createdAt = new Date();
		}
		this.updatedAt = new Date();
		
	}

	public static ProductDTO castDto(Product obj) {

		ModelMapper mapper = new ModelMapper();
		return mapper.map(obj, ProductDTO.class);

	}

	public static Product castObj(ProductDTO dto) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(dto, Product.class);
	}
	
	
}
